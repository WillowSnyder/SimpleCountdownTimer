package com.gmail.wyatt.james.snyder.simplecountdowntimer;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class editDeleteEvent extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    ArrayList<Event> events;
    private int itemPos, year, month, day;
    private Event currEvent;
    private EditText setDate;
    private EditText eventName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_delete_event);
        loadEvents();


        Intent intent = getIntent();

        itemPos = intent.getIntExtra("itemPosition", -1);
        setDate = findViewById(R.id.pickDate);
        eventName = findViewById(R.id.eventName);
        currEvent = events.get(itemPos);

        eventName.setText(currEvent.getName());
        setDate.setText(currEvent.getDate());
        year = currEvent.getYear();
        month = currEvent.getMonth();
        day = currEvent.getDay();
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

    public void showDatePickerDialog(View view){
        DialogFragment dateDialog = new DatePickerFragment();
        dateDialog.show(getSupportFragmentManager(), "datePicker");
    }

    public void deleteEvent(View view){
        events.remove(itemPos);
        saveEvent();

        Intent intent = new Intent(editDeleteEvent.this, MainActivity.class);
        startActivity(intent);
    }

    public void editEvent(View view){
        currEvent.setDay(this.day);
        currEvent.setMonth(this.month);
        currEvent.setYear(this.year);
        currEvent.setName(eventName.getText().toString());

        saveEvent();

        Intent intent = new Intent(editDeleteEvent.this, MainActivity.class);
        startActivity(intent);
    }

    private void saveEvent(){
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
