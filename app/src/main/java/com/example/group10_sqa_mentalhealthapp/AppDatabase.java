package com.example.group10_sqa_mentalhealthapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.group10_sqa_mentalhealthapp.journal.JournalEntry;
import com.example.group10_sqa_mentalhealthapp.journal.JournalEntryDao;

/**
 * Room Database class for the application.
 *
 * <p>This class represents the Room Database for the application. It defines the entities
 * and their relationships, as well as provides access to the DAOs (Data Access Objects).
 *
 * @see RoomDatabase
 */
@Database(entities = {JournalEntry.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    /**
     * Get the Data Access Object (DAO) for JournalEntry entities.
     *
     * @return The JournalEntry DAO.
     */
    public abstract JournalEntryDao journalEntryDao();
}
