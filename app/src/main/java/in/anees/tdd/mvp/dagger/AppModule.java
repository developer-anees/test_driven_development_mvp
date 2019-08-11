package in.anees.tdd.mvp.dagger;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Anees Thyrantakath on 2019-08-12.
 */
@Module
public abstract class AppModule {
    @Binds
    abstract Context bindContext(Application application);
}
