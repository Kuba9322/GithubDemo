package com.company.projectcatalog.io.file;

import com.company.projectcatalog.exeptions.DataExportExeption;
import com.company.projectcatalog.exeptions.DataImportExeption;
import com.company.projectcatalog.exeptions.InvalidDataExeption;
import com.company.projectcatalog.model.*;
import java.io.*;
import java.util.Collection;

public class CsvFileManager implements FileManager {

    private static final String FILE_NAME = "Projects.csv";
    private static final String CUSTOMERS_FILE_NAME = "ProjectCatalogCustomers.csv";

    @Override
    public ProjectsCatalog importData() {
        ProjectsCatalog projectsCatalog = new ProjectsCatalog();
        importProjects(projectsCatalog);
        importCustomers(projectsCatalog);

        return projectsCatalog;
    }

    private void importCustomers(ProjectsCatalog projectsCatalog) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(CUSTOMERS_FILE_NAME))) {
            bufferedReader.lines()
                    .map(this::createCustomerFromString)
                    .forEach(projectsCatalog::addCustomer);
        } catch (IOException e) {
            throw new DataImportExeption(e.getMessage());
        }
    }

    private Customer createCustomerFromString(String csvText) {
        String[] data = csvText.split(";");
        String firstName = data[0];
        String lastName = data[1];
        return new Customer(firstName, lastName);
    }

    private void importProjects(ProjectsCatalog projectsCatalog) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME))) {
            bufferedReader.lines()
                    .map(this::createObjectFromString)
                    .forEach(projectsCatalog::addProject);
        } catch (IOException e) {
            throw new DataImportExeption(e.getMessage());
        }
    }

    private Project createObjectFromString(String line) {
        String[] data = line.split(";");
        String type = data[0];
        if (OriginalProject.TYPE.equals(type)) {
            return createOriginalProject(data);
        } else if (SubstitutionProject.TYPE.equals(type)) {
            return createSubstitutionProject(data);
        }
        throw new InvalidDataExeption("Nieobsługiwany typ projektu " + type);
    }

    private SubstitutionProject createSubstitutionProject(String[] data) {
        int id = Integer.parseInt(data[1]);
        String title = data[2];
        String trader = data[3];
        String purchaser = data[4];
        String purchasersCompany = data[5];
        double price = Double.parseDouble(data[6]);
        String offerent = data[7];
        return new SubstitutionProject(title, offerent, trader, purchaser, purchasersCompany, id, price);
    }

    private OriginalProject createOriginalProject(String[] data) {
        int id = Integer.parseInt(data[1]);
        String title = data[2];
        String trader = data[3];
        String purchaser = data[4];
        String purchasersCompany = data[5];
        double price = Double.parseDouble(data[6]);
        String designer = data[7];
        return new OriginalProject(title, designer, trader, purchaser, purchasersCompany, id, price);
    }

    @Override
    public void exportData(ProjectsCatalog projectsCatalog) {
        exportProjects(projectsCatalog);
        exportCustomers(projectsCatalog);

    }

    private void exportCustomers(ProjectsCatalog projectsCatalog) {
        Collection<Customer> customers = projectsCatalog.getCustomers().values();
        exportToCSV(customers, CUSTOMERS_FILE_NAME);

    }
    private void exportProjects(ProjectsCatalog projectsCatalog) {
        Collection<Project> projects = projectsCatalog.getProjects().values();
        exportToCSV(projects, FILE_NAME);
    }

    private <T extends CsvConvertible> void exportToCSV(Collection<T> collection, String fileName)  {
        try (
                FileWriter fileWriter = new FileWriter(fileName);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)
        ) {
            for (T element : collection) {
                bufferedWriter.write(element.toCSV());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new DataExportExeption("Błąd zapisu danych do pliku.");
        }
    }
}
