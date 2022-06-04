package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.util.Pair;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

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
    ApartmentAPI apartmentAPI;
    String prenotationStart;
    String prenotationEnd;

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
                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Pair<Long,Long>>() {
                    @Override
                    public void onPositiveButtonClick(Pair<Long,Long> selection) {

                        TimeZone timeZoneUTC = TimeZone.getDefault();
                        // It will be negative, so that's the -1
                        int offsetFromUTC = timeZoneUTC.getOffset(new Date().getTime()) * -1;
                        // Create a date format, then a date object with our offset
                        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ITALY);
                        Date startDate = new Date(selection.first + offsetFromUTC);
                        Date endDate = new Date(selection.second + offsetFromUTC);

                        prenotationStart = simpleFormat.format(startDate);
                        prenotationEnd = simpleFormat.format(endDate);

                    }
                });
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                searchForApartment();

                //Intent intent = new Intent(MainActivity.this, ResultSearchActivity.class);
                //startActivity(intent);
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
                if (i>0) {
                    i--;
                    numGuestSelector.setText(i.toString());
                }
            }
        });

        /*loginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });*/


    }

    public void initViews(){
        searchBox = findViewById(R.id.searchBox);
        moreFilters = findViewById(R.id.moreFilters);
        numGuests = findViewById(R.id.numGuests);
        budgetNotte = findViewById(R.id.budgetNotte);
        btnSearch = findViewById(R.id.btnSearch);
        btnDownArrow = findViewById(R.id.btnDownArrow);
        btnUpArrow = findViewById(R.id.btnUpArrow);
        //loginRedirect = findViewById(R.id.loginRedirect);
        datePicker = findViewById(R.id.btnDatePicker);
        numGuestSelector = findViewById(R.id.numGuestSelector);
        addGuest = findViewById(R.id.addGuest);
        removeGuest = findViewById(R.id.removeGuest);

        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        materialDatePicker = builder.build();

        budgetSpinner = findViewById(R.id.budgetSpinner);
        ArrayList<String> rangeBudgets = new ArrayList<String>();
        rangeBudgets.add("");
        rangeBudgets.add("< 25€");
        rangeBudgets.add("< 50€");
        rangeBudgets.add("< 75€");
        rangeBudgets.add("< 100€");
        rangeBudgets.add("100€+");
        ArrayAdapter<String> numGuestAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,rangeBudgets);
        budgetSpinner.setAdapter(numGuestAdapter);

        rangeSpinner = findViewById(R.id.rangeSpinner);
        ArrayList<String> rangeDistance = new ArrayList<String>();
        rangeDistance.add("");
        rangeDistance.add("< 2.5Km");
        rangeDistance.add("< 5Km");
        rangeDistance.add("< 7.5Km");
        rangeDistance.add("< 10Km");
        rangeDistance.add("10Km+");
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

    private void searchForApartment() {

        SearchBody searchBody = new SearchBody(Integer.parseInt(numGuestSelector.getText().toString()),
                searchBox.getText().toString(),rangeConverter(rangeSpinner.getSelectedItem().toString()),
                tagsConverter(),priceConverter(budgetSpinner.getSelectedItem().toString()),prenotationStart,prenotationEnd);

        /*Gson gson = new Gson();

        Toast.makeText(MainActivity.this, gson.toJson(searchBody), Toast.LENGTH_LONG).show();*/

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.0.10:8080/").addConverterFactory(GsonConverterFactory.create()).build();

        apartmentAPI = retrofit.create(ApartmentAPI.class);

        apartmentAPI.getApartments(searchBody).enqueue(new Callback<ArrayList<Apartment>>() {
            @Override
            public void onResponse(Call<ArrayList<Apartment>> call, Response<ArrayList<Apartment>> response) {
                if (response.body()==null || !response.isSuccessful() || response.body().size()==0 ) {
                    Toast.makeText(MainActivity.this, "No apartments found.", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, ResultSearchActivity.class);
                    ArrayList<Apartment> result = response.body();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("Result search",result);
                    bundle.putString("Start Date", prenotationStart);
                    bundle.putString("End Date", prenotationEnd);
                    intent.putExtras(bundle);
                    //intent.putExtra("Start Date", prenotationStart);
                    //intent.putExtra("End Date", prenotationEnd);
                    startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Apartment>> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, "Error contacting server, please retry.", Toast.LENGTH_LONG).show();
            }
        });


    }

    public double rangeConverter(String range) {
        double result;
        switch (range) {
            case "< 2.5Km":
                result = 2500;
                break;

            case "< 5Km":
                result = 5000;
                break;

            case "< 7.5Km":
                result = 7500;
                break;

            case "< 10Km":
                result = 10000;
                break;

            default:
                result = 30000;
        }
        return result;

    }

    public String tagsConverter() {
        String result="";

        if (tagMountain.isChecked())
            result += "Mountain-";

        if (tagSea.isChecked())
            result += "Sea-";

        if (tagCity.isChecked())
            result += "City-";

        if (tagLake.isChecked())
            result += "Lake-";

        if (tagBB.isChecked())
            result += "BB-";

        if (tagMetropoly.isChecked())
            result += "Metropoly-";

        if (tagCountryside.isChecked())
            result += "Countryside-";

        if (result.endsWith("-"))
            result = result.substring(0,result.length()-1);

        return result;
    }

    public double priceConverter(String price){
        double result;
        switch (price) {
            case "< 25€":
                result = 25;
                break;

            case "< 50€":
                result = 50;
                break;

            case "< 75€":
                result = 75;
                break;

            case "< 100€":
                result = 100;
                break;

            default:
                result = -1;
        }
        return result;

    }


}