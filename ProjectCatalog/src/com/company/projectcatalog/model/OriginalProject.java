package com.company.projectcatalog.model;

import java.time.LocalDate;
import java.util.Objects;

public class OriginalProject extends Project{
    public static final String TYPE = "Projekt własny";
    private String designer;


    public String toCSV(){
        return TYPE + ";" + super.toCSV() +";"+ designer;
    }

    public OriginalProject(String title, String trader, String purchaser, String purchasersCompany, int id, double price, LocalDate receiveDate, LocalDate implementationDate, String designer) {
        super(title, trader, purchaser, purchasersCompany, id, price, receiveDate, implementationDate);
        this.designer = designer;
    }

    public OriginalProject(String title, String trader, String purchaser, String purchasersCompany, int id, double price, LocalDate receiveDate, String designer) {
        super(title, trader, purchaser, purchasersCompany, id, price, receiveDate);
        this.designer = designer;
    }

    public OriginalProject(String title, String purchaser, String purchasersCompany, int id) {
        this.setTitle(title);
        this.setPurchaser(purchaser);
        this.setPurchasersCompany(purchasersCompany);
        this.setId(id);
    }

    public OriginalProject(String title, String designer, String trader, String purchaser, String purchasersCompany, int id, double price) {
        this(title, purchaser, purchasersCompany, id);
        this.setTrader(trader);
        this.setPrice(price);
        this.designer = designer;
    }

    public String getDesigner() {
        return designer;
    }

    public void setDesigner(String designer) {
        this.designer = designer;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Nazwisko i Imię projektanta wykonującego: " + designer+ "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OriginalProject that = (OriginalProject) o;
        return Objects.equals(designer, that.designer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), designer);
    }
}
