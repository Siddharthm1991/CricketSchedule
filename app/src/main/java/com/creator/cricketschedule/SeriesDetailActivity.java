package com.creator.cricketschedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class SeriesDetailActivity extends AppCompatActivity {

    public static final String  EXTRA_SERIESNO = "seriesNo";
    public String[] matchesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int j=0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_detail);
        Intent intent = getIntent();
        int id = intent.getIntExtra(EXTRA_SERIESNO, -1);
        ScheduleData scheduleData = new ScheduleData();
        String[] seriesList = scheduleData.getSeriesList();
        String selectedSeriesName = seriesList[id];
        String[] matchesList = scheduleData.getMatchDataForSeries(selectedSeriesName);
        ArrayAdapter<String> seriesListDetails = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                matchesList);

        ListView listView = (ListView) findViewById(R.id.seriesDetailList);
        listView.setAdapter(seriesListDetails);
    }

}
