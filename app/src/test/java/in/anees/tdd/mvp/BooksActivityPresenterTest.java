package in.anees.tdd.mvp;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import in.anees.tdd.mvp.repositories.BooksRepository;
import in.anees.tdd.mvp.repositories.model.Book;
import in.anees.tdd.mvp.rx.scheduler.SchedulerProvider;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.verify;

/**
 * Created by Anees Thyrantakath on 2019-08-10.
 */
public class BooksActivityPresenterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    BooksRepository mBooksRepository;
    @Mock
    BooksActivityView mView;

    BooksActivityPresenter booksActivityPresenter;

    private final List<Book> MANY_BOOKS = Arrays.asList(new Book(1L, "asd"),
            new Book(2L, "asdad"), new Book(3L, "sdgrs"));

    @Before
    public void setUp() throws Exception {
        MockTestScheduler mockTestScheduler = new MockTestScheduler();
        booksActivityPresenter = new BooksActivityPresenter(mView, mBooksRepository, mockTestScheduler);
    }

    @Test
    public void shouldPassBooksToView() {
        // Arrange
        Mockito.when(mBooksRepository.getBooks()).thenReturn(Single.just(MANY_BOOKS));
        // Act
        booksActivityPresenter.loadBooks();
        // Assert
        verify(mView).displayBooks(MANY_BOOKS);
    }


    @Test
    public void shouldHandleNoBooksFound() {
        // Arrange
        Mockito.when(mBooksRepository.getBooks()).thenReturn(Single.just(Collections.<Book>emptyList()));
        // Act
        booksActivityPresenter.loadBooks();
        // Assert
        verify(mView).displayNoBooksFound();
    }

    @Test
    public void shouldHandleError() {
        // Arrange
        Mockito.when(mBooksRepository.getBooks()).thenReturn(Single.<List<Book>>error(new Throwable("")));
        // Act
        booksActivityPresenter.loadBooks();
        // Assert
        verify(mView).displayError();
    }

    public class MockTestScheduler implements SchedulerProvider {

        @Override
        public Scheduler mainScheduler() {
            return Schedulers.trampoline();
        }

        @Override
        public Scheduler backgroundScheduler() {
            return Schedulers.trampoline();
        }
    }
}