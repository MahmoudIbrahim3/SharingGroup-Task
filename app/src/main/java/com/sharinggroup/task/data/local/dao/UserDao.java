package com.sharinggroup.task.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.sharinggroup.task.data.local.entity.UserEntity;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertUsers(List<UserEntity> users);

    @Query("SELECT * FROM `UserEntity`")
    List<UserEntity> getUsers();
}
