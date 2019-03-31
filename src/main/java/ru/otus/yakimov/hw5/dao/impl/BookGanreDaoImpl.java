package ru.otus.yakimov.hw5.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;
import ru.otus.yakimov.hw5.dao.BookGanreDao;
import ru.otus.yakimov.hw5.domain.BookGanre;

import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookGanreDaoImpl implements BookGanreDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public void add(List<BookGanre> bookGanreList) {
        SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(bookGanreList);
        final String sql = "insert into tbook_ganre(book_id, ganre_id) values(:bookId, :ganreId)";
        namedParameterJdbcOperations.batchUpdate(sql, params);
    }

    @Override
    public List<BookGanre> findByBookId(String bookId) {
        final String sql = "select book_id, ganre_id from tbook_ganre where book_id = :bookId";
        return namedParameterJdbcOperations.query(sql, Collections.singletonMap("bookId", bookId), (rs, i)->{
            String b_id = rs.getString("book_id");
            long ganre_id = rs.getLong("ganre_id");
            return new BookGanre(b_id, ganre_id);
        });
    }

    @Override
    public void delete(String bookId) {
        final String sql = "delete from tbook_ganre where book_id = :bookId";
        namedParameterJdbcOperations.update(sql, Collections.singletonMap("bookId", bookId));
    }
}
