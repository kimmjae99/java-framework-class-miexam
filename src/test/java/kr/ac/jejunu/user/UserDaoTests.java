package kr.ac.jejunu.user;

import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {

    private static UserDao userDao;

    @BeforeAll
    public static void setup(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);

        userDao = applicationContext.getBean("getUserDao", UserDao.class);
    }
    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Long id = 1l;
        String name = "김민재";
        String password = "1111";

        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void insert() throws SQLException, ClassNotFoundException {
        String name = "Aiden";
        String password = "1234";
        User user = new User();
        user.setName(name);
        user.setPassword(password);

        userDao.insert(user);

        User insertedUser = userDao.get(user.getId());

        assertThat(insertedUser.getName(), is(name));
        assertThat(insertedUser.getPassword(), is(password));

    }

    @Test
    public void update() throws SQLException, ClassNotFoundException {
        String name = "Aiden";
        String password = "1234";
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userDao.insert(user);

        String updatedName = "Aiden2";
        String updatedPassword = "11111";

        user.setName(updatedName);
        user.setPassword(updatedPassword);

        userDao.update(user);

        User updatedUser = userDao.get(user.getId());

        assertThat(updatedUser.getName(), is(updatedName));
        assertThat(updatedUser.getPassword(), is(updatedPassword));

    }

    @Test
    public void delete() throws SQLException, ClassNotFoundException {
        String name = "Aiden";
        String password = "1234";
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userDao.insert(user);

        userDao.delete(user.getId());

        User deletedUser = userDao.get(user.getId());

        assertThat(deletedUser, IsNull.nullValue());
    }
}
