package com.company.projectcatalog.model;

import com.company.projectcatalog.exeptions.CustomerAlreadyExistExeption;
import com.company.projectcatalog.exeptions.ProjectAlreadyExistException;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProjectsCatalog implements Serializable {
    private int projectsNumber = 0;

    private Map<String, Project> projects = new HashMap<>();
    private Map<String, Customer> customers = new HashMap<>();

    public Collection<Project> getSortedProjects(Comparator<Project> comparator) {
        ArrayList<Project> list = new ArrayList<>(this.projects.values());
        list.sort(comparator);
        return list;
    }

    public Collection<Customer> getSortedCustomers(Comparator<Customer> comparator) {
        ArrayList<Customer> list = new ArrayList<>(customers.values());
        list.sort(comparator);
        return list;
    }

    public int getProjectsNumber() {
        return projectsNumber;
    }

    public Map<String, Project> getProjects() {
        return projects;
    }

    public Map<String, Customer> getCustomers() {
        return customers;
    }

    public void setCustomer(Map<String, Customer> customer) {
        this.customers = customer;
    }

    public void addProject(Project project) {
        if (projects.containsKey(project.getTitle())) {
            throw new ProjectAlreadyExistException("Projekt o tej nazwie już istnieje. ");
        } else
            projects.put(project.getTitle(), project);
    }

    public void addCustomer(Customer customer) {
        if (customers.containsValue(customer))
            throw new CustomerAlreadyExistExeption("Taki klient znajduje się już w bazie danych. ");
        else
            customers.put(customer.toString(), customer);
    }

    public boolean removeProject(Project project) {
        if (projects.containsValue(project)) {
            projects.remove(project.getTitle());
            return true;
        } else
            return false;
    }

    public ArrayList<Project> findProjectsByTittle(String findingProject) {
        return new ArrayList<>(this.projects.values()).stream()
                .filter(project -> (project.getTitle().toUpperCase()).contains(findingProject))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
