package com.company.repository;

import com.company.config.DBConfig;
import com.company.users.Person;
import com.company.users.Student;

import java.sql.*;

import static com.company.config.DBConfig.closeDatabaseConnection;

public class StudentRepository {

    private static StudentRepository instance;

    private StudentRepository(){

    }

    public static StudentRepository createStudentRepository(){
        if(instance == null){
            instance = new StudentRepository();
        }
        return instance;
    }

    public void createStudentTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS student " +
                "(id int PRIMARY KEY AUTO_INCREMENT, " +
                "name varchar(30), " +
                "surname varchar(30), " +
                "age int, " +
                "email varchar(100), " +
                "password varchar(30), " +
                "birthday date, " +
                "class_id int)";

        Connection connection = DBConfig.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeDatabaseConnection();

    }

    public void insertStudent(Student s) {
        String insertStudentSql = "INSERT INTO student(name, surname, age, email, password, birthday, class_id)" +
                " VALUES(?, ?, ?, ?, ?, ?, ?)";

        Connection connection = DBConfig.getDatabaseConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertStudentSql)) {
            preparedStatement.setString(1, s.getName());
            preparedStatement.setString(2, s.getSurname());
            preparedStatement.setInt(3, s.getAge());
            preparedStatement.setString(4, s.getEmail());
            preparedStatement.setString(5, s.getPassword());
            preparedStatement.setDate(6, new java.sql.Date(s.getBirthday().getTime()));
            preparedStatement.setInt(7, s.getClassId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeDatabaseConnection();
    }

    private Student mapToStudent(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new Student(resultSet.getString(1), resultSet.getString(2),
                    resultSet.getInt(3), resultSet.getString(3),
                    resultSet.getString(3), new java.util.Date(resultSet.getDate(3).getTime()),
                    resultSet.getInt(3));
        }
        return null;
    }

    public Student getStudentById(int id) {
        String selectSql = "SELECT * FROM person WHERE id=?";

        Connection connection = DBConfig.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            closeDatabaseConnection();
            return mapToStudent(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeDatabaseConnection();
        return null;
    }

    public void updateStudent(String email, String password, int id) {
        String updateStudentSql = "UPDATE person SET email = ? password = ? WHERE id=?";

        Connection connection = DBConfig.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateStudentSql)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeDatabaseConnection();
    }

    public void deleteStudent(int id){
        String deleteStudentSql ="DELETE FROM student WHERE id = ?";
        Connection connection = DBConfig.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteStudentSql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeDatabaseConnection();
    }

}