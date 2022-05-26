package com.company.users;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Professor extends Person {
    private List<Integer> courses = new ArrayList<>();

    public Professor() {
        super();
    }
    public Professor(String name, String surname, int age, String email, String password, Date birthday) {
        super(name, surname, age, email, password, birthday);
    }
    public Professor(int id, String name, String surname, int age, String email, String password, Date birthday, List<Integer> courses) {
        super(name, surname, age, email, password, birthday);
        this.courses = courses;
    }

    public List<Integer> getCourses() {
        return courses;
    }
    public void setCourses(List<Integer> courses) {
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
