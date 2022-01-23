package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;

public class TestDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_dashboard);

        TextView textView = findViewById(R.id.test);

        SessionManager sessionManager = new SessionManager(this);
        HashMap<String,String> userDetails = sessionManager.getUserDetailsFromSession();

        String name = userDetails.get(SessionManager.KEY_NAME);
        String surname = userDetails.get(SessionManager.KEY_SURNAME);
        String email = userDetails.get(SessionManager.KEY_EMAIL);
        String cf = userDetails.get(SessionManager.KEY_CODICEFISCALE);
        String dob = userDetails.get(SessionManager.KEY_DOB);

        textView.setText(name + "\n" + surname + "\n" + email + "\n" + cf + "\n" + dob);

    }
}