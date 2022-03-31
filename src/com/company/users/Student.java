package com.company.users;

import com.company.assignments.Work;

import java.util.ArrayList;
import java.util.Date;

public class Student extends Person{
    private int classId;
    private ArrayList<Work> works = new ArrayList<>();

    public Student(){
        super();
    }

    public Student(String name, String surname, int age, String email, String password, Date birthday, int classId, ArrayList<Work> works) {
        super(name, surname, age, email, password, birthday);
        this.classId = classId;
        this.works = works;
    }

    public Student(String name, String surname, int age, String email, String password, Date birthday, int classId) {
        super(name, surname, age, email, password, birthday);
        this.classId = classId;
    }

    public int getClassId() {
        return classId;
    }
    public void setClassId(int classId) {
        this.classId = classId;
    }

    public ArrayList<Work> getWorks() {
        return works;
    }

    public void setWorks(ArrayList<Work> works) {
        this.works = works;
    }

    @Override
    public String toString() {
        return "Student{" +
                "classId=" + classId +
                ", works=" + works +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }
}
