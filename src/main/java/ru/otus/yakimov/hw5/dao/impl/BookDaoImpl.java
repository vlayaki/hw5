package ru.otus.yakimov.hw5.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.yakimov.hw5.dao.BookDao;
import ru.otus.yakimov.hw5.dao.extractors.BookExtractor;
import ru.otus.yakimov.hw5.domain.Book;

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
        final String sql = "insert into tbook(isbn, title, description) values(:isbn, :title, :description)";
        Map<String, Object> params = new HashMap<>();
        params.put("isbn", book.getIsbn());
        params.put("title", book.getTitle());
        params.put("description", book.getDescription());
        namedParameterJdbcOperations.update(sql, params);
    }

    @Override
    public void delete(String id) {
        final String sql = "delete from tbook where isbn = :id";
        Map<String, String> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(sql, params);
    }
}
