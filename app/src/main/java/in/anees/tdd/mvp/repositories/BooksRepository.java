package in.anees.tdd.mvp.repositories;

import java.util.List;

import in.anees.tdd.mvp.repositories.model.Book;

/**
 * Created by Anees Thyrantakath on 2019-08-10.
 */
public interface BooksRepository {
    List<Book> getBooks();
}
