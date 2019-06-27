package com.example.matt.buskett;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class ProfileActivity extends AppCompatActivity {
    ImageView profilePic;
    EditText Fname, Sname;
    Spinner regionSpinner;
    Button upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profilePic = findViewById(R.id.profilePic);
        Fname = findViewById(R.id.Fname);
        Sname = findViewById(R.id.Sname);

        regionSpinner = findViewById(R.id.locationSpinner);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.regions, R.layout.support_simple_spinner_dropdown_item);
        regionSpinner.setAdapter(arrayAdapter);

        upload = findViewById(R.id.uploadBtn);
    }
}
