package src.com.company.users;

import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable {
    protected static int idCounter = 0;
    protected final int id;
    protected String name = "[FIRST_NAME]";
    protected String surname = "[LAST_NAME]";
    protected int age = 0;
    protected String email = null;
    protected String password = "admin";
    protected Date birthday = null;

    public Person(){
        idCounter ++;
        this.id = idCounter;
    }
    public Person(String name, String surname, int age, String email, String password, Date birthday) {
        idCounter ++;
        this.id = idCounter;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getSurname(){
        return surname;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }

    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age = age;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
