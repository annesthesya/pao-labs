package com.company.subjects;

import java.util.ArrayList;

public class Class {
    private static int idCounter = 0;
    private final int id;
    private String name;
    private int studentNumber;
    private int representativeId;
    private ArrayList<Integer> studentIdList;
    private ArrayList<Integer> courseIdList;

    public Class(String name, int studentNumber, int representativeId, ArrayList<Integer> studentIdList,ArrayList<Integer> courseIdList) {
        idCounter ++;
        this.id = idCounter;
        this.name = name;
        this.studentNumber = studentNumber;
        this.representativeId = representativeId;
        this.studentIdList = studentIdList;
        this.courseIdList = courseIdList;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getStudentNumber() {
        return studentNumber;
    }
    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public int getRepresentativeId() {
        return representativeId;
    }
    public void setRepresentativeId(int representativeId) {
        this.representativeId = representativeId;
    }

    public ArrayList<Integer> getStudentIdList() {
        return studentIdList;
    }
    public void setStudentIdList(ArrayList<Integer> studentIdList) {
        this.studentIdList = studentIdList;
    }

    public ArrayList<Integer> getCourseIdList() {
        return courseIdList;
    }
    public void setCourseIdList(ArrayList<Integer> courseIdList) {
        this.courseIdList = courseIdList;
    }

    @Override
    public String toString() {
        return "Class{" +
                "name='" + name + '\'' +
                ", studentNumber=" + studentNumber +
                ", representative=" + representativeId +
                '}';
    }
}
