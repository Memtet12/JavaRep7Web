package dbService.dao;

import dbService.dataSets.UsersDataSet;
import dbService.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;


public class UsersDAO {

    private Executor executor;

    public UsersDAO(Connection connection) {
        this.executor = new Executor(connection);
    }
    public void updateUserPassword(long id, String newPassword) throws SQLException {
        executor.execUpdate("UPDATE Users SET password='" + newPassword + "' WHERE ID=" + id);
    }

    public UsersDataSet get(long id) throws SQLException {
        return executor.execQuery("SELECT * FROM Users WHERE id=" + id, result -> {
            if (!result.next()) return null;
            return new UsersDataSet(
                    result.getLong("ID"),
                    result.getString("name"),
                    result.getString("password"),
                    result.getString("email")
            );
        });
    }

    public UsersDataSet getUserByName(String name) throws SQLException {
        return executor.execQuery("SELECT * FROM Users WHERE name='" + name + "'", result -> {
            if (!result.next()) return null;
            return new UsersDataSet(
                    result.getLong("ID"),
                    result.getString("name"),
                    result.getString("password"),
                    result.getString("email")
            );
        });
    }

    public Long getUserId(String name) throws SQLException {
        return executor.execQuery("SELECT ID FROM Users WHERE name='" + name + "'", result -> {
            return result.next() ? result.getLong("ID") : null;
        });
    }

    public void insertUser(String name, String password, String email) throws SQLException {
        String sql = String.format(
                "INSERT INTO Users (name, password, email) VALUES ('%s', '%s', '%s')",
                name, password, email
        );
        executor.execUpdate(sql);
    }


    public void createTable() throws SQLException {
        executor.execUpdate("CREATE TABLE IF NOT EXISTS Users (" +
                "ID INTEGER auto_increment NOT NULL, " +
                "name varchar(50) NOT NULL, " +
                "password varchar(50) NOT NULL, " +
                "email varchar(100) NOT NULL, " +
                "PRIMARY KEY (ID), " +
                "UNIQUE KEY (name), " +
                "UNIQUE KEY (email)" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci");
    }

    public void dropTable() throws SQLException {
        executor.execUpdate("DROP TABLE IF EXISTS Users");
    }
}