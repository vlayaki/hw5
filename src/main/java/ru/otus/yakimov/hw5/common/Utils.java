package ru.otus.yakimov.hw5.common;

import ru.otus.yakimov.hw5.domain.Book;
import ru.otus.yakimov.hw5.domain.BookAuthor;
import ru.otus.yakimov.hw5.domain.BookGanre;

import java.util.List;
import java.util.stream.Collectors;

public class Utils {
    public static List<BookAuthor> fromBookToBookAuthor(Book book) {
        return book.getAuthors()
                .stream()
                .map(author -> new BookAuthor(book.getIsbn(), author.getId()))
                .collect(Collectors.toList());
    }

    public static List<BookGanre> fromBookToBookGanre(Book book){
        return book.getGanres()
                .stream()
                .map(ganre -> new BookGanre(book.getIsbn(), ganre.getId()))
                .collect(Collectors.toList());
    }
}
