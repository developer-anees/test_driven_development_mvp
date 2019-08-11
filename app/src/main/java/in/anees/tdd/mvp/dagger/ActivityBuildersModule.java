package in.anees.tdd.mvp.dagger;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

import in.anees.tdd.mvp.BooksActivity;

/**
 * Created by Anees Thyrantakath on 2019-08-12.
 */
@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = BookActivityModule.class)
    abstract BooksActivity bindBooksActivity();
}
