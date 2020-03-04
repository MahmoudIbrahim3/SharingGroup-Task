package com.sharinggroup.task.di.module;

import com.sharinggroup.task.ui.userslist.UsersListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract UsersListFragment contributeUsersListFragment();
}
