package com.sharinggroup.task.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.sharinggroup.task.di.ViewModelFactory;
import com.sharinggroup.task.di.ViewModelKey;
import com.sharinggroup.task.ui.userprofile.UserProfileViewModel;
import com.sharinggroup.task.ui.userslist.UsersListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(UsersListViewModel.class)
    protected abstract ViewModel usersListViewModel(UsersListViewModel userListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(UserProfileViewModel.class)
    protected abstract ViewModel userProfileViewModel(UserProfileViewModel userProfileViewModel);
}
