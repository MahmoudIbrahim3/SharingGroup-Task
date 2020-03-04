package com.sharinggroup.task.ui.userprofile;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sharinggroup.task.data.Resource;
import com.sharinggroup.task.data.local.dao.UserDao;
import com.sharinggroup.task.data.local.entity.UserEntity;
import com.sharinggroup.task.data.remote.api.UsersApiService;
import com.sharinggroup.task.data.repository.UserRepository;

import javax.inject.Inject;

public class UserProfileViewModel extends ViewModel {

    private UserRepository userRepository;

    @Inject
    public UserProfileViewModel(UserDao userDao, UsersApiService usersApiService) {
        userRepository = new UserRepository(userDao, usersApiService);
    }

    private MutableLiveData<Resource<UserEntity>> userProfileLiveData = new MutableLiveData<>();

    public MutableLiveData<Resource<UserEntity>> getUserProfileLiveData() {
        return userProfileLiveData;
    }

    public void loadUserProfile(Integer userId) {
        userRepository.loadUserProfile(userId).subscribe(
                resource -> getUserProfileLiveData().postValue(resource));
    }
}
