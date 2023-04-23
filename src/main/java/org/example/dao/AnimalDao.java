package org.example.dao;

import java.sql.SQLException;

public interface AnimalDao {

    public void createTable() throws SQLException;


    public void dropTable() throws SQLException;
}
