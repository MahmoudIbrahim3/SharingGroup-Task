package com.sharinggroup.task.data.repository;

import com.sharinggroup.task.data.NetworkBoundResource;
import com.sharinggroup.task.data.Resource;
import com.sharinggroup.task.data.local.dao.UserDao;
import com.sharinggroup.task.data.local.entity.UserEntity;
import com.sharinggroup.task.data.remote.api.UsersApiService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Observable;

@Singleton
public class UserRepository {

    private UserDao userDao;
    private UsersApiService usersApiService;

    public UserRepository(UserDao userDao, UsersApiService usersApiService) {
        this.userDao = userDao;
        this.usersApiService = usersApiService;
    }

    public Observable<Resource<List<UserEntity>>> loadUsers() {
        return new NetworkBoundResource<List<UserEntity>, List<UserEntity>>() {
            @Override
            protected void saveCallResult(List<UserEntity> result) {
                userDao.insertUsers(result);
            }

            @Override
            protected boolean shouldFetch() {
                return true;
            }

            @Override
            protected Flowable<List<UserEntity>> loadFromDb() {
                List<UserEntity> userEntities = userDao.getUsers();
                if(userEntities == null || userEntities.isEmpty())
                    return Flowable.just(Collections.emptyList());
                return Flowable.just(userEntities);
            }

            @Override
            protected Observable<Resource<List<UserEntity>>> createCall() {
                return usersApiService.fetchUsers()
                        .flatMap(usersApiResponse -> Observable.just(usersApiResponse == null
                                ? Resource.error("", new ArrayList<>())
                                : Resource.success(usersApiResponse)));
            }

        }.getAsObservable();
    }

    public Observable<Resource<UserEntity>> loadUserProfile(Integer userId) {
        return new NetworkBoundResource<UserEntity, UserEntity>(){

            @Override
            protected void saveCallResult(UserEntity data) {
                userDao.insertUserProfile(data);
            }

            @Override
            protected boolean shouldFetch() {
                return true;
            }

            @Override
            protected Flowable<UserEntity> loadFromDb() {
                UserEntity userEntity = userDao.getUserProfileById(userId);
                return Flowable.just(userEntity);
            }

            @Override
            protected Observable<Resource<UserEntity>> createCall() {
                return usersApiService.fetchUserProfile(userId)
                        .flatMap(usersApiResponse -> Observable.just(usersApiResponse == null
                                ? Resource.error("", null)
                                : Resource.success(usersApiResponse)));
            }
        }.getAsObservable();
    }
}
