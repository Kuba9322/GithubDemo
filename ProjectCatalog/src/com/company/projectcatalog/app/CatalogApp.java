package com.company.projectcatalog.app;

public class CatalogApp {
    private static final String APP_NAME = "Katalog projektów v1.8.2";

    public static void main(String[] args) {
        System.out.println(APP_NAME);

        CatalogControl catalogControl = new CatalogControl();
        catalogControl.controlLoop();
    }
}
