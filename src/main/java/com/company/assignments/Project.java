package com.company.assignments;

import java.util.Date;

public class Project extends Assignment {
    private int studentNumber = 1;

    public Project(int courseId, double weight, Date deadline, String details, int studentNumber) {
        super(courseId, weight, deadline, details);
        this.studentNumber = studentNumber;
    }

    public int getStudentNumber() {
        return studentNumber;
    }
    public void setStudentNumber(int workedOnBy) {
        this.studentNumber = workedOnBy;
    }

    @Override
    public String toString() {
        return "Project{" +
                "courseId=" + courseId +
                ", weight=" + weight +
                ", deadline=" + deadline +
                ", details='" + details + '\'' +
                '}';
    }
}
