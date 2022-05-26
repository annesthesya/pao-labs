package com.company.assignments;

import java.util.Date;

public class Work {
    private static int idCounter = 0;
    private final int id;
    private double grade = 0;
    private int assignmentId;
    private int studentId;
    private Date turnInTime;
    private boolean graded = false;
    private boolean late = false;

    public Work(){
        idCounter ++;
        this.id = idCounter;
    }

    public Work(int assignmentId, int studentId, Date turnInTime, boolean late) {
        idCounter ++;
        this.id = idCounter;
        this.assignmentId = assignmentId;
        this.studentId = studentId;
        this.turnInTime = turnInTime;
        this.late = late;
    }

    public int getId() {
        return id;
    }

    public double getGrade() {
        return grade;
    }
    public void setGrade(double grade) {
        this.grade = grade;
    }

    public int getAssignmentId() {
        return assignmentId;
    }
    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public Date getTurnInTime() {
        return turnInTime;
    }
    public void setTurnInTime(Date turnInTime) {
        this.turnInTime = turnInTime;
    }

    public boolean isGraded() {
        return graded;
    }
    public void setGraded(boolean graded) {
        this.graded = graded;
    }

    public boolean isLate() {
        return late;
    }
    public void setLate(boolean late) {
        this.late = late;
    }

    @Override
    public String toString() {
        return "Work{" +
                "grade=" + grade +
                ", turnInTime=" + turnInTime +
                ", graded=" + graded +
                '}';
    }
}
