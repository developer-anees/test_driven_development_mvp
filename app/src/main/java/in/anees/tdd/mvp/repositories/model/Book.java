package in.anees.tdd.mvp.repositories.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Anees Thyrantakath on 2019-08-10.
 */
@Entity(tableName = "book_table")
public class Book {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String title;

    public Book(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
