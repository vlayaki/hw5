package ru.otus.yakimov.hw5.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.yakimov.hw5.dao.impl.BookDaoImpl;
import ru.otus.yakimov.hw5.domain.Author;
import ru.otus.yakimov.hw5.domain.Book;
import ru.otus.yakimov.hw5.domain.Genre;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@JdbcTest
@Import(BookDaoImpl.class)
public class BookDaoTest {

    @Autowired
    BookDao bookDao;

    @Test
    public void shouldReadBookFromDb() {
        final String isbn = "0596009208";
        final String title = "Head First Java";
        final String description = "Learning a complex new language is no easy task especially when it s an object-oriented computer programming...";
        Set<Author> authors = new HashSet<>();
        authors.add(new Author(3, "Sierra", "Kathy"));
        authors.add(new Author(4, "Bates", "Bert"));
        Set<Genre> genres = new HashSet<>();
        genres.add(new Genre(1, "educational", "desc"));
        Book expected = new Book(isbn, title, description, authors, genres);
        Book actual = bookDao.findById(isbn);
        assertThat(actual, notNullValue());
        assertThat(actual, is(equalTo(expected)));
    }


    @Rollback
    @Test
    public void shouldPersistBookToDb(){
        final String isbn = "1";
        final String title = "test book";
        final String description = "test book desc";
        Set<Author> authors = new HashSet<>();
        authors.add(new Author(3, "Sierra", "Kathy"));
        authors.add(new Author(4, "Bates", "Bert"));
        Set<Genre> genres = new HashSet<>();
        genres.add(new Genre(1, "educational", "desc"));
        Book testBook = new Book(isbn, title, description, authors, genres);
        bookDao.add(testBook);
        Book bookFromDb = bookDao.findById(testBook.getIsbn());
        assertThat(bookFromDb, is(notNullValue()));
        assertThat(bookFromDb, is(equalTo(testBook)));
    }
}