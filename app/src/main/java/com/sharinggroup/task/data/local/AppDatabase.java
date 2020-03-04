package com.sharinggroup.task.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.sharinggroup.task.data.local.converter.AddressTypeConverter;
import com.sharinggroup.task.data.local.converter.CompanyTypeConverter;
import com.sharinggroup.task.data.local.converter.GeoTypeConverter;
import com.sharinggroup.task.data.local.dao.UserDao;
import com.sharinggroup.task.data.local.entity.UserEntity;

@Database(entities = {UserEntity.class}, version = 1, exportSchema = false)
@TypeConverters({AddressTypeConverter.class, GeoTypeConverter.class, CompanyTypeConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
}
