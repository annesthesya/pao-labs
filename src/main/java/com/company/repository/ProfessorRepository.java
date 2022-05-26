package com.company.repository;

import com.company.config.DBConfig;
import com.company.users.Professor;

import java.sql.*;

import static com.company.config.DBConfig.closeDatabaseConnection;

public class ProfessorRepository {

    private static ProfessorRepository instance;

    private ProfessorRepository(){

    }

    public static ProfessorRepository createProfessorRepository(){
        if(instance == null){
            instance = new ProfessorRepository();
        }
        return instance;
    }

    public void createProfessorTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS professor " +
                "(id int PRIMARY KEY AUTO_INCREMENT, " +
                "name varchar(30), " +
                "surname varchar(30), " +
                "age int, " +
                "email varchar(100), " +
                "password varchar(30), " +
                "birthday date)";

        Connection connection = DBConfig.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeDatabaseConnection();
    }

    public void insertProfessor(Professor p) {
        String insertPersonSql = "INSERT INTO professor(name, surname, age, email, password, birthday)" +
                " VALUES(?, ?, ?, ?, ?, ?)";

        Connection connection = DBConfig.getDatabaseConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql)) {
            preparedStatement.setString(1, p.getName());
            preparedStatement.setString(2, p.getSurname());
            preparedStatement.setInt(3, p.getAge());
            preparedStatement.setString(4, p.getEmail());
            preparedStatement.setString(5, p.getPassword());
            preparedStatement.setDate(6, new java.sql.Date(p.getBirthday().getTime()));
            preparedStatement.executeUpdate();
            closeDatabaseConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}