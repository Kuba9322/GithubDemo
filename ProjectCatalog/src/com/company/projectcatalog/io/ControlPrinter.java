package com.company.projectcatalog.io;

import com.company.projectcatalog.model.*;

import java.util.Collection;
import java.util.Objects;

public class ControlPrinter {

    public void printOriginalProjects(Collection<Project> projects) {
        long countOriginalProjects = projects.stream()
                .filter(p -> p instanceof OriginalProject)
                .map(p -> p.toString())
                .peek(ControlPrinter::printLine)
                .count();
        if (countOriginalProjects == 0) {
            printLine("Brak projektów wykonanych przez biuro w bazie.");
        }
    }

    public void printSubstitutionProjects(Collection<Project> projects) {
        long countSubstitutionProjects = projects.stream()
                .filter(p -> p instanceof SubstitutionProject)
                .map(p -> p.toString())
                .peek(ControlPrinter::printLine)
                .count();
        if (countSubstitutionProjects == 0) {
            printLine("Brak projektów zamiennych w bazie.");
        }
    }

    public void printProjects(Collection<Project> projects) {
        long countProjects = projects.stream()
                .filter(p -> p.getTitle() != null)
                .map(Project::toString)
                .peek(ControlPrinter::printLine)
                .count();
        if (countProjects == 0) {
            printLine("Brak projektów o podanej nazwie.");
        }
    }


    public static void printLine(String text) {
        System.out.println(text);
    }

    public void printCustomers(Collection<Customer> customers) {
        for (Customer customer : customers) {
            printLine(customer.toString());
        }
    }
}
