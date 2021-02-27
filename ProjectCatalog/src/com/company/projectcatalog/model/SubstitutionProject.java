package com.company.projectcatalog.model;

import java.time.LocalDate;
import java.util.Objects;

public class SubstitutionProject extends Project{
    String bidder;
    public static final String TYPE = "Projekt zamienny";

    public String toCSV(){
        return TYPE + ";" + super.toCSV() +";"+ bidder;
    }

    public SubstitutionProject(String title, String trader, String purchaser, String purchasersCompany, int id, double price, LocalDate receiveDate, LocalDate implementationDate, String bidder) {
        super(title, trader, purchaser, purchasersCompany, id, price, receiveDate, implementationDate);
        this.bidder = bidder;
    }

    public SubstitutionProject(String title, String trader, String purchaser, String purchasersCompany, int id, double price, LocalDate receiveDate, String bidder) {
        super(title, trader, purchaser, purchasersCompany, id, price, receiveDate);
        this.bidder = bidder;
    }

    public SubstitutionProject(String title, String purchaser, String purchasersCompany, int id) {
        this.setTitle(title);
        this.setPurchaser(purchaser);
        this.setPurchasersCompany(purchasersCompany);
        this.setId(id);
    }


    public SubstitutionProject(String title, String oferent, String trader, String purchaser, String purchasersCompany, int id, double price) {
        this(title, purchaser, purchasersCompany, id);
        this.setTrader(trader);
        this.setPrice(price);
        this.bidder = oferent;
    }

    public String getBidder() {
        return bidder;
    }

    public void setBidder(String bidder) {
        this.bidder = bidder;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Nazwisko i Imię oferenta wykonującego: + " + bidder + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SubstitutionProject that = (SubstitutionProject) o;
        return Objects.equals(bidder, that.bidder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bidder);
    }
}
