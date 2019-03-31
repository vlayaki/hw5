package ru.otus.yakimov.hw5.dao.extractors;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import ru.otus.yakimov.hw5.domain.Author;
import ru.otus.yakimov.hw5.domain.Book;
import ru.otus.yakimov.hw5.domain.Ganre;

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
                Set<Ganre> ganres = new HashSet<>();
                book = new Book(isbn, title, description, authors, ganres);
                isbnToBook.put(isbn, book);
            }
            Long authorId = rs.getLong("author_id");
            if (!rs.wasNull()) {
                String lastName = rs.getString("last_name");
                String firstName = rs.getString("first_name");
                Author author = new Author(authorId, lastName, firstName);
                book.getAuthors().add(author);
            }

            Long ganreId = rs.getLong("ganre_id");
            if (!rs.wasNull()) {
                String name = rs.getString("name");
                String description = rs.getString("ganre_desc");
                Ganre ganre = new Ganre(ganreId, name, description);
                book.getGanres().add(ganre);
            }
        }
        return new ArrayList<>(isbnToBook.values());
    }
}
