package org.example.dao;

import java.sql.*;


public class AnimalDaoImpl implements AnimalDao{
    private final Connection connection;

    public AnimalDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createTable() throws SQLException{
        Statement statement = connection.createStatement();
        statement.execute("create table if not exists food " +
                "(id integer not null auto_increment, " +
                "name varchar(100), description varchar(100)," +
                "calories_per_100 varchar(100)," +
                "expiration_date date," +
                " primary key (id))");
    }

    @Override
    public void dropTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("drop table animals");
    }
}
