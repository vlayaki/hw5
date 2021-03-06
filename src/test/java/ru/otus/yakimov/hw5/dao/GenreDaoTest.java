package ru.otus.yakimov.hw5.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.yakimov.hw5.dao.impl.GenreDaoImpl;
import ru.otus.yakimov.hw5.domain.Genre;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(SpringRunner.class)
@JdbcTest
@Import(GenreDaoImpl.class)
public class GenreDaoTest {

    @Autowired
    private GenreDao genreDao;

    @Test
    public void shouldReadGenreFromDb() {
        long id = 1;
        String name = "educational";
        String description = "desc";
        Genre expected = new Genre(id, name, description);
        Genre actual = genreDao.findById(id);
        assertThat(actual, is(notNullValue()));
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    @Rollback
    public void shouldPersistGenreToDb() {
        Genre expected = new Genre(2, "testGenre", "desc");
        genreDao.add(expected);
        Genre actual = genreDao.findById(expected.getId());
        assertThat(actual, is(notNullValue()));
        assertThat(actual, is(equalTo(expected)));
    }

}