package in.anees.tdd.mvp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import in.anees.tdd.mvp.repositories.impl.DatabaseBooksRepository;
import in.anees.tdd.mvp.repositories.model.Book;
import in.anees.tdd.mvp.rx.SchedulerProviderImpl;

public class BooksActivity extends AppCompatActivity implements BooksActivityView {

    private static final String TAG = "BooksActivity";

    private TextView tvMessage;

    private BooksActivityPresenter mBooksActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        tvMessage = findViewById(R.id.textView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mBooksActivityPresenter =
                new BooksActivityPresenter(this,
                        new DatabaseBooksRepository(getApplicationContext()),
                        new SchedulerProviderImpl());
        mBooksActivityPresenter.loadBooks();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mBooksActivityPresenter.unSubscribe();
    }

    @Override
    public void displayBooks(List<Book> bookList) {
        Log.d(TAG, "displayBooks: " + bookList.size());
        String message = "Total " + bookList.size() + " items fetched!";
        tvMessage.setText(message);
    }

    @Override
    public void displayNoBooksFound() {
        Log.d(TAG, "displayNoBooksFound, Something went wrong...");
        tvMessage.setText("No Books found to display!");
    }

    @Override
    public void displayError() {
        Log.e(TAG, "Error happened, maybe show some Toast!");
        tvMessage.setText("Something went wrong!");
    }
}
