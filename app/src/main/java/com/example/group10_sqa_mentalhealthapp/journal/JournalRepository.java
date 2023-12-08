package com.example.group10_sqa_mentalhealthapp.journal;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.group10_sqa_mentalhealthapp.AppDatabase;

import java.util.List;

public class JournalRepository {
    private static JournalRepository instance;
    private final JournalEntryDao dao;

    private JournalRepository(Application application) {
        AppDatabase db = Room.databaseBuilder(application,
                AppDatabase.class, "journal_database").build();
        dao = db.journalEntryDao();
    }

    public static synchronized JournalRepository getInstance(Application application) {
        if (instance == null) {
            instance = new JournalRepository(application);
        }
        return instance;
    }

    LiveData<List<JournalEntry>> getAllEntries() {
        return dao.getAllEntries();
    }

    void addEntry(JournalEntry entry) {
        new Thread(() -> dao.insert(entry)).start();
    }

    void deleteEntry(JournalEntry entry) {
        new Thread(() -> dao.delete(entry)).start();
    }
}