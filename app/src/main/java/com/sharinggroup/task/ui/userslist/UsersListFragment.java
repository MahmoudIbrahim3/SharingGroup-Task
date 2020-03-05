package com.sharinggroup.task.ui.userslist;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sharinggroup.task.MainActivity;
import com.sharinggroup.task.R;
import com.sharinggroup.task.data.local.entity.UserEntity;
import com.sharinggroup.task.databinding.UsersListFragmentBinding;
import com.sharinggroup.task.di.ViewModelFactory;
import com.sharinggroup.task.ui.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class UsersListFragment extends BaseFragment {

    @Inject
    ViewModelFactory viewModelFactory;

    private UsersListViewModel viewModel;
    private UsersListFragmentBinding binding;
    private UsersListAdapter usersListAdapter;

    private MainActivity mainActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
        mainActivity = (MainActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.users_list_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewModel();
        initAdapter();
        initView();
    }

    private void initAdapter() {
        usersListAdapter = new UsersListAdapter();
        binding.rvUsers.setAdapter(usersListAdapter);
    }

    private void initView() {
        mainActivity.showActionBar();
        mainActivity.setActionBarTitle(getString(R.string.friends));
        mainActivity.showLoading();
        mainActivity.hideConnectionLoss();

        viewModel.loadUsers();
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this, viewModelFactory).get(UsersListViewModel.class);

        viewModel.getUsersLivaData().observe(getViewLifecycleOwner(), resource -> {
            Log.d("TAG", "loading: " + resource.isLoading());
            Log.d("TAG", "success: " + resource.isSuccess());
            Log.d("TAG", "data: " + resource.data.size());

            Boolean isTrue = resource.isLoading() || resource.isSuccess();

            if(isTrue && !resource.data.isEmpty()) {
                updateUI(resource.data);
            }
            else if(!isTrue && resource.data.isEmpty()) {
                mainActivity.hideLoading();
                mainActivity.showConnectionLoss();
            }
        });
    }

    private void updateUI(List<UserEntity> users) {
        mainActivity.hideLoading();
        usersListAdapter.getItems().clear();
        usersListAdapter.setItems(users);
    }
}
