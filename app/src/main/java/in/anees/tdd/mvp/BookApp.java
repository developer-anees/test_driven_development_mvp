package in.anees.tdd.mvp;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

import in.anees.tdd.mvp.dagger.AppComponent;
import in.anees.tdd.mvp.dagger.DaggerAppComponent;

/**
 * Created by Anees Thyrantakath on 2019-08-12.
 */
public class BookApp extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }
}
