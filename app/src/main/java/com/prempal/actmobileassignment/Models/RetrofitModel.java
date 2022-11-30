package com.prempal.actmobileassignment.Models;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RetrofitModel {
    private int code;
    private ArrayList<CountryNamesModel> result;

    public RetrofitModel() {
    }

    public RetrofitModel(int code, ArrayList<CountryNamesModel> result) {
        this.code = code;
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ArrayList<CountryNamesModel> getResult() {
        return result;
    }

    public void setResult(ArrayList<CountryNamesModel> result) {
        this.result = result;
    }
}
