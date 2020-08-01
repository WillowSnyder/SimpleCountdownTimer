package com.gmail.wyatt.james.snyder.simplecountdowntimer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class addEvent extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private EditText setDate;
    final Calendar c = Calendar.getInstance();
    private ArrayList<Event> events;
    private EditText eventName;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        setDate = findViewById(R.id.pickDate);

        Calendar c = Calendar.getInstance();

        String currentDateString = DateFormat.getDateInstance().format(c.getTime());
        setDate.setText(currentDateString);

        this.year = c.get(Calendar.YEAR);
        this.month = c.get(Calendar.MONTH);
        this.day = c.get(Calendar.DAY_OF_MONTH);

        eventName = findViewById(R.id.eventName);
    }

    public void showDatePickerDialog(View view){
        DialogFragment dateDialog = new DatePickerFragment();
        dateDialog.show(getSupportFragmentManager(), "datePicker");
    }

    public void confirmEvent(View view){
        saveEvent();
        startActivity(new Intent(addEvent.this, MainActivity.class));
    }

    private void loadEvents(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Events", null);
        Type type = new TypeToken<ArrayList<Event>>() {}.getType();
        events = gson.fromJson(json, type);

        if(events == null){
            events = new ArrayList<>();
        }
    }

    private void saveEvent(){
        loadEvents();

        Event currentEvent = new Event(eventName.getText().toString(), this.year, this.month, this.day);
        events.add(currentEvent);

        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(events);
        editor.putString("Events", json);
        editor.apply();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        this.year = year;
        this.month = month;
        this.day = dayOfMonth;

        String currentDateString = DateFormat.getDateInstance().format(c.getTime());
        setDate.setText(currentDateString);
    }
}


