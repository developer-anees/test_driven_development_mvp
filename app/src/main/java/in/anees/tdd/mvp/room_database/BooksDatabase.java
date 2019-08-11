package in.anees.tdd.mvp.room_database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import in.anees.tdd.mvp.repositories.model.Book;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Anees Thyrantakath on 2019-08-10.
 */
@Database(entities = {Book.class}, version = 1, exportSchema = false)
public abstract class BooksDatabase extends RoomDatabase {

    private static final String TAG = "BooksDatabase";
    private static BooksDatabase sInstance;

    public abstract BooksDao booksDao();

    public static synchronized BooksDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(context.getApplicationContext(), BooksDatabase.class, "books_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return sInstance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            // Here we insert three items to database
            CompositeDisposable compositeDisposable = new CompositeDisposable();
            Completable completable = Completable
                    .fromAction(() -> {
                        List<Book> insertListOfBooks = new ArrayList<>(3);
                        insertListOfBooks.add(new Book(1L, "Book A"));
                        insertListOfBooks.add(new Book(2L, "Book B"));
                        insertListOfBooks.add(new Book(3L, "Book C"));
                        sInstance.booksDao().insertAll(insertListOfBooks);
                    })
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread());
            completable.subscribe(new CompletableObserver() {
                @Override
                public void onSubscribe(Disposable d) {
                    compositeDisposable.add(d);
                }

                @Override
                public void onComplete() {
                    compositeDisposable.clear();
                }

                @Override
                public void onError(Throwable e) {
                    compositeDisposable.clear();
                }
            });
        }
    };
}
