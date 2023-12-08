package com.example.group10_sqa_mentalhealthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class GoalCreation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_creation);
    }

    public void onGoalCreateClick(View v) {
        EditText editText = (EditText) findViewById(R.id.edit_goal_title);
        String goalTitle = editText.getText().toString();

        // todo: input parsing

        Bundle bundle = new Bundle();
        bundle.putString("goalTitle", goalTitle);

        Intent intent = new Intent();
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }
}