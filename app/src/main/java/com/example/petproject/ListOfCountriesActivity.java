package com.example.petproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ListOfCountriesActivity extends AppCompatActivity {
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String[] countries = {"Usa", "Ukraine", "Turkey", "Austria", "Czech Republic","France","Spain","Portugal"};

        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_countries);
        arrayAdapter = new ArrayAdapter<>(this, R.layout.list_item, countries);
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteTextView);

        autoCompleteTextView.setAdapter(arrayAdapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedCountry = adapterView.getItemAtPosition(i).toString();
                openCountryDetailActivity(selectedCountry);
            }
        });
    }

    private void openCountryDetailActivity(String selectedCountry) {
        Intent intent = new Intent(ListOfCountriesActivity.this, CountryDetailActivity.class);
        intent.putExtra("country", selectedCountry);
        startActivity(intent);
    }
}