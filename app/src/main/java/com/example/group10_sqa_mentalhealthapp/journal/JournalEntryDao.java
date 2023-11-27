package com.example.group10_sqa_mentalhealthapp.journal;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface JournalEntryDao {
    @Insert
    void insert(JournalEntry entry);

    @Query("SELECT * FROM journal_entries")
    LiveData<List<JournalEntry>> getAllEntries();

    @Delete
    void delete(JournalEntry entry);
}

