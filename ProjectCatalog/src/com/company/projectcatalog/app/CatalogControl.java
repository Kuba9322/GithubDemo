package com.company.projectcatalog.app;

import com.company.projectcatalog.exeptions.*;
import com.company.projectcatalog.io.ControlPrinter;
import com.company.projectcatalog.io.DataReader;
import com.company.projectcatalog.io.file.FileManager;
import com.company.projectcatalog.io.file.FileManagerBuilder;
import com.company.projectcatalog.model.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;

public class CatalogControl {
    private ControlPrinter printer = new ControlPrinter();
    private DataReader dataReader = new DataReader(printer);
    private FileManager fileManager;
    private ProjectsCatalog projectsCatalog;

    protected CatalogControl() {
        fileManager = new FileManagerBuilder(printer, dataReader).build();
        try {
            projectsCatalog = fileManager.importData();
            printer.printLine("Zaimportowano dane z pliku.");
        } catch (DataImportExeption | InvalidDataExeption e) {
            printer.printLine(e.getMessage());
            printer.printLine("Zainicjowano nową bazę.");
            projectsCatalog = new ProjectsCatalog();
        }
    }

    void controlLoop() {
        MainOption mainOption;
        do {
            printOptions(MainOption.values());
           mainOption = getOption();
            switch (mainOption) {
                case ADD_ORIGINAL_PROJECT:
                    addOriginalProject();
                    break;
                case ADD_SUBSTITUTION_PROJECT:
                    addSubstitutionProject();
                    break;
                case PRINT_ORIGINAL_PROJECTS:
                    printOriginalProjects();
                    break;
                case PRINT_SUBSTITUTION_PROJECTS:
                    printSubstitutionProjects();
                    break;
                case DELETE_PROJECT:
                    deleteProject();
                    break;
                case ADD_CUSTOMER:
                    addCustomer();
                    break;
                case PRINT_CUSTOMERS:
                    printCustomers();
                    break;
                case FIND_PROJECT:
                    findAndPrintProjectByTittle();
                    break;
                case EXIT:
                    exit();
                    break;
                default:
                    System.out.println("Nie ma takiej opcji! Wprowadź ponownie:");
            }

        } while (mainOption != MainOption.EXIT);
    }

    private void findAndPrintProjectByTittle() {
        printer.printLine("Wpisz nazwę szukanego projektu: ");
        String findingProject = dataReader.getString().toUpperCase();
        ArrayList<Project> projectsByTittle = projectsCatalog.findProjectsByTittle(findingProject);
        printer.printProjects(projectsByTittle);

    }


    private void addCustomer() {
        Customer customer = dataReader.createCustomer();
        try {
            projectsCatalog.addCustomer(customer);
        } catch (CustomerAlreadyExistExeption e) {
            printer.printLine(e.getMessage());
        }
    }

    private void printCustomers() {
        printer.printCustomers(projectsCatalog.getSortedCustomers(
                Comparator.comparing(Customer::getLastName, String.CASE_INSENSITIVE_ORDER)));
    }

    private MainOption getOption() {
        boolean optionOk = false;
        MainOption mainOption = null;
        while (!optionOk) {
            try {
                mainOption = createOptionFromInt(dataReader.getInt());
                optionOk = true;
            } catch (NoSuchOptionException e) {
                printer.printLine(e.getMessage() + ". Podaj ponownie:");
            } catch (InputMismatchException ignored) {
                printer.printLine("Wprowadzono wartość, która nie jest liczbą.");
            }
        }
        return mainOption;
    }

    private DeleteOption getDeleteOption() {
        boolean optionOk = false;
        DeleteOption option = null;
        while (!optionOk) {
            try {
                option = createDeleteOptionFromInt(dataReader.getInt());
                optionOk = true;
            } catch (NoSuchOptionException e) {
                printer.printLine(e.getMessage() + ". Podaj ponownie:");
            } catch (InputMismatchException ignored) {
                printer.printLine("Wprowadzono wartość, która nie jest liczbą.");
            }
        }
        return option;
    }


    private void exit() {
        try {
            fileManager.exportData(projectsCatalog);
            printer.printLine("Dane zostały zapisane.");
        } catch (DataExportExeption e) {
            printer.printLine(e.getMessage());
        }
        printer.printLine("Zakończono działanie programu.");
        dataReader.close();
    }

    private void printProjects(ArrayList<Project> projectsByTittle) {
        printer.printProjects(projectsByTittle);
    }

    private void printOriginalProjects() {
        printer.printOriginalProjects(projectsCatalog.getSortedProjects(
                Comparator.comparing(Project::getTitle, String.CASE_INSENSITIVE_ORDER)));
    }

    private void addOriginalProject() {
        try {
            OriginalProject originalProject = dataReader.readAndCreateOriginalProject();
            projectsCatalog.addProject(originalProject);
        } catch (InputMismatchException e) {
            printer.printLine("Dane niepoprawne! Nie udało się utworzyć projektu. ");
        }
    }

    private void printSubstitutionProjects() {
        printer.printSubstitutionProjects(projectsCatalog.getSortedProjects(
                Comparator.comparing(Project::getTitle, String.CASE_INSENSITIVE_ORDER)
        ));
    }

    private void addSubstitutionProject() {
        try {
            SubstitutionProject substitutionProject = dataReader.readAndCreateSubstitutionProject();
            projectsCatalog.addProject(substitutionProject);
        } catch (InputMismatchException e) {
            printer.printLine("Dane niepoprawne! Nie udało się utworzyć projektu. ");
        }
    }

    void printOptions(Enum[] enumTab) {
        System.out.println("Wybierz opcję:");
        for (Enum s : enumTab) {
            printer.printLine(s.toString());
        }
    }

    private void deleteProject() {
        DeleteOption option;
        do {
            printOptions(DeleteOption.values());
            option = getDeleteOption();
            switch (option) {
                case DELETE_ORIGINAL_PROJECT:
                    removeOriginalProject();
                    break;
                case DELETE_SUBSTITUTION_PROJECT:
                    removeSubstitutionProject();
                    break;
                case BACK:
                    break;
                default:
                    System.out.println("Nie ma takiej opcji! Wprowadź ponownie:");
            }

        } while (option != DeleteOption.BACK);
    }

    private void removeOriginalProject() {
        try {
            OriginalProject originalProject = dataReader.readAndCreateOriginalProject();
            boolean isRemoved = projectsCatalog.removeProject(originalProject);
            if (isRemoved)
                printer.printLine("Projekt został usunięty.");
        } catch (InputMismatchException e) {
            printer.printLine("Dane niepoprawne! Nie udało się usunąć projektu. ");
        }

    }

    private void removeSubstitutionProject() {
        try {
            SubstitutionProject substitutionProject = dataReader.readAndCreateSubstitutionProject();
            boolean isRemoved = projectsCatalog.removeProject(substitutionProject);
            if (isRemoved)
                printer.printLine("Projekt został usunięty.");
        } catch (InputMismatchException e) {
            printer.printLine("Dane niepoprawne! Nie udało się usunąć projektu. ");
        }
    }

    public enum MainOption {
        EXIT(0, "wyście z programu"),
        ADD_ORIGINAL_PROJECT(1, "dodanie nowego projektu"),
        ADD_SUBSTITUTION_PROJECT(2, "dodanie nowego projektu zamiennego"),
        PRINT_ORIGINAL_PROJECTS(3, "wyświetl realizowane projekty"),
        PRINT_SUBSTITUTION_PROJECTS(4, "wyświetl realizowane projekty zamienne"),
        DELETE_PROJECT(5, "usuń projekt z bazy"),
        ADD_CUSTOMER(6, "dodaj klienta do bazy"),
        PRINT_CUSTOMERS(7, "wyświetl klientów"),
        FIND_PROJECT(8,"znajdź projekt");

        private final int value;
        private final String description;

        MainOption(int value, String description) {
            this.value = value;
            this.description = description;
        }

        public int getValue() {
            return value;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return value + " - " + description;
        }

    }

    static MainOption createOptionFromInt(int option) {
        try {
            return MainOption.values()[option];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoSuchOptionException("Brak opcji o ID " + option);
        }
    }

    static DeleteOption createDeleteOptionFromInt(int deleteOption) {
        try {
            return DeleteOption.values()[deleteOption];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoSuchOptionException("Brak opcji o ID " + deleteOption);
        }
    }

    public enum DeleteOption {
        DELETE_ORIGINAL_PROJECT(0, "usuń projekt (oryginalny)"),
        DELETE_SUBSTITUTION_PROJECT(1, "usuń projekt (zamienny)"),
        BACK(2, "wróć");

        private final int value;
        private final String description;

        DeleteOption(int value, String description) {
            this.value = value;
            this.description = description;
        }

        @Override
        public String toString() {
            return value + " - " + description;
        }
    }
}
