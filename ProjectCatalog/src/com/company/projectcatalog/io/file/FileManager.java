package com.company.projectcatalog.io.file;

import com.company.projectcatalog.model.ProjectsCatalog;

public interface FileManager {

    ProjectsCatalog importData();

    void exportData(ProjectsCatalog projectsCatalog);
}
