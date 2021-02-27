package com.company.projectcatalog.io.file;

import com.company.projectcatalog.exeptions.NoSuchFileTypeExeption;
import com.company.projectcatalog.io.ControlPrinter;
import com.company.projectcatalog.io.DataReader;

public class FileManagerBuilder {
    private ControlPrinter printer;
    private DataReader reader;

    public FileManagerBuilder(ControlPrinter printer, DataReader reader) {
        this.printer = printer;
        this.reader = reader;
    }
    public FileManager build(){
        printer.printLine("Wybierz format danych: ");
        FileType fileType = getFileType();
        switch (fileType){
            case SERIAL:
                return new SerializableFileManager();
            case CSV:
                return new CsvFileManager();
            default:
                throw new NoSuchFileTypeExeption("Nieobsługiwany typ danych.");
        }
    }

    private FileType getFileType() {
        boolean typeOk = false;
        FileType result = null;
        do {
            printTypes();
            String type = reader.getString().toUpperCase();
            try{
                result = FileType.valueOf(type);
                typeOk = true;
            }catch (IllegalArgumentException e){
                printer.printLine("Nieobsługiwany typ danych. Wybierz ponownie:");
            }
        }while (!typeOk);
        return result;
        }

    private void printTypes() {
        for (FileType value : FileType.values()) {
            printer.printLine(value.name());
        }
    }
}

