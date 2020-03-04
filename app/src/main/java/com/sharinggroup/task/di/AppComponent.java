package com.sharinggroup.task.di;

import android.app.Application;

import com.sharinggroup.task.AppController;
import com.sharinggroup.task.di.module.ActivityModule;
import com.sharinggroup.task.di.module.ApiModule;
import com.sharinggroup.task.di.module.DbModule;
import com.sharinggroup.task.di.module.FragmentModule;
import com.sharinggroup.task.di.module.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/*
 * We mark this interface with the @Component annotation.
 * And we define all the modules that can be injected.
 * Note that we provide AndroidSupportInjectionModule.class
 * here. This class was not created by us.
 * It is an internal class in Dagger 2.10.
 * Provides our activities and fragments with given module.
 * */

@Singleton
@Component(modules = {AndroidInjectionModule.class, ApiModule.class, DbModule.class,
        ViewModelModule.class,
        AndroidSupportInjectionModule.class,
        ActivityModule.class,
        FragmentModule.class})
public interface AppComponent extends AndroidInjector<AppController> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(AppController appController);
}
