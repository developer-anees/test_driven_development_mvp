package in.anees.tdd.mvp.repositories.impl;

import android.content.Context;

import java.util.List;

import in.anees.tdd.mvp.repositories.BooksRepository;
import in.anees.tdd.mvp.repositories.model.Book;
import in.anees.tdd.mvp.room_database.BooksDatabase;

import io.reactivex.Single;

/**
 * Created by Anees Thyrantakath on 2019-08-10.
 */
public class DatabaseBooksRepository implements BooksRepository {

    private BooksDatabase mBooksDatabase;

    public DatabaseBooksRepository(Context context) {
        mBooksDatabase = BooksDatabase.getInstance(context);
    }

    @Override
    public Single<List<Book>> getBooks() {
        return Single.fromCallable(() -> {
            return mBooksDatabase.booksDao().getAllBooks();
        });
    }
}
