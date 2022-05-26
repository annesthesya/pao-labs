package src.com.company.users;

import java.io.Serializable;
import java.util.Date;

public class Action implements Serializable {

    private static int idCounter = 0;
    private final int id;
    private int userId;
    private String function;
    private Date timeEntered;

    public Action() {
        idCounter++;
        this.id = idCounter;
    }

    public Action (int userId, String function, Date timeEntered) {
        idCounter++;
        this.id = idCounter;
        this.userId = userId;
        this.function = function;
        this.timeEntered = timeEntered;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public Date getTimeEntered() {
        return timeEntered;
    }

    public void setTimeEntered(Date timeEntered) {
        this.timeEntered = timeEntered;
    }
}
