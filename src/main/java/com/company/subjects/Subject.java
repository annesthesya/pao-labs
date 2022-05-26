package com.company.subjects;

public class Subject {
    private static int idCounter = 0;
    private final int id;
    private String name;

    public Subject() {
        idCounter++;
        this.id = idCounter;
    }

    public Subject(String name) {
        idCounter++;
        this.id = idCounter;
        this.name = name;
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

}
