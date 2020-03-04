package com.sharinggroup.task.data.remote.api;

import com.sharinggroup.task.data.local.entity.UserEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface UsersApiService {

    @GET("users/")
    Observable<List<UserEntity>> fetchUsers();
}
