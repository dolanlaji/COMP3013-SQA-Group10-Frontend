package com.example.group10_sqa_mentalhealthapp.journal;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.group10_sqa_mentalhealthapp.Converters;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity(tableName = "journal_entries")
@TypeConverters({Converters.class})
public class JournalEntry implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "content")
    public String content;

    @ColumnInfo(name = "timestamp")
    public Date localDateTime;
}
