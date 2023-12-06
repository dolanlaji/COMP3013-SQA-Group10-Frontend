package com.example.group10_sqa_mentalhealthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
/**
 * Activity for creating a new goal.
 *
 * <p>This activity allows the user to input a title for a new goal and sends the title back to the calling activity.
 */
public class GoalCreation extends AppCompatActivity {
    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     *                           this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     *                           Otherwise, it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_creation);
    }
    /**
     * Called when the "Create Goal" button is clicked.
     *
     * <p>Parses the input goal title, creates an intent with the title, and sets the result for the calling activity.
     *
     * @param v The view that was clicked.
     */
    public void onGoalCreateClick(View v) {
        // Find the EditText for the goal title
        EditText editText = (EditText) findViewById(R.id.edit_goal_title);
        // Get the goal title from the EditText
        String goalTitle = editText.getText().toString();

        // todo: input parsing

        // Create a bundle to pass data back to the calling activity
        Bundle bundle = new Bundle();
        bundle.putString("goalTitle", goalTitle);
        // Create an intent and put the bundle in it
        Intent intent = new Intent();
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }
}