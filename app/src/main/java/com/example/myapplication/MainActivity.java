package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.util.Pair;
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
import com.google.android.material.datepicker.MaterialDatePicker;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText searchBox;
    TextView moreFilters, numGuests, budgetNotte, numGuestSelector;
    ImageView btnSearch, btnDownArrow, btnUpArrow, removeGuest, addGuest;
    ConstraintLayout constrLayout;
    MaterialToolbar materialToolbar;
    Button loginRedirect, datePicker;
    CheckBox tagMountain, tagSea, tagCity, tagLake, tagMetropoly, tagBB, tagCountryside;
    Spinner budgetSpinner, rangeSpinner;

    MaterialDatePicker materialDatePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getSupportActionBar().hide();

        initViews();

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker.show(getSupportFragmentManager(),"DATE_PICKER");

            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ResultApartmentActivity.class);
                startActivity(intent);
            }
        });

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

        addGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer i = Integer.parseInt(numGuestSelector.getText().toString());
                if (i<20) {
                    i++;
                    numGuestSelector.setText(i.toString());
                }
            }
        });

        removeGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer i = Integer.parseInt(numGuestSelector.getText().toString());
                if (i>1) {
                    i--;
                    numGuestSelector.setText(i.toString());
                }
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
        moreFilters = findViewById(R.id.moreFilters);
        numGuests = findViewById(R.id.numGuests);
        budgetNotte = findViewById(R.id.budgetNotte);
        btnSearch = findViewById(R.id.btnSearch);
        btnDownArrow = findViewById(R.id.btnDownArrow);
        btnUpArrow = findViewById(R.id.btnUpArrow);
        loginRedirect = findViewById(R.id.loginRedirect);
        datePicker = findViewById(R.id.btnDatePicker);
        numGuestSelector = findViewById(R.id.numGuestSelector);
        addGuest = findViewById(R.id.addGuest);
        removeGuest = findViewById(R.id.removeGuest);

        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        materialDatePicker = builder.build();

        budgetSpinner = findViewById(R.id.budgetSpinner);
        ArrayList<String> rangeBudgets = new ArrayList<String>();
        rangeBudgets.add("");
        rangeBudgets.add("0€ ~ 25€");
        rangeBudgets.add("25€ ~ 50€");
        rangeBudgets.add("50€ ~ 75€");
        rangeBudgets.add("75€ ~ 100€");
        rangeBudgets.add("100€+");
        ArrayAdapter<String> numGuestAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,rangeBudgets);
        budgetSpinner.setAdapter(numGuestAdapter);

        rangeSpinner = findViewById(R.id.rangeSpinner);
        ArrayList<String> rangeDistance = new ArrayList<String>();
        rangeDistance.add("");
        rangeDistance.add("< 2.5Km");
        rangeDistance.add("2.5Km ~ 5Km");
        rangeDistance.add("5Km ~ 7.5Km");
        rangeDistance.add("7.5Km ~ 10Km");
        rangeDistance.add("> 10Km");
        ArrayAdapter<String> rangeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,rangeDistance);
        rangeSpinner.setAdapter(rangeAdapter);

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