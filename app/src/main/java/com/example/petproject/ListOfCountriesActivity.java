package com.example.petproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.petproject.updatepart.AllPlaylists;
import com.example.petproject.updatepart.ListOfListsOfNotes;
import com.example.petproject.updatepart.Note;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ListOfCountriesActivity extends AppCompatActivity {
    String country;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        createNotes();
        String[] countries = {"Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua and Barbuda",
                "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain",
                "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia",
                "Bosnia and Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina Faso",
                "Burundi", "Cabo Verde", "Cambodia", "Cameroon", "Canada", "Central African Republic",
                "Chad", "Chile", "China", "Colombia", "Comoros", "Congo", "Costa Rica", "Croatia",
                "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic",
                "East Timor (Timor-Leste)", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea",
                "Estonia", "Eswatini", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia",
                "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana",
                "Haiti", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland",
                "Israel", "Italy", "Ivory Coast", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati",
                "Korea, North", "Korea, South", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon",
                "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Madagascar", "Malawi",
                "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico",
                "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar (Burma)",
                "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria",
                "North Macedonia (formerly Macedonia)", "Norway", "Oman", "Pakistan", "Palau", "Panama",
                "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Romania",
                "Russia", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines",
                "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles",
                "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa",
                "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Sweden", "Switzerland", "Syria", "Taiwan",
                "Tajikistan", "Tanzania", "Thailand", "Togo", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey",
                "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States",
                "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe"
        };

        ArrayAdapter<String> arrayAdapter;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_countries);
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, countries);
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteTextView);

        autoCompleteTextView.setAdapter(arrayAdapter);





        Button button = findViewById(R.id.addCommentButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCountryDetailActivity(country);

            }
        });

        Button buttonPlaylist = findViewById(R.id.listOfAllNotes);
        buttonPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ListOfListsOfNotes.class));
                finish();

            }
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomAppBar);
        bottomNavigationView.setSelectedItemId(R.id.list);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.settings:
                    startActivity(new Intent(getApplicationContext(), SettingActivity.class));
                    overridePendingTransition(R.anim.slide_right, R.anim.slide_left);

                    finish();
                    return true;

                case R.id.map:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(R.anim.slide_left_opposite, R.anim.slide_right_opposite);

                    finish();
                    return true;
                case R.id.list:

                    return true;
            }
            return false;
        });
    }

    private void openCountryDetailActivity(String selectedCountry) {
        Intent intent = new Intent(ListOfCountriesActivity.this, CountryDetailActivity.class);
        intent.putExtra("country", selectedCountry);
        startActivity(intent);
    }
    public void createNotes(){
        Note note1 = new Note(1,"Best trip","Content", "France");
        Note note2 = new Note(2,"Medium Trip","Content", "Germany");
        Note note3 = new Note(3,"Baza","Content", "France");
        Note note4 = new Note(4,"Example","Content", "Ukraine");
        Note note5 = new Note(5,"Love","Content", "Turkey");
        Note note6 = new Note(6,"Rock","Content", "USA");
        Note note7 = new Note(7,"Mission","Content", "France");
//        Note note8 = new Note(8,"Work trip","Content", "Poland");
//        Note note9 = new Note(9,"Studies1","Content", "Lithuania");
//        Note note10 = new Note(10,"Erasmus","Content", "Czech republic");
        Note.noteArrayList.add(note1);
        Note.noteArrayList.add(note2);
        Note.noteArrayList.add(note3);
        Note.noteArrayList.add(note4);
        Note.noteArrayList.add(note5);
        Note.noteArrayList.add(note6);
        Note.noteArrayList.add(note7);

//        Note.noteArrayList.add(note8);
//        Note.noteArrayList.add(note9);
//        Note.noteArrayList.add(note10);
    }
}
