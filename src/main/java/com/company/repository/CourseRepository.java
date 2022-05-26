package com.company.repository;

import com.company.config.DBConfig;
import com.company.subjects.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static com.company.config.DBConfig.closeDatabaseConnection;

public class CourseRepository {

    private static CourseRepository instance;

    private CourseRepository(){

    }

    public static CourseRepository createCourseRepository(){
        if(instance == null){
            instance = new CourseRepository();
        }
        return instance;
    }

    public void createCourseTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS Course " +
                "(id int PRIMARY KEY AUTO_INCREMENT, " +
                "subject_id int, " +
                "professor_id int, " +
                "week_day int, " +
                "hour varchar(10), " +
                "location varchar(100))";

        Connection connection = DBConfig.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeDatabaseConnection();
    }

    public void insertCourse(Course c) {
        String insertPersonSql = "INSERT INTO course(subject_id, professor_id, week_day, hour, location)" +
                " VALUES(?, ?, ?, ?, ?, ?)";

        Connection connection = DBConfig.getDatabaseConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql)) {
            preparedStatement.setInt(1, c.getSubjectId());
            preparedStatement.setInt(2, c.getProfessorId());
            preparedStatement.setInt(3, c.getWeekDay());
            preparedStatement.setString(4, c.getHour());
            preparedStatement.setString(4, c.getLocation());
            preparedStatement.executeUpdate();
            closeDatabaseConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}