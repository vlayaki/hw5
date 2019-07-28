package ru.otus.yakimov.hw5.shell;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.yakimov.hw5.domain.Author;
import ru.otus.yakimov.hw5.domain.Book;
import ru.otus.yakimov.hw5.domain.Genre;
import ru.otus.yakimov.hw5.service.BookService;
import ru.otus.yakimov.hw5.service.UserService;

import java.util.Set;
import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
@Slf4j
public class BookCommands {

    private final BookService bookService;
    private final UserService userService;

    @ShellMethod(value =  "Prints book in JSON format", key = "show_book")
    public void show_book(String isbn){
        Book book = bookService.findById(isbn);
        log.info(book.toString());
    }

    @ShellMethod(value = "Adds new book to database", key = "add_book")
    public void add_book(String isbn, String title, String desc) {
        Set<Author> authors = userService.getSetOfIdsFromUser("Input author id or press Enter to continue.")
                .stream()
                .map(id -> new Author(id, null, null))
                .collect(Collectors.toSet());
        Set<Genre> genres = userService.getSetOfIdsFromUser("Input genre id or press Enter to continue.")
                .stream()
                .map(id -> new Genre(id, null, null))
                .collect(Collectors.toSet());
        Book book = new Book(isbn, title, desc, authors, genres);
        bookService.add(book);
    }

}
