package com.company.subjects;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private static int idCounter;
    private final int id;
    private int subjectId;
    private int professorId;
    private int weekDay;
    private String hour;
    private String location;
    private List<Integer> assignmentList = new ArrayList<>();

    public Course() {
        idCounter++;
        this.id = idCounter;
    }

    public Course(int subjectId, int professorId, int weekDay, String hour, String location) {
        idCounter++;
        this.id = idCounter;
        this.subjectId = subjectId;
        this.professorId = professorId;
        this.weekDay = weekDay;
        this.hour = hour;
        this.location = location;
    }

    public Course(int subjectId, int professorId,int weekDay, String hour, ArrayList<Integer> assignmentList, String location) {
        idCounter++;
        this.id = idCounter;
        this.subjectId = subjectId;
        this.professorId = professorId;
        this.weekDay = weekDay;
        this.hour = hour;
        this.assignmentList = assignmentList;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public int getSubjectId() {
        return subjectId;
    }
    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getProfessorId() {
        return professorId;
    }
    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    public int getWeekDay() {
        return weekDay;
    }
    public void setWeekDay(int weekDay) {
        this.weekDay = weekDay;
    }

    public String getHour() {
        return hour;
    }
    public void setHour(String hour) {
        this.hour = hour;
    }

    public List<Integer> getAssignmentList() {
        return assignmentList;
    }
    public void setAssignmentList(ArrayList<Integer> assignmentList) {
        this.assignmentList = assignmentList;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Course{" +
                "professorId=" + professorId +
                ", weekDay=" + weekDay +
                ", hour='" + hour + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
