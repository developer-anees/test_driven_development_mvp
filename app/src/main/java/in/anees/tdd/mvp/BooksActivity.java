package in.anees.tdd.mvp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import in.anees.tdd.mvp.repositories.impl.DatabaseBooksRepository;
import in.anees.tdd.mvp.repositories.model.Book;

public class BooksActivity extends AppCompatActivity implements BooksActivityView {

    private static final String TAG = "BooksActivity";

    private BooksActivityPresenter mBooksActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        mBooksActivityPresenter =
                new BooksActivityPresenter(this, new DatabaseBooksRepository(getApplicationContext()));
        mBooksActivityPresenter.loadBooks();
    }

    @Override
    public void displayBooks(List<Book> bookList) {
        Log.d(TAG, "displayBooks: " + bookList.size());
    }

    @Override
    public void displayNoBooksFound() {
        Log.e(TAG, "displayNoBooksFound, Something went wrong...");
    }
}
