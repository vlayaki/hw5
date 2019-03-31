package ru.otus.yakimov.hw5.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;
import ru.otus.yakimov.hw5.dao.BookAuthorDao;
import ru.otus.yakimov.hw5.domain.BookAuthor;

import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookAuthorDaoImpl implements BookAuthorDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public void add(List<BookAuthor> bookAuthor) {
        SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(bookAuthor);
        final String sql = "insert into tbook_author(book_id, author_id) values(:bookId, :authorId)";
        namedParameterJdbcOperations.batchUpdate(sql, params);
    }

    @Override
    public void delete(String bookId) {
        final String sql = "delete from tbook_author where book_id = :bookId";
        namedParameterJdbcOperations.update(sql, Collections.singletonMap("bookId", bookId));
    }

    @Override
    public List<BookAuthor> findByBookId(String bookId) {
        final String sql = "select book_id, author_id from tbook_author where book_id = :bookId";
        return namedParameterJdbcOperations.query(sql, Collections.singletonMap("bookId", bookId), (rs, i) -> {
            String bId = rs.getString("book_id");
            long authorId = rs.getLong("author_id");
            return new BookAuthor(bId, authorId);
        });
    }
}
