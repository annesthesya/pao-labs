package com.company.assignments;

import java.util.Date;

public class Assignment {
    private static int idCounter = 0 ;
    private final int id;
    private int courseId;
    private double weight;
    private Date deadline;
    private String details;

    public Assignment(int courseId, double weight, Date deadline, String details) {
        idCounter++;
        this.id = idCounter;
        this.courseId = courseId;
        this.weight = weight;
        this.deadline = deadline;
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public int getCourseId() {
        return courseId;
    }
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Date getDeadline() {
        return deadline;
    }
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }



}
