package com.filters_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

public class ImagePreviewActivity extends AppCompatActivity {

    Toolbar mcontroltoolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_preview);

        mcontroltoolbar = (Toolbar) findViewById(R.id.toolbar);

        mcontroltoolbar.setTitle(getString(R.string.app_name));
        mcontroltoolbar.setNavigationIcon(R.drawable.appicon);
        mcontroltoolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));


    }
}
