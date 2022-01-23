package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText searchBox, budgetNotteSelector;
    TextView moreFilters, numGuests, budgetNotte;
    ImageView btnSearch, btnDownArrow, btnUpArrow;
    Spinner numGuestSpinner;
    ConstraintLayout constrLayout;
    MaterialToolbar materialToolbar;
    Button loginRedirect;
    CheckBox tagMountain, tagSea, tagCity, tagLake, tagMetropoly, tagBB, tagCountryside;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getSupportActionBar().hide();

        initViews();

        btnDownArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                constrLayout.setVisibility(View.VISIBLE);
                btnUpArrow.setVisibility(View.VISIBLE);
                btnDownArrow.setVisibility(View.GONE);
            }
        });

        btnUpArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                constrLayout.setVisibility(View.GONE);
                btnDownArrow.setVisibility(View.VISIBLE);
                btnUpArrow.setVisibility(View.GONE);
            }
        });

        loginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }

    public void initViews(){
        searchBox = findViewById(R.id.searchBox);
        budgetNotteSelector = findViewById(R.id.budgetNotteSelector);
        moreFilters = findViewById(R.id.moreFilters);
        numGuests = findViewById(R.id.numGuests);
        budgetNotte = findViewById(R.id.budgetNotte);
        btnSearch = findViewById(R.id.btnSearch);
        btnDownArrow = findViewById(R.id.btnDownArrow);
        btnUpArrow = findViewById(R.id.btnUpArrow);
        loginRedirect = findViewById(R.id.loginRedirect);

        numGuestSpinner = findViewById(R.id.numGuestSpinner);
        ArrayList<Integer> numGuest = new ArrayList<Integer>();
        for (int x = 0; x<=15; x++) {
            numGuest.add(x);
        }
        ArrayAdapter<Integer> numGuestAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,numGuest);
        numGuestSpinner.setAdapter(numGuestAdapter);

        tagMountain = findViewById(R.id.tagMountain);
        tagSea = findViewById(R.id.tagSea);
        tagCity = findViewById(R.id.tagCity);
        tagLake = findViewById(R.id.tagLake);
        tagBB = findViewById(R.id.tagBB);
        tagMetropoly = findViewById(R.id.tagMetropoly);
        tagCountryside = findViewById(R.id.tagCountryside);
        constrLayout = findViewById(R.id.moreFilters2);
        materialToolbar = findViewById(R.id.toolbar);


        /*tagList = findViewById(R.id.tagsList);
        ArrayList<String> tags = new ArrayList<>();
        tags.add("Mountain");
        tags.add("Sea");
        tags.add("City");
        tags.add("Lake");
        tags.add("B&B");
        tags.add("Metropoly");
        tags.add("City");
        tags.add("Tropical");
        tags.add("Porco dio");

        tagList = findViewById(R.id.listTags);
        String[] tags = {"Mountain","Sea","City", "Lake", "B&B", "Metropoly", "City","Tropical"};
        ArrayAdapter<String> tagsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, tags);
        tagList.setAdapter(tagsAdapter);*/
    }


}