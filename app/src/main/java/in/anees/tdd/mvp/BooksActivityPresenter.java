package in.anees.tdd.mvp;

import java.util.List;

import in.anees.tdd.mvp.repositories.BooksRepository;
import in.anees.tdd.mvp.repositories.model.Book;

/**
 * Created by Anees Thyrantakath on 2019-08-09.
 */
class BooksActivityPresenter {

    private BooksActivityView mBooksActivityView;
    private BooksRepository mBooksRepository;

    public BooksActivityPresenter(BooksActivityView booksActivityView, BooksRepository booksRepository) {
        this.mBooksActivityView = booksActivityView;
        this.mBooksRepository = booksRepository;
    }


    public void loadBooks() {
        List<Book> bookList = mBooksRepository.getBooks();
        if (!bookList.isEmpty()) {
            mBooksActivityView.displayBooks(bookList);
        } else {
            mBooksActivityView.displayNoBooksFound();
        }
    }
}
