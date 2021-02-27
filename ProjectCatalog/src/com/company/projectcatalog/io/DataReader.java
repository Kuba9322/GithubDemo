package com.company.projectcatalog.io;

import com.company.projectcatalog.model.Customer;
import com.company.projectcatalog.model.OriginalProject;
import com.company.projectcatalog.model.SubstitutionProject;

import java.time.LocalDate;
import java.util.Scanner;

public class DataReader {
    private Scanner scanner = new Scanner(System.in);
    private ControlPrinter printer;

    public DataReader(ControlPrinter printer) {
        this.printer = printer;
    }


    public SubstitutionProject readAndCreateSubstitutionProject() {
        LocalDate receiveDate = LocalDate.now();
        printer.printLine("Podaj dane PROJEKTU:");
        printer.printLine("Nazwa: ");
        String title = scanner.nextLine();
        printer.printLine("Nazwisko i Imię oferenta wykonującego: ");
        String bidder = scanner.nextLine();
        printer.printLine("Nazwisko i Imię handlowca nadzorującego: ");
        String trader = scanner.nextLine();
        printer.printLine("Nazwisko i Imię klienta: ");
        String purchaser = scanner.nextLine();
        printer.printLine("Nazwa firmy klienta: ");
        String purchasersCompany = scanner.nextLine();
        printer.printLine("Numer ID projektu: ");
        int id = getInt();
        scanner.nextLine();
        printer.printLine("Kwota realizacji projektu: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        printer.printLine("Wczytano dane projektu zamiennego.");
        return new SubstitutionProject(title,trader, purchaser, purchasersCompany, id, price, receiveDate, bidder);
    }


    public OriginalProject readAndCreateOriginalProject() {
        LocalDate receiveDate = LocalDate.now();
        printer.printLine("Podaj dane PROJEKTU:");
        System.out.println("Nazwa: ");
        String title = scanner.nextLine();
        printer.printLine("Nazwisko i Imię projektanta wykonującego: ");
        String designer = scanner.nextLine();
        printer.printLine("Nazwisko i Imię handlowca nadzorującego: ");
        String trader = scanner.nextLine();
        printer.printLine("Nazwisko i Imię klienta: ");
        String purchaser = scanner.nextLine();
        printer.printLine("Nazwa firmy klienta: ");
        String purchasersCompany = scanner.nextLine();
        printer.printLine("Numer ID projektu: ");
        int id = getInt();
        printer.printLine("Kwota realizacji projektu: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        printer.printLine("Wczytano dane projektu.");
        return new OriginalProject(title, trader, purchaser, purchasersCompany, id, price, receiveDate, designer);
    }

    public String getString() {
        return scanner.nextLine();
    }

    public int getInt() {
        try {
            return scanner.nextInt();
        } finally {
            scanner.nextLine();
        }
    }

    public Customer createCustomer() {
        printer.printLine("Imię: ");
        String firstName = scanner.nextLine();
        printer.printLine("Nazwisko: ");
        String lastName = scanner.nextLine();
        return new Customer(firstName, lastName);
    }

    public void printCustomers(Customer customer) {
        printer.printLine(customer.toString());
    }


    public void close() {
        scanner.close();
    }

}
