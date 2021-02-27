package com.company.projectcatalog.io.file;

import com.company.projectcatalog.exeptions.DataExportExeption;
import com.company.projectcatalog.exeptions.DataImportExeption;
import com.company.projectcatalog.model.ProjectsCatalog;

import java.io.*;

public class SerializableFileManager implements FileManager {
    private static final String FILE_NAME = "Library.o";

    @Override
    public ProjectsCatalog importData() {
        try (FileInputStream fis = new FileInputStream(FILE_NAME);
             ObjectInputStream ois = new ObjectInputStream(fis);
        ) {

            return (ProjectsCatalog) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new DataImportExeption("Brak pliku " + FILE_NAME);
        } catch (IOException e) {
            throw new DataImportExeption("Błąd odczytu pliku: " + FILE_NAME);
        } catch (ClassNotFoundException e) {
            throw new DataImportExeption("Niezgodny typ danych w pliku: " + FILE_NAME);
        }

    }

    @Override
    public void exportData(ProjectsCatalog projectsCatalog) {
        try (FileOutputStream fos = new FileOutputStream(FILE_NAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(projectsCatalog);
        } catch (FileNotFoundException e) {
            throw new DataExportExeption("Brak pliku " + FILE_NAME);
        } catch (IOException e) {
            throw new DataExportExeption("Błąd zapisu danych do pliku: " + FILE_NAME);
        }

    }
}
