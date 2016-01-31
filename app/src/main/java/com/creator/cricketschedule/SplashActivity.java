package com.creator.cricketschedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final Intent intent = getIntent();
        final String callClass = intent.getStringExtra("ClassName");
        Thread welcomeThread = new Thread(){
            public void run()
            {
                try{
                    super.run();
                    sleep(5000);
                }
                catch(Exception e)
                {

                }
                finally{
                    if(callClass.equals("SeriesDetailActivity.class"))
                    {
                        Intent i = new Intent(SplashActivity.this,SeriesDetailActivity.class);
                        Log.d("#:#:#::#"," "+intent.getIntExtra(SeriesDetailActivity.EXTRA_SERIESNO,-1));
                        i.putExtra(SeriesDetailActivity.EXTRA_SERIESNO, intent.getIntExtra(SeriesDetailActivity.EXTRA_SERIESNO, -1));
                        startActivity(i);
                        finish();
                    }

                }
            }
        };
        welcomeThread.run();
    }

}
