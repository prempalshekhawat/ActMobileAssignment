package com.prempal.actmobileassignment.Models;

public class CountryNamesModel {

    String name;
    String code;

    public CountryNamesModel() {
    }

    public CountryNamesModel(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
