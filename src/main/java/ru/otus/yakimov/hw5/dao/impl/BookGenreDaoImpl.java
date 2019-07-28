package ru.otus.yakimov.hw5.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;
import ru.otus.yakimov.hw5.dao.BookGenreDao;
import ru.otus.yakimov.hw5.domain.BookGenre;

import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookGenreDaoImpl implements BookGenreDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public void add(List<BookGenre> bookGenreList) {
        SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(bookGenreList);
        final String sql = "insert into tbook_genre(book_id, genre_id) values(:bookId, :genreId)";
        namedParameterJdbcOperations.batchUpdate(sql, params);
    }

    @Override
    public List<BookGenre> findByBookId(String bookId) {
        final String sql = "select book_id, genre_id from tbook_genre where book_id = :bookId";
        return namedParameterJdbcOperations.query(sql, Collections.singletonMap("bookId", bookId), (rs, i)->{
            String b_id = rs.getString("book_id");
            long genre_id = rs.getLong("genre_id");
            return new BookGenre(b_id, genre_id);
        });
    }

    @Override
    public void delete(String bookId) {
        final String sql = "delete from tbook_genre where book_id = :bookId";
        namedParameterJdbcOperations.update(sql, Collections.singletonMap("bookId", bookId));
    }
}
