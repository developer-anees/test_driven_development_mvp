package in.anees.tdd.mvp;

import java.util.List;

import in.anees.tdd.mvp.repositories.model.Book;

/**
 * Created by Anees Thyrantakath on 2019-08-09.
 */
public interface BooksActivityView {

    void displayBooks(List<Book> bookList);
    void displayNoBooksFound();
}
