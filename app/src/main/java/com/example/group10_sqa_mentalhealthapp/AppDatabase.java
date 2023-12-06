package com.example.group10_sqa_mentalhealthapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.group10_sqa_mentalhealthapp.journal.JournalEntry;
import com.example.group10_sqa_mentalhealthapp.journal.JournalEntryDao;
import com.example.group10_sqa_mentalhealthapp.user.UserEntry;
import com.example.group10_sqa_mentalhealthapp.user.UserEntryDao;

/**
 * Room Database class for the application.
 *
 * <p>This class represents the Room Database for the application. It defines the entities
 * and their relationships, as well as provides access to the DAOs (Data Access Objects).
 *
 * Ensure anytime you make a change you increase the version by one, then add a migration.
 *
 * @see RoomDatabase
 */
@Database(entities = {JournalEntry.class, UserEntry.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase database;
    private static final String databaseName = "clayton-db";

    public abstract JournalEntryDao journalEntryDao();
    public abstract UserEntryDao userEntryDao();

    public static synchronized AppDatabase getDatabase(Context context)
    {
        if (database == null) {
            database = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, databaseName)
                    .addMigrations(/* Our migrations */)
                    .build();
        }
        return database;
    }
}
