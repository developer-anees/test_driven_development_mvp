package in.anees.tdd.mvp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class BooksActivity extends AppCompatActivity implements BooksActivityView {

    private BooksActivityPresenter mBooksActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        mBooksActivityPresenter = new BooksActivityPresenter(this);
    }
}
