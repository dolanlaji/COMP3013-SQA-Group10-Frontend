package com.example.group10_sqa_mentalhealthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class DisclaimerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclaimer);
    }

    public void onAgree(View v) {
        setResult(Activity.RESULT_OK);
        finish();
    }
}