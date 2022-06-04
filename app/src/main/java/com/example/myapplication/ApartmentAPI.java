package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApartmentAPI {

    @POST("controller/research")
    Call<ArrayList<Apartment>> getApartments(@Body SearchBody body);
}
