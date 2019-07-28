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
    public List<Book> findBooks() {
        final String sql = "select isbn, title, description from tbook";
        return namedParameterJdbcOperations.query(sql, (rs, i) -> {
            String isbn = rs.getString("isbn");
            String title = rs.getString("title");
            String description = rs.getString("description");
            return new Book(isbn, title, description);
        });
    }

    @Override
    public Map<String, Set<Long>> getBookIdToAuthorIdsMap() {
        final String sql = "select book_id, author_id from tbook_author";
        return namedParameterJdbcOperations.query(sql, rs -> {
            Map<String, Set<Long>> res = new HashMap<>();
            while (rs.next()) {
                String bookId = rs.getString("book_id");
                Long authorId = rs.getLong("author_id");
                Set<Long> authors = res.get(bookId);
                if (authors == null) {
                    authors = new HashSet<>();
                    res.put(bookId, authors);
                }
                authors.add(authorId);
            }
            return res;
        });
    }

    @Override
    public Map<String, Set<Long>> getBookIdToGenreIdsMap() {
        final String sql = "select book_id, genre_id from tbook_genre";
        return namedParameterJdbcOperations.query(sql, rs -> {
            Map<String, Set<Long>> res = new HashMap<>();
            while (rs.next()) {
                String bookId = rs.getString("book_id");
                Long authorId = rs.getLong("genre_id");
                Set<Long> genres = res.get(bookId);
                if (genres == null) {
                    genres = new HashSet<>();
                    res.put(bookId, genres);
                }
                genres.add(authorId);
            }
            return res;
        });
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
