package ru.otus.yakimov.hw5.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.yakimov.hw5.dao.GanreDao;
import ru.otus.yakimov.hw5.domain.Ganre;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class GanreDaoImpl implements GanreDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public void add(Ganre ganre) {
        final String sql = "insert into tganre(id, name, description) values(:id, :name, :description)";
        Map<String, Object> params = new HashMap<>();
        params.put("id", ganre.getId());
        params.put("name", ganre.getName());
        params.put("description", ganre.getDescription());
        namedParameterJdbcOperations.update(sql, params);
    }

    @Override
    public Ganre findById(long id) {
        final String sql = "select id, name, description from tganre where id = :id";
        return namedParameterJdbcOperations.queryForObject(sql, Collections.singletonMap("id", id), (rs, i) -> {
            long ganreId = rs.getLong("id");
            String name = rs.getString("name");
            String description = rs.getString("description");
            return new Ganre(id, name, description);
        });
    }
}
