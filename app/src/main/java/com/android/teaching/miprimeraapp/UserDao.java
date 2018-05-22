package com.android.teaching.miprimeraapp;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user WHERE username IS :username")
    User findUserByUsername(String username);

    @Insert
    void insert(User user);

    @Delete
    void delete(User user);
}
