package com.example.myapplication.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Classes.Apartment;
import com.example.myapplication.Classes.MultipleApartmentsStructure;
import com.example.myapplication.R;
import com.example.myapplication.RecyclerView.ApartmentRecViewAdapter;
import com.example.myapplication.RecyclerView.MultipleApartmentRecViewAdapter;

import java.util.ArrayList;

public class ResultSplitSearchActivity extends AppCompatActivity {

    private RecyclerView apartmentRecView;
    private MultipleApartmentRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        adapter = new MultipleApartmentRecViewAdapter(this);
        apartmentRecView = findViewById(R.id.apartmentRecView);
        apartmentRecView.setAdapter(adapter);

        apartmentRecView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<MultipleApartmentsStructure> apartments;
        Bundle bundle = getIntent().getExtras();
        apartments = (ArrayList<MultipleApartmentsStructure>) bundle.getSerializable("Result search");
        //apartments.add(new Apartment("Allegro rifugio", "Serina, via Marconi, 11", "https://img3.idealista.it/blur/WEB_LISTING-M/0/id.pro.it.image.master/4c/9a/0e/137754757.jpg", 30, 5));
        //apartments.add(new Apartment("Ritrovo del vicolo", "Milano, piazza Maggiore, 7a", "https://www.coqtailmilano.com/wp-content/uploads/2020/02/cocktail-bar-in-porta-roma-The-Spirit-Coqtail-Milano-1155x770.jpg", 45, 7));
        //apartments.add(new Apartment("Silenzio marittimo", "Santa maria di leuca, via San Gennaro, 3", "https://cdn2.hometogo.net/small/v1/c78/e25/6045c1fe7c0144ea3e31a3abdf.jpg", 15, 4));

        adapter.setApartments(apartments);
        adapter.setStartDate(bundle.getString("Start Date"));
        adapter.setEndDate(bundle.getString("End Date"));


    }
}