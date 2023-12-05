package com.example.group10_sqa_mentalhealthapp.user;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserEntryDao {
    @Query("SELECT * FROM user_entry LIMIT 1")
    LiveData<UserEntry> getUser();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(UserEntry user);

    @Update
    void updateUser(UserEntry user);

    @Delete
    void deleteUser(UserEntry user);
}
