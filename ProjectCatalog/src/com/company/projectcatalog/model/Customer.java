package com.company.projectcatalog.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer extends Person {
    private List<Project> projectsHistory = new ArrayList<>();
    private List<Project> activeProjects = new ArrayList<>();

    public Customer(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public void addProjectToHistory(Project project) {
        projectsHistory.add(project);
    }

    public void addToActiveProject(Project project) {
        activeProjects.add(project);
    }

    public List<Project> getProjectsHistory() {
        return projectsHistory;
    }

    public List<Project> getActiveProjects() {
        return activeProjects;
    }

    public boolean closeProject(Project project) {
        if (activeProjects.contains(project)) {
            activeProjects.remove(project);
            addProjectToHistory(project);
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(projectsHistory, customer.projectsHistory) &&
                Objects.equals(activeProjects, customer.activeProjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), projectsHistory, activeProjects);
    }

    @Override
    public String toCSV() {
        return getFirstName() + ";" + getLastName();
    }

}
