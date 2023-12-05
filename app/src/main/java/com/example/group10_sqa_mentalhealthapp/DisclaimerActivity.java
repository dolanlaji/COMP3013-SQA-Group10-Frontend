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

public class DisclaimerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclaimer);
    }

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
