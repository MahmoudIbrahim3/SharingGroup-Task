package com.sharinggroup.task.ui.userprofile;

import androidx.lifecycle.MutableLiveData;

import com.sharinggroup.task.data.Resource;
import com.sharinggroup.task.data.local.dao.UserDao;
import com.sharinggroup.task.data.remote.model.UserProfileApiResponse;
import com.sharinggroup.task.data.remote.api.UsersApiService;
import com.sharinggroup.task.data.repository.UserRepository;
import com.sharinggroup.task.ui.base.BaseViewModel;

import javax.inject.Inject;

public class UserProfileViewModel extends BaseViewModel {

    private UserRepository userRepository;

    @Inject
    public UserProfileViewModel(UserDao userDao, UsersApiService usersApiService) {
        userRepository = new UserRepository(userDao, usersApiService);
    }

    private MutableLiveData<Resource<UserProfileApiResponse>> userProfileLiveData = new MutableLiveData<>();

    public MutableLiveData<Resource<UserProfileApiResponse>> getUserProfileLiveData() {
        return userProfileLiveData;
    }

    public void loadUserProfile(Integer userId) {
        userRepository.loadUserProfile(userId).subscribe(
                resource -> getUserProfileLiveData().postValue(resource),
                error -> getUserProfileLiveData().postValue(null));
    }
}
