package com.company.users;

import java.util.ArrayList;
import java.util.Date;

public class Professor extends Person{
    private ArrayList<Integer> courses = new ArrayList<>();

    public Professor() {
        super();
    }
    public Professor(String name, String surname, int age, String email, String password, Date birthday) {
        super(name, surname, age, email, password, birthday);
    }
    public Professor(int id, String name, String surname, int age, String email, String password, Date birthday, ArrayList<Integer> courses) {
        super(name, surname, age, email, password, birthday);
        this.courses = courses;
    }

    public ArrayList<Integer> getCourses() {
        return courses;
    }
    public void setCourses(ArrayList<Integer> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
