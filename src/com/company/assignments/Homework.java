package com.company.assignments;

import java.util.Date;

public class Homework extends Assignment{

    public Homework(int courseId, double weight, Date deadline, String details) {
        super(courseId, weight, deadline, details);
    }

    @Override
    public String toString() {
        return "Homework{" +
                "courseId=" + courseId +
                ", weight=" + weight +
                ", deadline=" + deadline +
                ", details='" + details + '\'' +
                '}';
    }
}
