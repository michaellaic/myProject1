package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;

public class EventCellActivity extends AppCompatActivity {
    Button eventNameB,timeB , dateB;
    EditText eventNameET , eventLocationET;
    private DatePickerDialog datePickerDialog;
    int hour, minute;
    private LocalTime time;
    private LocalDate dateC;
    private Event event;
    String eventName = event.getName();

    public EventCellActivity(Event event) {
        this.event = event;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_cell);
        initWidgets();
        initDatePicker();
        time = event.getTime();
        dateC = event.getDate();
        dateB.setText(CalendarUtils.formattedDate(dateC));
        timeB.setText(CalendarUtils.formattedShortTime(time));
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
        eventNameB = findViewById(R.id.eventNameB);

    }

    public void saveEventAction(View view) {
        String eventLocation = eventLocationET.getText().toString();
        Event newEvent = new Event(eventName,eventLocation,dateC,time);
        Event.eventsList.remove(event);
        Event.eventsList.add(newEvent);
        event = newEvent;
    }
    public void editName(View view) {
        eventNameB.setVisibility(View.INVISIBLE);
        eventNameET.setVisibility(View.VISIBLE);
        if (!eventNameET.getText().toString().isEmpty()) {
            eventName = eventNameET.getText().toString();
            eventNameB.setText(eventName);
            eventNameB.setVisibility(View.VISIBLE);
            eventNameET.setVisibility(View.INVISIBLE);
        }
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
