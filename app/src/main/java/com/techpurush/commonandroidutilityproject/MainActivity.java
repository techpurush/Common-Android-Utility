package com.techpurush.commonandroidutilityproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.techpurush.commonandroidutility.LinkUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinkUtils.viewInGooglePlay(this,"");

    }
}