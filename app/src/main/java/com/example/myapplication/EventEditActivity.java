package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Locale;

public class EventEditActivity extends AppCompatActivity {
    private EditText eventNameET, eventLocationET;
   // private TextView eventDateTV, eventTimeTV;
    private DatePickerDialog datePickerDialog;
    Button timeB , dateB;
    int hour, minute;
    private LocalTime time;
    private LocalDate dateC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
        initWidgets();
        initDatePicker();
        time = LocalTime.now();
        dateC = CalendarUtils.selectedDate;
        dateB.setText(CalendarUtils.formattedDate(CalendarUtils.selectedDate));
        timeB.setText("Select Time");
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateC = LocalDate.of(year,month,day);
                dateB.setText(date);
            }
        };

        //Calendar cal = Calendar.getInstance();
        int year = CalendarUtils.selectedDate.getYear();
        int month = CalendarUtils.selectedDate.getMonthValue() - 1;
        int day = CalendarUtils.selectedDate.getDayOfMonth();

        //int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }
    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }
    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    private void initWidgets()
    {
        eventNameET = findViewById(R.id.eventNameET);
        timeB = findViewById(R.id.timeB);
        dateB = findViewById(R.id.dateB);
        eventLocationET = findViewById(R.id.eventLocationET);
    }

    public void saveEventAction(View view) {
        String eventName = eventNameET.getText().toString();
        String eventLocation = eventLocationET.getText().toString();
        Event newEvent = new Event(eventName,eventLocation,dateC,time);
        Event.eventsList.add(newEvent);
        finish();
    }

    public void returnEventAction(View view) {
        finish();
    }

    public void popTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
            {
                hour = selectedHour;
                minute = selectedMinute;
                time = LocalTime.of(hour,minute);
                timeB.setText(String.format(Locale.getDefault(), "%02d:%02d",hour, minute));
            }
        };

        // int style = AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, /*style,*/ onTimeSetListener, hour, minute, true);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }
}