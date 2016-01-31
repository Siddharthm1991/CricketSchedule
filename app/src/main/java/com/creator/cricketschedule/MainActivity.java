package com.creator.cricketschedule;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

    public String[] seriesList;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        ScheduleData scheduleData = new ScheduleData();
        seriesList = scheduleData.getSeriesList();
        seriesList = scheduleData.onlySeriesList;

        ArrayAdapter<String> seriesListAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                seriesList);
        ListView seriesListView = (ListView) findViewById(R.id.seriesList);
        seriesListView.setAdapter(seriesListAdapter);

        AdapterView.OnItemClickListener seriesClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,SeriesDetailActivity.class);
                intent.putExtra(SeriesDetailActivity.EXTRA_SERIESNO, (int)id);
                startActivity(intent);
                /*Intent intent = new Intent(MainActivity.this,SplashActivity.class);
                intent.putExtra(SeriesDetailActivity.EXTRA_SERIESNO, (int)id);
                intent.putExtra("ClassName","SeriesDetailActivity.class");
                startActivity(intent);*/
            }
        };

        seriesListView.setOnItemClickListener(seriesClickListener);
            //String srsName = e.getAttribute("info");
            //Log.d("#Series Name : ",srsName.split(",")[1]);
    }


}
