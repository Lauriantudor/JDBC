package org.example.dao;

import org.example.model.Animal;

import java.sql.SQLException;

public interface AnimalDao {

    public void createTable() throws SQLException;


    public void dropTable() throws SQLException;

    public void  create(Animal animal)throws SQLException;
}
