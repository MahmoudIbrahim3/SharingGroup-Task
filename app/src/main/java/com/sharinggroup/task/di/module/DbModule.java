package com.sharinggroup.task.di.module;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.sharinggroup.task.data.local.AppDatabase;
import com.sharinggroup.task.data.local.dao.UserDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DbModule {

    /*
     * The method returns the Database object
     * */
    @Provides
    @Singleton
    AppDatabase provideDatabase(@NonNull Application application) {
        return Room.databaseBuilder(application,
                AppDatabase.class, "SharingGroupTask.db")
                .allowMainThreadQueries().build();
    }

    @Provides
    @Singleton
    UserDao provideUserDao(@NonNull AppDatabase appDatabase) {
        return appDatabase.userDao();
    }
}
