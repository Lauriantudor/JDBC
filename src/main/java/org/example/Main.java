package org.example;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private final static Logger LOGGER = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        String dbLocation = "localhost:3306";
        String dbName = "jdbc_db";
        String dbUser = "root";
        String dbPassword = "root";

        MysqlDataSource dataSource = new MysqlDataSource();
        // jdbc:mysql://localhost:3306/jdbc_db
        dataSource.setURL("jdbc:mysql://"+ dbLocation+ "/"+dbName);
        dataSource.setUser(dbUser);
        dataSource.setPassword(dbPassword);

        try {
            Connection connection = dataSource.getConnection();
        }catch(SQLException sqlException){
           LOGGER.log(Level.SEVERE ,"Error when connecting to db "+dbName
                        + " from "+ dbLocation
                        + " with user "+ dbUser);
           LOGGER.log(Level.SEVERE, sqlException.getStackTrace().toString());
        }


    }
}