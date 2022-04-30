package src.com.company.assignments;

import java.util.Date;

public class Homework extends Assignment{
    private int numberOfExercises;

    public Homework(int courseId, double weight, Date deadline, String details, int numberOfExercises) {
        super(courseId, weight, deadline, details);
        this.numberOfExercises = numberOfExercises;
    }

    public int getNumberOfExercises() {
        return numberOfExercises;
    }

    public void setNumberOfExercises(int numberOfExercises) {
        this.numberOfExercises = numberOfExercises;
    }

    @Override
    public String toString() {
        return "Homework{" +
                "courseId=" + courseId +
                ", weight=" + weight +
                ", deadline=" + deadline +
                ", details='" + details + '\'' +
                '}';
    }
}
