package com.company.subjects;

import java.util.ArrayList;
import java.util.List;

public class Class {
    private static int idCounter = 0;
    private final int id;
    private String name;
    private int studentNumber;
    private int representativeId;
    private List<Integer> studentIdList = new ArrayList<>();
    private List<Integer> courseIdList = new ArrayList<>();

    public Class() {
        idCounter ++;
        this.id = idCounter;
    }

    public Class(String name, int studentNumber, int representativeId, List<Integer> studentIdList, List<Integer> courseIdList) {
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

    public List<Integer> getStudentIdList() {
        return studentIdList;
    }
    public void setStudentIdList(List<Integer> studentIdList) {
        this.studentIdList = studentIdList;
    }

    public List<Integer> getCourseIdList() {
        return courseIdList;
    }
    public void setCourseIdList(List<Integer> courseIdList) {
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
