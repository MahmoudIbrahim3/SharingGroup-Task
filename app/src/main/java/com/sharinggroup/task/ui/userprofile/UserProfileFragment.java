package com.sharinggroup.task.ui.userprofile;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sharinggroup.task.AppConst;
import com.sharinggroup.task.MainActivity;
import com.sharinggroup.task.R;
import com.sharinggroup.task.data.local.entity.UserEntity;
import com.sharinggroup.task.databinding.UserProfileFragmentBinding;
import com.sharinggroup.task.di.ViewModelFactory;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class UserProfileFragment extends Fragment {

    @Inject
    ViewModelFactory viewModelFactory;

    private UserProfileViewModel viewModel;
    private UserProfileFragmentBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.user_profile_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewModel();
        initView();
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this, viewModelFactory).get(UserProfileViewModel.class);

        viewModel.getUserProfileLiveData().observe(getViewLifecycleOwner(), resource -> {
            if(resource.isSuccess() || resource.data != null) {
                updateUI(resource.data);
            }
            else if(!resource.isSuccess()){
                ((MainActivity) getActivity()).hideLoading();
                ((MainActivity) getActivity()).showConnectionLoss();
            }
        });
    }

    private void updateUI(UserEntity data) {
        ((MainActivity) getActivity()).hideLoading();

        binding.tvName.setText(data.getName());
        binding.tvUsername.setText(getString(R.string.username, data.getUsername()));
        binding.tvAddress.setText(getString(R.string.address, data.getAddress()));
        binding.tvEmail.setText(getString(R.string.email, data.getEmail()));
        binding.tvPhone.setText(getString(R.string.phone, data.getWebsite()));
        binding.tvWebsite.setText(getString(R.string.website, data.getWebsite()));
        binding.tvCompany.setText(getString(R.string.company, data.getCompany()));
    }

    private void initView() {
        ((MainActivity) getActivity()).showLoading();
        ((MainActivity) getActivity()).hideConnectionLoss();

        viewModel.loadUserProfile(getArguments().getInt(AppConst.INTENT_USER_ID));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
