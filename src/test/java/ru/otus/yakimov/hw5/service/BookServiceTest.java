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

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
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
    public void shouldReadAllBooksFromDb() {
        String isbn1 = "0596009208";
        String title1 = "Head First Java";
        String desc1 = "Learning a complex new language is no easy task especially when it s an object-oriented computer programming...";
        Set<Author> authors1 = new HashSet<>(Arrays.asList(new Author(3,"Sierra", "Kathy"), new Author(4, "Bates", "Bert")));
        Set<Genre> genres1 = new HashSet<>(Arrays.asList(new Genre(1, "educational", "desc")));
        Book book1 = new Book(isbn1, title1, desc1, authors1, genres1);

        String isbn2 = "0321127420";
        String title2 = "Patterns of Enterprise Application Architecture";
        String desc2 = "The practice of enterprise application development has benefited from the emergence of many...";
        Set<Author> authors2 = new HashSet<>(Arrays.asList(new Author(2,"Fowler", "Martin")));
        Set<Genre> genres2 = new HashSet<>(Arrays.asList(new Genre(1, "educational", "desc")));
        Book book2 = new Book(isbn2, title2, desc2, authors2, genres2);

        List<Book> actual = bookService.findBooks();
        assertThat(actual, hasSize(2));
        assertThat(actual, containsInAnyOrder(book1, book2));
    }
}