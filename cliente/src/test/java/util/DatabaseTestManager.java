package util;

import org.apache.ibatis.jdbc.ScriptRunner;

import br.com.desafio.builder.cliente.config.TestPropertiesExtractor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseTestManager {

    public DatabaseTestManager() {

    }

    public static void createDatabase() throws SQLException, FileNotFoundException {
        Connection connection = getConnection();
        cleanDatabase(connection);
        ScriptRunner sr = new ScriptRunner(connection);
        Reader reader = new BufferedReader(new FileReader("src/test/resources/db_test/data_test.sql"));
        sr.runScript(reader);
        sr.closeConnection();
    }

    public static void cleanDatabase(Connection connection) throws SQLException, FileNotFoundException {
        boolean closeable = false;
        if(connection == null) {
            connection = getConnection();
            closeable = true;
        }
        ScriptRunner sr = new ScriptRunner(connection);
        Reader reader = new BufferedReader(new FileReader("src/test/resources/db_test/clean_database.sql"));
        sr.runScript(reader);
        if(closeable) {
            sr.closeConnection();
        }
    }

    private static Connection getConnection() throws SQLException {
    	String dbUrl = TestPropertiesExtractor.getProperty("url.for.h2");
    	String dbUser = TestPropertiesExtractor.getProperty("spring.datasource.username");
      	String dbPassword = TestPropertiesExtractor.getProperty("spring.datasource.password");
        Connection con =  DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        return con;
    }

}
