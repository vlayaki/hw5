package ru.otus.yakimov.hw5.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;
import ru.otus.yakimov.hw5.dao.BookDao;
import ru.otus.yakimov.hw5.dao.extractors.BookExtractor;
import ru.otus.yakimov.hw5.domain.Author;
import ru.otus.yakimov.hw5.domain.Book;
import ru.otus.yakimov.hw5.domain.Genre;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class BookDaoImpl implements BookDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public Book findById(String id) {
        final String select =
                "select b.isbn, b.title, b.description book_desc, a.id author_id, a.first_name, a.last_name, g.id genre_id, g.name, g.description genre_desc " +
                        "from tbook b " +
                        "left join tbook_author ba on b.isbn = ba.book_id " +
                        "left join tauthor a on a.id = ba.author_id " +
                        "left join tbook_genre bg on b.isbn = bg.book_id " +
                        "left join tgenre g on g.id = bg.genre_id " +
                        "where b.isbn = :id";
        Map<String, String> params = Collections.singletonMap("id", id);
        List<Book> books = namedParameterJdbcOperations.query(select, params, new BookExtractor());
        if (books.size() == 1) {
            return books.get(0);
        } else {
            throw new RuntimeException("Several or non books were found with id = " + id);
        }
    }

    @Override
    public List<Book> findAll() {
        final String select =
                "select b.isbn, b.title, b.description book_desc, a.id author_id, a.first_name, a.last_name, g.id genre_id, g.name, g.description genre_desc from tbook b " +
                        "left join tbook_author ba on b.isbn = ba.book_id " +
                        "left join tauthor a on a.id = ba.author_id " +
                        "left join tbook_genre bg on b.isbn = bg.book_id " +
                        "left join tgenre g on g.id = bg.genre_id ";
        return namedParameterJdbcOperations.query(select, new BookExtractor());
    }

    @Override
    public void add(Book book) {
        addBook(book);
        addBookAuthors(book);
        addBookGenres(book);

    }

    private void addBook(Book book) {
        final String sql = "insert into tbook(isbn, title, description) values(:isbn, :title, :description)";
        Map<String, Object> params = new HashMap<>();
        params.put("isbn", book.getIsbn());
        params.put("title", book.getTitle());
        params.put("description", book.getDescription());
        namedParameterJdbcOperations.update(sql, params);
    }

    private void addBookGenres(Book book) {
        final String sql = "insert into tbook_genre(book_id, genre_id) values(:bookId, :genreId)";
        final List<Map<String, Object>> params = new ArrayList<>();
        Set<Genre> genres = book.getGenres();
        genres.forEach(g -> {
            Map<String, Object> map = new HashMap<>();
            map.put("bookId", book.getIsbn());
            map.put("genreId", g.getId());
            params.add(map);
        });
        SqlParameterSource[] parameterSources = SqlParameterSourceUtils.createBatch(params);
        namedParameterJdbcOperations.batchUpdate(sql, parameterSources);
    }

    private void addBookAuthors(Book book) {
        final String sql = "insert into tbook_author(book_id, author_id) values(:bookId, :authorId)";
        final List<Map<String, Object>> params = new ArrayList<>();
        Set<Author> authors = book.getAuthors();
        authors.forEach(a -> {
            Map<String, Object> map = new HashMap<>();
            map.put("bookId", book.getIsbn());
            map.put("authorId", a.getId());
            params.add(map);
        });
        SqlParameterSource[] parameterSources = SqlParameterSourceUtils.createBatch(params);
        namedParameterJdbcOperations.batchUpdate(sql, parameterSources);
    }
}
