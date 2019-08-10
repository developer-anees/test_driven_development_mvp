package in.anees.tdd.mvp.repositories.impl;

import android.content.Context;

import java.util.List;

import in.anees.tdd.mvp.repositories.BooksRepository;
import in.anees.tdd.mvp.repositories.model.Book;
import in.anees.tdd.mvp.room_database.BooksDatabase;

/**
 * Created by Anees Thyrantakath on 2019-08-10.
 */
public class DatabaseBooksRepository implements BooksRepository {

    private static final String TAG = "DatabaseBooksRepository";

    private BooksDatabase mBooksDatabase;

    public DatabaseBooksRepository(Context context) {
        mBooksDatabase = BooksDatabase.getInstance(context);
    }

    @Override
    public List<Book> getBooks() {
        // TODO : Not good to call on main thread need to change implementation later
        return mBooksDatabase.booksDao().getAllBooks();
    }
}
