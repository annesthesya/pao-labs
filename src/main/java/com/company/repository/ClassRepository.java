package com.company.repository;

import com.company.config.DBConfig;
import com.company.subjects.Class;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static com.company.config.DBConfig.closeDatabaseConnection;

public class ClassRepository {

    private static ClassRepository instance;

    private ClassRepository(){

    }

    public static ClassRepository createClassRepository(){
        if(instance == null){
            instance = new ClassRepository();
        }
        return instance;
    }


    public void createClassTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS Class " +
                "(id int PRIMARY KEY AUTO_INCREMENT, " +
                "name varchar(30), " +
                "student_number int, " +
                "representative_id int)";

        Connection connection = DBConfig.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeDatabaseConnection();
    }

    public void insertClass(Class c) {
        String insertPersonSql = "INSERT INTO class(name, student_number, representative_id)" +
                " VALUES(?, ?, ?)";

        Connection connection = DBConfig.getDatabaseConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql)) {
            preparedStatement.setString(1, c.getName());
            preparedStatement.setInt(2, c.getStudentNumber());
            preparedStatement.setInt(3, c.getRepresentativeId());
            preparedStatement.executeUpdate();
            closeDatabaseConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}