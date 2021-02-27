package com.company.projectcatalog.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public abstract class Project implements Serializable, CsvConvertible {
    private String title;
    private String trader;
    private String purchaser;
    private String purchasersCompany;
    private int id;
    private double price;
    private LocalDate receiveDate;
    private LocalDate implementationDate;

    public Project(String title, String trader, String purchaser, String purchasersCompany, int id, double price, LocalDate receiveDate, LocalDate implementationDate) {
        this.title = title;
        this.trader = trader;
        this.purchaser = purchaser;
        this.purchasersCompany = purchasersCompany;
        this.id = id;
        this.price = price;
        this.receiveDate = receiveDate;
        this.implementationDate = implementationDate;
    }
    public Project(String title, String trader, String purchaser, String purchasersCompany, int id, double price, LocalDate receiveDate) {
        this.title = title;
        this.trader = trader;
        this.purchaser = purchaser;
        this.purchasersCompany = purchasersCompany;
        this.id = id;
        this.price = price;
        this.receiveDate = receiveDate;
    }



    public Project() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrader() {
        return trader;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    public String getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(String purchaser) {
        this.purchaser = purchaser;
    }

    public String getPurchasersCompany() {
        return purchasersCompany;
    }

    public void setPurchasersCompany(String purchasersCompany) {
        this.purchasersCompany = purchasersCompany;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(LocalDate receiveDate) {
        this.receiveDate = receiveDate;
    }

    public LocalDate getCompletionDate() {
        return implementationDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.implementationDate = completionDate;
    }

    public String toCSV() {
        return id + ";" + title.toUpperCase() +
                ";" + trader +
                ";" + purchaser +
                ";" + purchasersCompany +
                ";" + price +
                ";" + receiveDate +
                ";" + implementationDate;
    }


    @Override
    public String toString() {
        return id + ": Nazwa: " + title.toUpperCase() + ".\n" +
                "Nazwisko i Imię handlowca nadzorującego: " + trader + ". \n" +
                "Nazwisko i Imię klienta: " + purchaser + "\n" +
                "Nazwa firmy klienta: " + purchasersCompany + ". \n" +
                "Kwota realizacji projektu: " + price + "PLN .\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id == project.id &&
                Double.compare(project.price, price) == 0 &&
                Objects.equals(title, project.title) &&
                Objects.equals(trader, project.trader) &&
                Objects.equals(purchaser, project.purchaser) &&
                Objects.equals(purchasersCompany, project.purchasersCompany);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, trader, purchaser, purchasersCompany, id, price);
    }
}
