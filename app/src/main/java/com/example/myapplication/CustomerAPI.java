package com.example.myapplication;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CustomerAPI {

    @GET("controller/customer")
    Call<List<Customer>> getCustomers();

    @GET("controller/customer/{email}")
    Call<Customer> getCustomer(@Path("email") String email);

    @POST("login")
    Call<ResponseBody> loginCustomer(@Body RequestBody body);

    @POST("controller/registration")
    Call<ResponseBody> registerCustomer(@Body Customer customer);

}
