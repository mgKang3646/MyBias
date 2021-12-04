package com.example.actorsearchapplication.models;

public class UrlModel {

    private static String query;
    private static String page = "1";

    public static String getQuery() {
        return query;
    }
    public static void setQuery(String query) {
        UrlModel.query = query;
    }
    public static String getPage() {
        return page;
    }
    public static void setPage(String page) {
        UrlModel.page = page;
    }
}
