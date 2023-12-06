package com.example.group10_sqa_mentalhealthapp.user;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_entry")
public class UserEntry {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "handle")
    public String handle;

    @ColumnInfo(name = "pet_name")
    public String petName;

    @ColumnInfo(name = "currency")
    public long currency;

    @ColumnInfo(name = "xp")
    public long xp;
}
