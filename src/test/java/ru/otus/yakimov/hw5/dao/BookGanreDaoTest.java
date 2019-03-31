package ru.otus.yakimov.hw5.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.yakimov.hw5.domain.Ganre;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
public class BookGanreDaoTest {

    @Autowired
    private GanreDao ganreDao;

    @Test
    public void shouldReadGanreFromDb(){
        long id = 1;
        String name = "educational";
        String description = "desc";
        Ganre expected = new Ganre(id, name, description);
        Ganre actual = ganreDao.findById(id);
        assertThat(actual, is(notNullValue()));
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    @Rollback
    public void shouldPersistGanreToDb(){
        Ganre expected = new Ganre(2, "testGanre", "desc");
        ganreDao.add(expected);
        Ganre actual = ganreDao.findById(expected.getId());
        assertThat(actual, is(notNullValue()));
        assertThat(actual, is(equalTo(expected)));
    }

}