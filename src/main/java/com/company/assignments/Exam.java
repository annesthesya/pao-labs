package com.company.assignments;

import java.util.Date;

public class Exam extends Assignment {
    private int duration;
    private int supervisorId;
    private boolean oral;

    public Exam(int courseId, double weight, Date deadline, String details, int duration, int supervisorId, boolean oral) {
        super(courseId, weight, deadline, details);
        this.duration = duration;
        this.supervisorId = supervisorId;
        this.oral = oral;
    }

    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getSupervisorId() {
        return supervisorId;
    }
    public void setSupervisorId(int supervisorId) {
        this.supervisorId = supervisorId;
    }

    public boolean isOral() {
        return oral;
    }
    public void setOral(boolean oral) {
        this.oral = oral;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "duration=" + duration +
                ", supervisorId=" + supervisorId +
                ", oral=" + oral +
                ", id=" + id +
                ", courseId=" + courseId +
                ", weight=" + weight +
                ", deadline=" + deadline +
                ", details='" + details + '\'' +
                '}';
    }
}
