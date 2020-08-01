package com.gmail.wyatt.james.snyder.simplecountdowntimer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class addEvent extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    public EditText setDate;
    final Calendar c = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        setDate = findViewById(R.id.pickDate);

        Calendar c = Calendar.getInstance();

        String currentDateString = DateFormat.getDateInstance().format(c.getTime());
        setDate.setText(currentDateString);
    }

    public void showDatePickerDialog(View view){
        DialogFragment dateDialog = new DatePickerFragment();
        dateDialog.show(getSupportFragmentManager(), "datePicker");
    }

    public void confirmEvent(View view){
        startActivity(new Intent(addEvent.this, MainActivity.class));
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String currentDateString = DateFormat.getDateInstance().format(c.getTime());
        setDate.setText(currentDateString);
    }
}


