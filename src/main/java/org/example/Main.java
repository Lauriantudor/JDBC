package org.example;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;
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
            Statement statement = connection.createStatement();
            statement.execute("create table if not exists animals " +
                    "(id integer not null auto_increment, " +
                    "name varchar(100), species varchar(100), primary key (id))");


            statement.execute("insert into animals (name, species) values (\"Lucky\", \"Dog\")");
            statement.execute("update animals set name=\"dog1\"  where id=1");

            statement.execute("create table if not exists food " +
                    "(id integer not null auto_increment, " +
                    "name varchar(100), description varchar(100)," +
                    "calories_per_100 varchar(100)," +
                    "expiration_date date," +
                    " primary key (id))");

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into food (name, description, calories_per_100, expiration_date) values (?, ?, ?, ?)");
            preparedStatement.setString(1, "ciocolată");
            preparedStatement.setString(2, "ciocolată de casă");
            preparedStatement.setInt(3, 550);
            Date expirationDate = Date.valueOf("2024-10-12");
            preparedStatement.setDate(4, expirationDate);
            preparedStatement.execute();

            preparedStatement.setString(1, "alune");
            preparedStatement.setString(2, "punga de 500g grame de alune prajite");
            preparedStatement.setInt(3,600);
            preparedStatement.setDate(4,expirationDate);


            ResultSet rs = statement.executeQuery("select * from animals");
            while (rs.next() == true) {
                System.out.println(rs.getInt(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
            }



            ResultSet rs2 = statement.executeQuery("SELECT * FROM food");
            System.out.println("Foods: ");
            while (rs2.next()){
                System.out.print("id. " + rs2.getInt(1) + " - ");
                System.out.print(rs2.getString(2) + " - ");
                System.out.print(rs2.getString(3) + " - ");
                System.out.print(rs2.getInt(4) +"kcal per 100g - ");
                System.out.print("expiră la " + rs2.getDate(5));
                System.out.println();
            }
            statement.execute("drop table animals");
            statement.execute("drop table food");



        }catch(SQLException sqlException){
           sqlException.printStackTrace();
        }


    }
}