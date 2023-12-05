package com.example.group10_sqa_mentalhealthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
/**
 * Activity displaying a disclaimer to the user.
 *
 * <p>This activity presents a disclaimer to the user, and the user can agree to it by interacting with the UI.
 */
public class DisclaimerActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_disclaimer);
    }

    /**
     * Called when the user clicks the "Agree" button.
     *
     * <p>Sets the result to indicate that the user has agreed to the disclaimer and finishes the activity.
     *
     * @param v The view that was clicked.
     */
    public void onAgree(View v) {
        setResult(Activity.RESULT_OK);
        finish();
    }
}