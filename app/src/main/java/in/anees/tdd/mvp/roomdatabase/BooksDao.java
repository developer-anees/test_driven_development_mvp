package in.anees.tdd.mvp.roomdatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import in.anees.tdd.mvp.repositories.model.Book;


/**
 * Created by Anees Thyrantakath on 2019-08-10.
 */
@Dao
public interface BooksDao {

    @Insert
    void  insert(Book book);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(List<Book> books);

    @Update
    void update(Book book);

    @Delete
    void delete(Book book);

    @Query("DELETE FROM book_table")
    void deleteAllBooks();

    @Query("SELECT * FROM book_table")
    List<Book> getAllBooks();
}
