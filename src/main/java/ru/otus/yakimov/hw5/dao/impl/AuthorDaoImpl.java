package ru.otus.yakimov.hw5.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.yakimov.hw5.dao.AuthorDao;
import ru.otus.yakimov.hw5.domain.Author;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class AuthorDaoImpl implements AuthorDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public void add(Author author) {
        final String sql = "insert into tauthor(id, first_name, last_name) values(:id, :firstName, :lastName)";
        Map<String, Object> params = new HashMap<>();
        params.put("id", author.getId());
        params.put("firstName", author.getFirstName());
        params.put("lastName", author.getLastName());
        namedParameterJdbcOperations.update(sql, params);
    }

    @Override
    public Author findById(long id) {
        final String sql = "select id, first_name, last_name from tauthor where id = :id";
        return namedParameterJdbcOperations.queryForObject(sql, Collections.singletonMap("id", id), (rs, i) -> {
            long authorId = rs.getLong("id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            return new Author(id, lastName, firstName);
        });
    }

    @Override
    public Map<Long, Author> getIdToAuthorMap() {
        final String sql = "select id, first_name, last_name from tauthor";
        return namedParameterJdbcOperations.query(sql, (rs) -> {
            Map<Long, Author> res = new HashMap<>();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                Author author = new Author(id, lastName, firstName);
                res.put(id, author);
            }
            return res;
        });
    }
}
