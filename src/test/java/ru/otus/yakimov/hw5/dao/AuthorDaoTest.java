package ru.otus.yakimov.hw5.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.yakimov.hw5.dao.impl.AuthorDaoImpl;
import ru.otus.yakimov.hw5.domain.Author;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@JdbcTest
@Import(AuthorDaoImpl.class)
public class AuthorDaoTest {

    @Autowired
    AuthorDao authorDao;

    @Test
    public void shouldReadAuthorFromDb(){
        Author expected = new Author(1, "Bloch", "Joshua");
        Author author = authorDao.findById(1);
        assertThat(author, notNullValue());
        assertThat(author, equalTo(expected));
    }

    @Test
    @Rollback
    public void shouldPersistAuthorToDB() {
        Author testData = new Author(2, "Ivanov", "Ivan");
        authorDao.add(testData);
        Author author = authorDao.findById(testData.getId());
        assertThat(author, notNullValue());
        assertThat(author, equalTo(testData));
    }
}