package ru.otus.yakimov.hw5.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.yakimov.hw5.domain.Author;
import ru.otus.yakimov.hw5.domain.Book;
import ru.otus.yakimov.hw5.domain.Genre;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
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

    @Test
    public void shouldPersistBookToDb(){
        Book book = new Book("isbn-test", "title-test", "desc-test", Collections.emptySet(), Collections.emptySet());
        bookDao.add(book);
        Book bookFromDb = bookDao.findById(book.getIsbn());
        assertThat(bookFromDb, is(notNullValue()));
        assertThat(bookFromDb, is(equalTo(book)));
    }

    @Test(expected = RuntimeException.class)
    @Rollback
    public void shouldDeleteBookFromDb() {
        final String isbn = "0596009208";
        bookDao.delete(isbn);
        Book book = bookDao.findById(isbn);
    }
}