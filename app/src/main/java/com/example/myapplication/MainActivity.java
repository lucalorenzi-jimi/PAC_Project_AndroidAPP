package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.0.29:8080/controller/").addConverterFactory(GsonConverterFactory.create()).build();

        CustomerAPI customerAPI = retrofit.create(CustomerAPI.class);

        Call<List<Customer>> call = customerAPI.getCustomers();

        call.enqueue(new Callback<List<Customer>>() {
            @Override
            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {
                if (!response.isSuccessful()){
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Customer> customers = response.body();

                for (Customer customer : customers) {
                    String content ="";
                    content += "CF: " + customer.getCf() + "\n";
                    content += "Name: " + customer.getName() + "\n";
                    content += "Surname: " + customer.getSurname() + "\n";
                    content += "Dob: " + customer.getDob() + "\n";
                    content += "Email: " + customer.getEmail() + "\n";
                    content += "Password: " + customer.getPwd() + "\n\n";

                    textViewResult.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Customer>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}