package com.example.group10_sqa_mentalhealthapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.example.group10_sqa_mentalhealthapp.user.UserEntry;
import com.example.group10_sqa_mentalhealthapp.user.UserRepository;

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
        setupUserObject();
        finish();
    }

    // todo: where should this be moved to?
    private void setupUserObject() {
        UserRepository userRepository = UserRepository.getInstance(getApplication());
        LiveData<UserEntry> userLiveData = userRepository.getUser();

        userLiveData.observe(this, entry -> {
            if (entry == null) {
                Log.i("UserObject", "Setting up a new user object.");
                // Assign defaults
                UserEntry newUser = new UserEntry();
                newUser.handle = "TestHandle";
                newUser.xp = 0;
                newUser.currency = 0;
                newUser.petName = "Clayton";
                // Insert
                userRepository.insertUser(newUser);
            } else {
                Log.i("UserObject", String.format("User object found! Handle: %s", entry.handle));
            }
        });
    }

}
