package ru.otus.yakimov.hw5.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.yakimov.hw5.dao.GenreDao;
import ru.otus.yakimov.hw5.domain.Genre;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class GenreDaoImpl implements GenreDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public void add(Genre genre) {
        final String sql = "insert into tgenre(id, name, description) values(:id, :name, :description)";
        Map<String, Object> params = new HashMap<>();
        params.put("id", genre.getId());
        params.put("name", genre.getName());
        params.put("description", genre.getDescription());
        namedParameterJdbcOperations.update(sql, params);
    }

    @Override
    public Genre findById(long id) {
        final String sql = "select id, name, description from tgenre where id = :id";
        return namedParameterJdbcOperations.queryForObject(sql, Collections.singletonMap("id", id), (rs, i) -> {
            String name = rs.getString("name");
            String description = rs.getString("description");
            return new Genre(id, name, description);
        });
    }

    @Override
    public Map<Long, Genre> getGenreIdToGenreMap() {
        final String sql = "select id, name, description from tgenre";
        return namedParameterJdbcOperations.query(sql, rs -> {
            Map<Long, Genre> res = new HashMap<>();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                Genre genre = new Genre(id, name, description);
                res.put(id, genre);
            }
            return res;
        });
    }
}
