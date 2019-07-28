package ru.otus.yakimov.hw5.service;

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

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    @Rollback
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
        bookService.add(testBook);
        Book bookFromDb = bookService.findById(testBook.getIsbn());
        assertThat(bookFromDb, is(notNullValue()));
        assertThat(bookFromDb, is(equalTo(testBook)));
    }
}