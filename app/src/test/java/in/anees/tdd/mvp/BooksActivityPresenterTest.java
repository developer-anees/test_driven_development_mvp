package in.anees.tdd.mvp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import in.anees.tdd.mvp.repositories.BooksRepository;
import in.anees.tdd.mvp.repositories.model.Book;

/**
 * Created by Anees Thyrantakath on 2019-08-10.
 */
@RunWith(MockitoJUnitRunner.class)
public class BooksActivityPresenterTest {

    private final List<Book> MANY_BOOKS = Arrays.asList(new Book(1L, "asd"),
            new Book(2L, "asdad"), new Book(3L, "sdgrs"));

    @Mock BooksRepository mBooksRepository;
    @Mock BooksActivityView mView;

    BooksActivityPresenter booksActivityPresenter;

    @Before
    public void setUp() throws Exception {
        booksActivityPresenter = new BooksActivityPresenter(mView, mBooksRepository);
    }

    @Test
    public void shouldPassBooksToView() {
        // Arrange
        Mockito.when(mBooksRepository.getBooks()).thenReturn(MANY_BOOKS);
        // Act
        booksActivityPresenter.loadBooks();
        // Assert
        Mockito.verify(mView).displayBooks(MANY_BOOKS);
    }

    @Test
    public void shouldHandleNoBooksFound() {
        // Arrange
        Mockito.when(mBooksRepository.getBooks()).thenReturn(Collections.<Book>emptyList());
        // Act
        booksActivityPresenter.loadBooks();
        // Assert
        Mockito.verify(mView).displayNoBooksFound();
    }
}