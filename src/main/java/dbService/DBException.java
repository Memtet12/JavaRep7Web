package dbService;

import java.sql.SQLException;

public class DBException extends Exception {
    public DBException(SQLException throwable) {
        super(throwable);
    }
}
