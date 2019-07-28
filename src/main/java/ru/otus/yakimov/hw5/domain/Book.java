package ru.otus.yakimov.hw5.domain;

import lombok.Data;

import java.util.Objects;
import java.util.Set;

@Data
public class Book {
    private final String isbn;
    private final String title;
    private final String description;
    private final Set<Author> authors;
    private final Set<Genre> genres;


    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", authors=" + authors +
                ", genres=" + genres +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return isbn.equals(book.isbn) &&
                title.equals(book.title) &&
                description.equals(book.description) &&
                authors.equals(book.authors) &&
                genres.equals(book.genres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, title, description, authors, genres);
    }
}
