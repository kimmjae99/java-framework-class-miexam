package kr.ac.jejunu.user;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private final JdbcContext jdbcContext;


    public UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User get(Long id) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new GetStatementStrategy(id);
        return jdbcContext.get(statementStrategy);
    }

    public void insert(User user) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new InsertStatementStrategy(user);
        jdbcContext.insert(user, statementStrategy);
    }

    public void update(User user) throws SQLException {
        StatementStrategy statementStrategy = new UpdateStatementStrategy(user);
        jdbcContext.update(statementStrategy);
    }

    public void delete(Long id) throws SQLException {
        StatementStrategy statementStrategy = new DeleteStatementStrategy(id);

        jdbcContext.update(statementStrategy);
    }

    private User get(StatementStrategy statementStrategy) throws SQLException {


        return jdbcContext.get(statementStrategy);
    }

    private void insert(User user, StatementStrategy statementStrategy) throws SQLException {
        jdbcContext.insert(user, statementStrategy);
    }

    private void update(StatementStrategy statementStrategy) throws SQLException {
        jdbcContext.update(statementStrategy);
    }
}
