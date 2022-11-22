package com.example.registerform;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText firstname, lastname, birthday, address, email;
    RadioGroup gender;
    RadioButton choose_gender;
    CheckBox term;
    Button register;
    final Calendar birthdayCalendar = Calendar.getInstance();

    private void build() {
        firstname = findViewById(R.id.edit_firstname);
        lastname = findViewById(R.id.edit_lastname);
        birthday = findViewById(R.id.edit_birthday);
        address = findViewById(R.id.edit_address);
        email = findViewById(R.id.edit_email);
        gender = findViewById(R.id.radio_gender);
        term = findViewById(R.id.check_term);
        gender.check(R.id.radio_male);
        choose_gender = findViewById(R.id.radio_male);
        register = findViewById(R.id.button_register);
    }

    private void updateLabel(){
        String myFormat="MM/dd/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        birthday.setText(dateFormat.format(birthdayCalendar.getTime()));
    }

    private void check() {
        if (firstname.getText().toString().trim().equalsIgnoreCase("")) {
            firstname.setError("First name can not be blank");
        }
        if (lastname.getText().toString().trim().equalsIgnoreCase("")) {
            lastname.setError("Last name can not be blank");
        }
        if (birthday.getText().toString().trim().equalsIgnoreCase("")) {
            birthday.setError("Please choose your birthday");
        }
        if (email.getText().toString().trim().equalsIgnoreCase("")) {
            email.setError("Email can not be blank");
        }
        if (address.getText().toString().trim().equalsIgnoreCase("")) {
            address.setError("Address can not be blank");
        }
        if (!term.isChecked()) term.setError("Please check to agree our terms");
        else term.setError(null);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        build();

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                birthdayCalendar.set(Calendar.YEAR, year);
                birthdayCalendar.set(Calendar.MONTH, month);
                birthdayCalendar.set(Calendar.DAY_OF_MONTH, day);
                Log.v("YEAR", Integer.toString(year));
                updateLabel();
            }
        };

        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this,
                        date,
                        birthdayCalendar.get(Calendar.YEAR),
                        birthdayCalendar.get(Calendar.MONTH),
                        birthdayCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check();
            }
        });

    }
}