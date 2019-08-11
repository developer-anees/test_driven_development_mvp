package in.anees.tdd.mvp.dagger;

import android.content.Context;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import in.anees.tdd.mvp.BooksActivity;
import in.anees.tdd.mvp.BooksActivityPresenter;
import in.anees.tdd.mvp.BooksActivityView;
import in.anees.tdd.mvp.repositories.BooksRepository;
import in.anees.tdd.mvp.repositories.impl.DatabaseBooksRepository;
import in.anees.tdd.mvp.rx.SchedulerProviderImpl;

/**
 * Created by Anees Thyrantakath on 2019-08-12.
 */
@Module
public abstract class BookActivityModule {

    @Provides
    static BooksRepository provideBooksRepository(Context context) {
        return new DatabaseBooksRepository(context);
    }

    @Binds
    abstract BooksActivityView bindBooksActivityView(BooksActivity booksActivity);

    @Provides
    static BooksActivityPresenter provideMainPresenter(BooksActivityView mainView,
                                                       BooksRepository booksRepository,
                                                       SchedulerProviderImpl schedulerProviderImpl) {
        return new BooksActivityPresenter(mainView, booksRepository, schedulerProviderImpl);
    }
}
