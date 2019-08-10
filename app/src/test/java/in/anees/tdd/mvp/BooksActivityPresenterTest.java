package in.anees.tdd.mvp;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import in.anees.tdd.mvp.repositories.BooksRepository;
import in.anees.tdd.mvp.repositories.model.Book;

import static org.junit.Assert.*;

/**
 * Created by Anees Thyrantakath on 2019-08-10.
 */
public class BooksActivityPresenterTest {

    @Test
    public void shouldPassBooksToView() {
        // Arrange

        BooksActivityView mView = new MockBooksActivityView();
        BooksRepository mBooksRepository = new MockBooksRepository(true);
        BooksActivityPresenter booksActivityPresenter = new BooksActivityPresenter(mView, mBooksRepository);
        // Act
        booksActivityPresenter.loadBooks();
        // Assert
        assertEquals(true, ((MockBooksActivityView) mView).isDisplayBooksWithBooksListCalled);
    }

    @Test
    public void shouldHandleNoBooksFound() {
        // Arrange
        BooksActivityView mView = new MockBooksActivityView();
        BooksRepository mBooksRepository = new MockBooksRepository(false);
        BooksActivityPresenter booksActivityPresenter = new BooksActivityPresenter(mView, mBooksRepository);
        // Act
        booksActivityPresenter.loadBooks();
        // Assert
        assertEquals(true, ((MockBooksActivityView) mView).isDisplayBooksWithNoBooksCalled);
    }

    public class MockBooksActivityView implements BooksActivityView {
        boolean isDisplayBooksWithBooksListCalled;
        boolean isDisplayBooksWithNoBooksCalled;

        @Override
        public void displayBooks(List<Book> bookList) {
            if (bookList.size() == 3) isDisplayBooksWithBooksListCalled = true;
        }

        @Override
        public void displayNoBooksFound() {
            isDisplayBooksWithNoBooksCalled = true;
        }
    }

    public class MockBooksRepository implements BooksRepository {

        private boolean mReturnSomeBooks;

        public MockBooksRepository(boolean returnSomeBooks) {
            mReturnSomeBooks = returnSomeBooks;
        }

        @Override
        public List<Book> getBooks() {
            if (mReturnSomeBooks) {
                return Arrays.asList(new Book(), new Book(), new Book());
            } else {
                return Collections.emptyList();
            }
        }
    }
}