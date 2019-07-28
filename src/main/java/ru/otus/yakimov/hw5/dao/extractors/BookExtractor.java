package ru.otus.yakimov.hw5.dao.extractors;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import ru.otus.yakimov.hw5.domain.Author;
import ru.otus.yakimov.hw5.domain.Book;
import ru.otus.yakimov.hw5.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class BookExtractor implements ResultSetExtractor<List<Book>> {
    @Override
    public List<Book> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<String, Book> isbnToBook = new HashMap<>();
        while (rs.next()) {
            String isbn = rs.getString("isbn");
            Book book = isbnToBook.get(isbn);
            if (book == null) {
                String title = rs.getString("title");
                String description = rs.getString("book_desc");
                Set<Author> authors = new HashSet<>();
                Set<Genre> genres = new HashSet<>();
                book = new Book(isbn, title, description, authors, genres);
                isbnToBook.put(isbn, book);
            }
            Long authorId = rs.getLong("author_id");
            if (!rs.wasNull()) {
                String lastName = rs.getString("last_name");
                String firstName = rs.getString("first_name");
                Author author = new Author(authorId, lastName, firstName);
                book.getAuthors().add(author);
            }

            Long genreId = rs.getLong("genre_id");
            if (!rs.wasNull()) {
                String name = rs.getString("name");
                String description = rs.getString("genre_desc");
                Genre genre = new Genre(genreId, name, description);
                book.getGenres().add(genre);
            }
        }
        return new ArrayList<>(isbnToBook.values());
    }
}
