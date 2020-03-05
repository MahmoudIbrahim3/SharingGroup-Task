package com.sharinggroup.task.ui.userprofile;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sharinggroup.task.AppConst;
import com.sharinggroup.task.MainActivity;
import com.sharinggroup.task.R;
import com.sharinggroup.task.data.remote.model.UserProfileApiResponse;
import com.sharinggroup.task.data.remote.model.Address;
import com.sharinggroup.task.databinding.UserProfileFragmentBinding;
import com.sharinggroup.task.di.ViewModelFactory;
import com.sharinggroup.task.ui.base.BaseFragment;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class UserProfileFragment extends BaseFragment {

    @Inject
    ViewModelFactory viewModelFactory;

    private UserProfileViewModel viewModel;
    private UserProfileFragmentBinding binding;
    private MainActivity mainActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
        mainActivity = (MainActivity) getActivity();
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
            if(resource != null && resource.isSuccess() && resource.data != null) {
                updateUI(resource.data);
            }
            else {
                mainActivity.hideLoading();
                mainActivity.showConnectionLoss();
            }
        });
    }

    private void updateUI(UserProfileApiResponse data) {
        mainActivity.hideLoading();

        binding.tvName.setText(data.getName());
        binding.tvUsername.setText(getString(R.string.username, data.getUsername()));

        Address add = data.getAddress();
        String address = add.getStreet() + ", " + add.getSuite() + ", " + add.getCity();
        binding.tvAddress.setText(getString(R.string.address, address));
        binding.tvEmail.setText(getString(R.string.email, data.getEmail()));
        binding.tvPhone.setText(getString(R.string.phone, data.getPhone()));
        binding.tvWebsite.setText(getString(R.string.website, data.getWebsite()));
        binding.tvCompany.setText(getString(R.string.company, data.getCompany().getName()));
    }

    private void initView() {
        mainActivity.showActionBar();
        mainActivity.setActionBarTitle(getString(R.string.profile));

        mainActivity.showLoading();
        mainActivity.hideConnectionLoss();
        mainActivity.showBackArrow();

        mainActivity.binding.toolbar.setNavigationOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot()).popBackStack();
            mainActivity.hideBackArrow();
        });

        viewModel.loadUserProfile(getArguments().getInt(AppConst.INTENT_USER_ID));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
