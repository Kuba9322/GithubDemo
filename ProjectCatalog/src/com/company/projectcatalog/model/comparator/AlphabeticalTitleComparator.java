package com.company.projectcatalog.model.comparator;

import com.company.projectcatalog.model.Project;

import java.util.Comparator;

public class AlphabeticalTitleComparator implements Comparator<Project> {

    @Override
    public int compare(Project p1, Project p2) {
        if (p1 == null && p2 == null)
            return 0;
        else if (p1 == null)
            return 1;
        else if (p2 == null)
            return -1;
        return p1.getTitle().compareToIgnoreCase(p2.getTitle());
    }
}

