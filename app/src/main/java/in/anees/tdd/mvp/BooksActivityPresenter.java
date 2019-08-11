package in.anees.tdd.mvp;

import java.util.List;

import in.anees.tdd.mvp.repositories.BooksRepository;
import in.anees.tdd.mvp.repositories.model.Book;
import in.anees.tdd.mvp.rx.scheduler.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by Anees Thyrantakath on 2019-08-09.
 */
class BooksActivityPresenter {

    private BooksActivityView mBooksActivityView;
    private BooksRepository mBooksRepository;

    private CompositeDisposable mCompositeDisposable;
    private SchedulerProvider mSchedulerProvider;

    public BooksActivityPresenter(BooksActivityView booksActivityView,
                                  BooksRepository booksRepository, SchedulerProvider schedulerProvider) {
        mCompositeDisposable = new CompositeDisposable();
        this.mBooksActivityView = booksActivityView;
        this.mBooksRepository = booksRepository;
        this.mSchedulerProvider = schedulerProvider;
    }

    public void loadBooks() {
        mCompositeDisposable.add(mBooksRepository.getBooks()
                .subscribeOn(mSchedulerProvider.backgroundScheduler())
                .observeOn(mSchedulerProvider.mainScheduler())
                .subscribeWith(new DisposableSingleObserver<List<Book>>() {
                    @Override
                    public void onSuccess(List<Book> bookList) {
                        if (!bookList.isEmpty()) {
                            mBooksActivityView.displayBooks(bookList);
                        } else {
                            mBooksActivityView.displayNoBooksFound();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mBooksActivityView.displayError();
                    }
                }));
    }

    public void unSubscribe() {
        mCompositeDisposable.clear();
    }
}
