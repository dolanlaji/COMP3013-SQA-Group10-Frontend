package com.example.group10_sqa_mentalhealthapp.user;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.group10_sqa_mentalhealthapp.AppDatabase;

public class UserRepository {
    private static UserRepository instance;
    private final UserEntryDao dao;

    private UserRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application.getApplicationContext());
        dao = db.userEntryDao();
    }

    public static synchronized UserRepository getInstance(Application application) {
        if (instance == null) {
            instance = new UserRepository(application);
        }
        return instance;
    }

    // Retrieve user data
    public LiveData<UserEntry> getUser() {
        return dao.getUser();
    }

    // Update user data
    public void updateUser(UserEntry user) {
        new Thread(() -> dao.updateUser(user)).start();
    }

    // Insert user data
    public void insertUser(UserEntry user) {
        new Thread(() -> dao.insertUser(user)).start();
    }

    // Delete user data
    public void deleteUser(UserEntry user) {
        new Thread(() -> dao.deleteUser(user)).start();
    }
}
