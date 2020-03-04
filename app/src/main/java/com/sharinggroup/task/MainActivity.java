package com.sharinggroup.task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;

import com.sharinggroup.task.databinding.MainActivityBinding;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    private MainActivityBinding binding;

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity);
    }

    public void showLoading() {
        binding.layoutLoading.clRootView.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        binding.layoutLoading.clRootView.setVisibility(View.GONE);
    }

    public void showConnectionLoss(){
        binding.layoutConnectionLoss.clRootView.setVisibility(View.VISIBLE);
    }

    public void hideConnectionLoss(){
        binding.layoutConnectionLoss.clRootView.setVisibility(View.GONE);
    }
}
