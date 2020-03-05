package com.sharinggroup.task.ui.userslist;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sharinggroup.task.data.Resource;
import com.sharinggroup.task.data.local.dao.UserDao;
import com.sharinggroup.task.data.local.entity.UserEntity;
import com.sharinggroup.task.data.remote.api.UsersApiService;
import com.sharinggroup.task.data.repository.UserRepository;
import com.sharinggroup.task.ui.base.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

public class UsersListViewModel extends BaseViewModel {

    private UserRepository userRepository;

    @Inject
    public UsersListViewModel(UserDao userDao, UsersApiService usersApiService) {
        userRepository = new UserRepository(userDao, usersApiService);
    }

    private MutableLiveData<Resource<List<UserEntity>>> usersLivaData = new MutableLiveData<>();

    public MutableLiveData<Resource<List<UserEntity>>> getUsersLivaData() {
        return usersLivaData;
    }

    public void loadUsers() {
        userRepository.loadUsers().subscribe(
                resource -> getUsersLivaData().postValue(resource),
                error -> getUsersLivaData().postValue(null));
    }
}
