package com.example.harshu195.samplecalendereventlist;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.net.Uri;
import android.provider.CalendarContract;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

public class CalenderActivity extends Activity {
    public Button btnGo,btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        //Using a btn event to launch an Intent of Calender App with some details
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar beginTime = Calendar.getInstance();
                beginTime.set(2015, 5, 12, 17, 30);
                Calendar endTime = Calendar.getInstance();
                endTime.set(2015, 5, 19, 18, 30);
                Intent intent = new Intent(Intent.ACTION_INSERT)
                        .setData(CalendarContract.Events.CONTENT_URI)
                        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                        .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
                        .putExtra(CalendarContract.Events.TITLE, "Walk")
                        .putExtra(CalendarContract.Events.DESCRIPTION, "Go have a 5min walk")
                        .putExtra(CalendarContract.Events.EVENT_LOCATION, "Field");
                startActivity(intent);
            }
        });


        //Using a btn event to launch an Intent of Calender App to view today's tasks.

        btnGo = (Button) findViewById(R.id.btnCal);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long startMillis = System.currentTimeMillis() + 1000 * 60 * 60;
                Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();
                builder.appendPath("time");
                ContentUris.appendId(builder, startMillis);
                Intent intent = new Intent(Intent.ACTION_VIEW)
                        .setData(builder.build());
                startActivity(intent);
            }
        });
    }
}
