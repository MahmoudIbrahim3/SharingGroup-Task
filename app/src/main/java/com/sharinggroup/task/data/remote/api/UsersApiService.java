package com.sharinggroup.task.data.remote.api;

import com.sharinggroup.task.data.local.entity.UserEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UsersApiService {

    @GET("users/")
    Observable<List<UserEntity>> fetchUsers();

    @GET("users/{user_id}")
    Observable<UserEntity> fetchUserProfile(@Path("user_id") Integer userId);
}
