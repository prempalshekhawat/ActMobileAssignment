package com.prempal.actmobileassignment.Retrofit;

import com.prempal.actmobileassignment.Models.CountryNamesModel;
import com.prempal.actmobileassignment.Models.RetrofitModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountryNamesApi {
//    String BASE_URL = "https://api.printful.com/";
    @GET("countries")
    Call<RetrofitModel> getCountryNames();
}
