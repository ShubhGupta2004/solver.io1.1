package com.celebrare.greentracker;

public class dataModel {
    private String category;
    private String data;

    public dataModel(String category, String data) {
        this.category = category;
        this.data = data;
    }


    public String getCategory() {
        return category;
    }

    public String getData() {
        return data;
    }
}
