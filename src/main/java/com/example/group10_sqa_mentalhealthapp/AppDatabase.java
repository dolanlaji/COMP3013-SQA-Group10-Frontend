package com.example.group10_sqa_mentalhealthapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.group10_sqa_mentalhealthapp.journal.JournalEntry;
import com.example.group10_sqa_mentalhealthapp.journal.JournalEntryDao;

@Database(entities = {JournalEntry.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract JournalEntryDao journalEntryDao();
}
