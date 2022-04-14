package com.company.services;

import com.company.assignments.*;
import com.company.subjects.Class;
import com.company.subjects.Course;
import com.company.subjects.Subject;
import com.company.users.Professor;
import com.company.users.Student;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Service {
    private final Map<Integer, Class> classMap = new HashMap<>();
    private final Map<Integer, Course> courseMap = new HashMap<>();
    private final Map<Integer, Student> studentMap = new HashMap<>();
    private final Map<Integer, Professor> professorMap = new HashMap<>();
    private final Map<Integer, Subject> subjectMap = new HashMap<>();
    private final Map<Integer, Assignment> assignmentMap = new HashMap<>();
    private final Scanner scan = new Scanner(System.in); //ok
    private static Service instance; //ok

    private Service() throws ParseException, InterruptedException {
        init();
    }

    public static Service createInstance() throws ParseException, InterruptedException {
        if (instance == null){
            instance = new Service();
        }
        return instance;
    }

    private void cls() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void studentListGenerator(int id) throws InterruptedException {
        cls();
        if(! classMap.containsKey(id) ){
            System.out.println("That class ID does not exist.");
        }
        System.out.println("CLASS " + classMap.get(id).getName());

        ArrayList<Integer> students = classMap.get(id).getStudentIdList();

        System.out.println("\n\tSTUDENT LIST:");
        for (Integer student : students) {
            Student currentStudent = studentMap.get(student);
            System.out.println("\n\nNAME: " + currentStudent.getName() + " " + currentStudent.getSurname());
            System.out.println("\nAGE: " + currentStudent.getAge());
        }
    }

    private void viewScheduleC() throws InterruptedException {
        cls();
        System.out.println("\n\tCLASS SCHEDULE GENERATOR" +
                "\nID:");
        int id = scan.nextInt();
        scan.nextLine();

        Class targetClass = classMap.get(id);

        System.out.println("ok 1");


        ArrayList<ArrayList<String>> schedule = new ArrayList<>(7);

        System.out.println("ok 2");

        for (int i = 0; i < 7; i++)
            schedule.add(new ArrayList<>());

        for (var entry : courseMap.entrySet()){
            Course currentCourse = entry.getValue();
            if (targetClass.getCourseIdList().contains(currentCourse.getId())) {
                String hour = currentCourse.getHour();
                String location = currentCourse.getLocation();
                String subjectName = subjectMap.get(currentCourse.getSubjectId()).getName();
                String professorName = professorMap.get(currentCourse.getProfessorId()).getName() + " " +
                        professorMap.get(currentCourse.getProfessorId()).getSurname();
                String eventLog = hour + " - " + subjectName + " in " + location + " with " + professorName +"\n";
                schedule.get(currentCourse.getWeekDay() - 1).add(eventLog);
                System.out.println("ok 3");
            }
        }

        System.out.println("\tSchedule of class " + classMap.get(id).getName()+"\n");

        for (int i = 0; i < 7; i++){
            if (! schedule.get(i).isEmpty()) {
                Collections.sort(schedule.get(i));

                if(i == 0){
                    System.out.println("MONDAY");
                }
                else if (i==1){
                    System.out.println("TUESDAY");
                }
                else if (i==2){
                    System.out.println("WEDNESDAY");
                }
                else if (i==3){
                    System.out.println("THURSDAY");
                }
                else if (i==4){
                    System.out.println("FRIDAY");
                }
                else if (i==5){
                    System.out.println("SATURDAY");
                }
                else {
                    System.out.println("SUNDAY");
                }

                for (var day : schedule.get(i)) {
                    System.out.println(day);
                }
            }
        }

        }

        private void viewScheduleP() throws InterruptedException { //ok - tested
            cls();
            System.out.println("\n\tPROFESSOR SCHEDULE GENERATOR" +
                    "\nID:");
            int id = scan.nextInt();
            scan.nextLine();

            String professorName = professorMap.get(id).getName() + " " + professorMap.get(id).getSurname();

            ArrayList<ArrayList<String>> schedule = new ArrayList<>(7);

            for (int i = 0; i < 7; i++)
                schedule.add(new ArrayList<>());

            for (var entry : courseMap.entrySet()){
                Course currentCourse = entry.getValue();
                if (currentCourse.getProfessorId() == id) {
                    String hour = currentCourse.getHour();
                    String location = currentCourse.getLocation();
                    String subjectName = subjectMap.get(currentCourse.getSubjectId()).getName();
                    String eventLog = hour + " - " + subjectName + " in " + location + "\n";
                    schedule.get(currentCourse.getWeekDay() - 1).add(eventLog);
                }
            }

            System.out.println("\tSchedule of " + professorName);


            for (int i = 0; i < 7; i++){
                if (! schedule.get(i).isEmpty()) {
                    Collections.sort(schedule.get(i));

                    if(i == 0){
                        System.out.println("MONDAY");
                    }
                    else if (i==1){
                        System.out.println("TUESDAY");
                    }
                    else if (i==2){
                        System.out.println("WEDNESDAY");
                    }
                    else if (i==3){
                        System.out.println("THURSDAY");
                    }
                    else if (i==4){
                        System.out.println("FRIDAY");
                    }
                    else if (i==5){
                        System.out.println("SATURDAY");
                    }
                    else {
                        System.out.println("SUNDAY");
                    }

                    for (var day : schedule.get(i)) {
                        System.out.println(day);
                    }
                }
            }
    }

    private void viewSchedule() throws InterruptedException { //ok - tested
        cls();
        System.out.println("\n\tSCHEDULE GENERATOR" +
                "\n1.Class schedule;" +
                "\n2.Professor schedule.");

        int choice = scan.nextInt();
        scan.nextLine();

        switch(choice){
            case 1: {
                viewScheduleC();
                break;
            }
            case 2: {
                viewScheduleP();
                break;
            }
        }

    }

    private void viewWorks(int id) throws InterruptedException {//ok - NOT tested
        cls();
        if (! studentMap.containsKey(id)){
            System.out.println("Wrong ID!");
            viewWorksP();
        }
        else {
            Student targetStudent = studentMap.get(id);
            if (targetStudent.getWorks().isEmpty()){
                System.out.println("This student has not submitted any works.");
            }
            else {
                WorkComparator workC = new WorkComparator();
                targetStudent.getWorks().sort(workC);
                for (var work : targetStudent.getWorks()) {
                    System.out.println(work.toString());
                }
            }
        }
    }

    private void viewDeadlines(int id) {//ok - tested
        Class targetClass = classMap.get(id);
        System.out.println("DEADLINES OF " + targetClass.getName());
        Date today = new Date(); //gets the current date
        ArrayList<Assignment> deadlines =  new ArrayList<>();

        for(var courseId : targetClass.getCourseIdList()){
            Course currentCourse = courseMap.get(courseId);
            for (var assignmentId : currentCourse.getAssignmentList()) {
                if (assignmentMap.get(assignmentId).getDeadline().compareTo(today) > 0){ //checks if the deadline is
                    //after the current date
                    deadlines.add(assignmentMap.get(assignmentId));
                }
            }
        }

        AssignmentComparator assignmentC = new AssignmentComparator();
        deadlines.sort(assignmentC);
        for (var deadline : deadlines){
            System.out.println(deadline.getDeadline() + " : " + deadline.getDetails());
        }
    }

//    ------------------------------------------------------------PROFESSOR METHODS-------------------------------------

    private void editProfessorData(int id) throws InterruptedException, ParseException { //ok - tested
        Professor currentProfessor = professorMap.get(id);
        cls();
        System.out.println("\tEDIT YOUR DATA");
        System.out.println("\nEMAIL:");
        String email = scan.nextLine();
        System.out.println("\nPASSWORD:");
        String password = scan.nextLine();
        if(currentProfessor.getPassword().equals(password)) {
            System.out.println("\nWARNING! The password is the same as the current one.");
        }
        currentProfessor.setEmail(email);
        currentProfessor.setPassword(password);
        professorMap.replace(id, currentProfessor);
        System.out.println("SUMMARY:");
        System.out.println(professorMap.get(id).toString());
        professorMenu(id);
    }

    private void studentEnroll() throws ParseException, InterruptedException { // ok - tested
        cls();
        System.out.println("\n\tENROLLING NEW STUDENT");
        System.out.println("\nNAME:");
        String name = scan.nextLine();
        System.out.println("\nSURNAME:");
        String surname = scan.nextLine();
        System.out.println("\nEMAIL:");
        String email = scan.nextLine();
        System.out.println("\nPASSWORD:");
        String password = scan.nextLine();
        System.out.println("\nBIRTHDAY(DD-MM-YYYY FORMAT):");
        String birthday = scan.nextLine();
        System.out.println("\nCLASS ID:");
        int classId = scan.nextInt();
        scan.nextLine();
        Student newStudent = new Student(name, surname,2022 - Integer.parseInt(birthday.substring(birthday.length() - 4)),
                email, password, new SimpleDateFormat("dd-MM-yyyy").parse(birthday), classId);
        studentMap.put(newStudent.getId(),newStudent);
        System.out.println("SUMMARY:" + studentMap.get(newStudent.getId()).toString());
    }

    private void addAssignment() throws ParseException { //ok - tested
        System.out.println("\n\n\tNEW ASSIGNMENT");
        System.out.println("\nCOURSE ID:");
        int courseId = scan.nextInt();
        scan.nextLine();
        System.out.println("\nWEIGHT(OUT OF 100):");
        int weight = scan.nextInt();
        weight = weight / 100;
        scan.nextLine();
        System.out.println("\nDEADLINE(DD-MM-YYYY FORMAT):");
        String deadline = scan.nextLine();
        System.out.println("\nDETAILS:");
        String details = scan.nextLine();
        System.out.println();

        System.out.println("Choose assignment type:" +
                "\n\t1. Homework;" +
                "\n\t2. Project;" +
                "\n\t3. Exam.");

        int choice = scan.nextInt();
        scan.nextLine();

        switch(choice){
            case 1: {
                System.out.println("NUMBER OF EXERCISES:");
                int nr = scan.nextInt();
                scan.nextLine();
                Homework h = new Homework(courseId, weight, new SimpleDateFormat("dd-MM-yyyy").parse(deadline),
                        details, nr);
                assignmentMap.put(h.getId(), h);
                courseMap.get(courseId).getAssignmentList().add(h.getId());
                System.out.println("SUMMARY:" + h.toString());
                break;
            }
            case 2:{
                System.out.println("NUMBER OF STUDENTS ALLOWED TO WORK ON THE SAME PROJECT:");
                int nr = scan.nextInt();
                scan.nextLine();
                Project p = new Project(courseId, weight, new SimpleDateFormat("dd-MM-yyyy").parse(deadline),
                        details, nr);
                assignmentMap.put(p.getId(), p);
                courseMap.get(courseId).getAssignmentList().add(p.getId());
                System.out.println("SUMMARY:" + p.toString());
                break;
            }
            case 3:{
                System.out.println("DURATION:");
                boolean oral = true;
                int duration = scan.nextInt();
                scan.nextLine();
                System.out.println("SUPERVISOR ID:");
                int supervisor = scan.nextInt();
                scan.nextLine();
                System.out.println("IS THE EXAM ORAL? Y/N:");
                String isOral = scan.nextLine();
                if (isOral.toUpperCase().equals("N")){
                    oral = false;
                }
                Exam e = new Exam(courseId, weight, new SimpleDateFormat("dd-MM-yyyy").parse(deadline),
                        details, duration,supervisor,oral);
                assignmentMap.put(e.getId(), e);
                System.out.println("SUMMARY:" + e.toString());
                courseMap.get(courseId).getAssignmentList().add(e.getId());
                break;
            }
        }
        System.out.println("Assignment successfully added.");
    }

    private void viewWorksP() throws InterruptedException { //ok - tested
        cls();
        System.out.println("\n\tVIEW STUDENT WORKS");
        System.out.println("STUDENT ID:");
        int id = scan.nextInt();
        scan.nextLine();
        viewWorks(id);
    }

    private void viewDeadlinesP() throws InterruptedException {//ok - tested
        cls();
        System.out.println("\n\tVIEW CLASS DEADLINES");
        System.out.println("CLASS ID:");
        int id = scan.nextInt();
        scan.nextLine();
        viewDeadlines(id);
    }

    private void studentListGeneratorP() throws InterruptedException {// ok - tested
        System.out.println("\n\tCLASS LIST GENERATOR\nID:");
        int id = scan.nextInt();
        scan.nextLine();
        studentListGenerator(id);
    }

    private void gradeWorks() {//ok - tested
        boolean exists = false;
        System.out.println("GRADING MENU");
        System.out.println("STUDENT ID:");
        int studentId = scan.nextInt();
        scan.nextLine();
        System.out.println("WORK ID:");
        int workId = scan.nextInt();
        scan.nextLine();
        for (var work : studentMap.get(studentId).getWorks()){
            if (work.getId() == workId){
                exists = true;
                if(work.isGraded()) {
                    System.out.println("This work has already been graded.");
                }
                else {
                    System.out.println("GRADE:");
                    double grade = scan.nextDouble();
                    scan.nextLine();
                    work.setGrade(grade);
                    work.setGraded(true);
                    System.out.println("Grading successful.");
                }
            }
        }
        if(! exists){
            System.out.println("This student does not have a work with that ID.");
        }
    }

    private void professorMenu(int id) throws ParseException, InterruptedException { //ok - tested
        //used to call all the methods that professors have access to
        cls();

        System.out.println("\t1. Edit your data;" +
                "\n\t2. Enroll a new student;" +
                "\n\t3. View the schedule of a class or professor;" +
                "\n\t4. Add a new assignment;" +
                "\n\t5. View upcoming deadlines;" +
                "\n\t6. View all the works turned in by a student;" +
                "\n\t7. View all students in a class;" +
                "\n\t8. Grade a student's work;" +
                "\n\t0. EXIT.");

        int choice = scan.nextInt();
        scan.nextLine();

        switch (choice){
            case 1: {
                editProfessorData(id);
                break;
            }
            case 2: {
                studentEnroll();
                break;
            }
            case 3: {
                viewSchedule();
                break;
            }
            case 4: {
                addAssignment();
                break;
            }
            case 5: {
                viewDeadlinesP();
                break;
            }
            case 6: {
                viewWorksP();
                break;
            }
            case 7: {
                studentListGeneratorP();
                break;
            }
            case 8: {
                gradeWorks();
                break;
            }
            case 0: {
                break;
            }
            default:{
                System.out.println("That number doesn't exist. Please try again!");
                professorMenu(id);
                break;
            }
        }
        professorMenu(id);
    }

    private void professorLogin(int attempts) throws ParseException, InterruptedException { //ok - tested
        cls();
        System.out.println("\n\tPROFESSOR LOGIN");
        System.out.println("\nID:");
        int id = scan.nextInt();
        scan.nextLine();
        System.out.println("\nPASSWORD:");
        String inputPassword = scan.nextLine();

        if (professorMap.get(id).getPassword().equals(inputPassword)){
            System.out.println("Login successful!");
            professorMenu(id);
        }
        else {
            if (attempts < 3) {
                System.out.println("Wrong ID or password. Attempts left:" + (3 - attempts));
                professorLogin(++attempts);
            }
            else {
                System.out.println("Too many wrong attempts. Returning to main menu.");
                mainMenu();
            }
        }
    }

//    ------------------------------------------------------------STUDENT METHODS---------------------------------------

    private void editStudentData(int id) throws InterruptedException { //ok - tested
        Student currentStudent = studentMap.get(id);
        cls();
        System.out.println("\tEDIT YOUR DATA");
        System.out.println("\nEMAIL:");
        String email = scan.nextLine();
        System.out.println("\nPASSWORD:");
        String password = scan.nextLine();
        currentStudent.setEmail(email);
        if(currentStudent.getPassword().equals(password)) {
            System.out.println("\nWARNING! The password is the same as the current one.");
        }
        currentStudent.setPassword(password);
        studentMap.replace(id, currentStudent);
        System.out.println("SUMMARY:");
        System.out.println(studentMap.get(id).toString());
    }

    private void submitWork(int id) { //ok - NOT tested
        boolean late = false;
        System.out.println("\n\tWORK SUBMISSION");
        System.out.println("\nASSIGNMENT ID:");
        int assignmentId = scan.nextInt();
        scan.nextLine();
        Date turnInTime = new Date();
        if (turnInTime.compareTo(assignmentMap.get(assignmentId).getDeadline()) > 0){
            late = true;
        }
        Work w = new Work(assignmentId,id,turnInTime,late);
        studentMap.get(id).getWorks().add(w);
    }

    private void viewGrade(int id) {//ok - NOT tested
        boolean exists = false;
        System.out.println("\n\tVIEW GRADE");
        System.out.println("\nWORK ID:");
        int workId = scan.nextInt();
        scan.nextLine();
        for (var work : studentMap.get(id).getWorks()){
            if (work.getId() == workId){
                exists = true;
                if(work.isGraded()) {
                    System.out.println("The grade of this work is " + work.getGrade());
                }
                else {
                    System.out.println("This work has not been graded yet.");
                }
            }
        }
        if(! exists){
            System.out.println("You have no work with that ID.");
        }
    }

    private void studentMenu(int id) throws ParseException, InterruptedException {//ok - tested
        //used to call all the methods that students have access to
        cls();

        System.out.println("\n\tSTUDENT MENU\nChoose the operation you want to perform:"+
                "\n\t1. Edit your data;" +
                "\n\t2. View the schedule of a class or professor;" +
                "\n\t3. Submit work for an assignment;" +
                "\n\t4. View upcoming deadlines;" +
                "\n\t5. View the grade of a work;" +
                "\n\t6. View all the works turned in;" +
                "\n\t0. EXIT.");
        int choice = scan.nextInt();
        scan.nextLine();

        switch (choice){
            case 1: {
                editStudentData(id);
                break;
            }
            case 2: {
                viewSchedule();
                break;
            }
            case 3: {
                submitWork(id);
                break;
            }
            case 4: {
                viewDeadlines(id);
                break;
            }
            case 5: {
                viewGrade(id);
                break;
            }
            case 6: {
                viewWorks(id);
                break;
            }
            case 0: {
                break;
            }
            default:{
                System.out.println("That number doesn't exist. Please try again!");
                studentMenu(id);
                break;
            }
        }
        studentMenu(id);
    }

    private void studentLogin(int attempts) throws ParseException, InterruptedException { //ok - tested
        cls();

        System.out.println("\n\tSTUDENT LOGIN");
        System.out.println("\nID:");
        int id = scan.nextInt();
        scan.nextLine();
        System.out.println("\nPASSWORD:");
        String password = scan.nextLine();

        if (studentMap.get(id) != null && studentMap.get(id).getPassword().equals(password)){
            System.out.println("Login successful!");
            studentMenu(id);
        }
        else if(attempts < 3){
            System.out.println("Wrong ID or password. Attempts left:" + (3 - attempts));
            studentLogin(++attempts);
        }
        else {
            System.out.println("Too many wrong attempts. Returning to main menu.");
            mainMenu();
        }
    }

    private void mainMenu() throws ParseException, InterruptedException { //ok - tested
        cls();

        System.out.println("\tHello!\n\nWelcome to the Student Info Portal. Please select the number of the operation you wish to perform:" +
                "\n\t1. PROFESSOR LOGIN;" +
                "\n\t2. STUDENT LOGIN;" +
                "\n\t0. EXIT." +
                "\n\nYour choice: ");

        int choice = scan.nextInt();
        scan.nextLine();

        switch(choice){
            case 1: {
                professorLogin(0);
                break;
            }
            case 2: {
                studentLogin(0);
                break;
            }
            case 0:{
                break;
            }
            default:{
                System.out.println("Wrong number. Try again.");
                break;
            }
        }

    }

    public void init() throws ParseException, InterruptedException { //NOT DONE

        Professor p = new Professor();

        Student s1= new Student("Dumitru","Cristea", 23, "dumitru.cristea@myxmail.com", "ITNCDH", new SimpleDateFormat("dd-MM-yyyy").parse("27-1-1999"),1);
        Student s2= new Student("Dante","Voinea", 21, "dante.voinea@myxmail.com", "EKYFGW", new SimpleDateFormat("dd-MM-yyyy").parse("15-7-2001"),1);
        Student s3= new Student("Stela","Barbu", 21, "stela.barbu@myxmail.com", "OZQKWO", new SimpleDateFormat("dd-MM-yyyy").parse("12-12-2001"),1);
        Student s4= new Student("Cosmina","Munteanu", 20, "cosmina.munteanu@myxmail.com", "OJJSPN", new SimpleDateFormat("dd-MM-yyyy").parse("1-9-2002"),1);
        Student s5= new Student("Anghel","Dima", 20, "anghel.dima@myxmail.com", "ZIUOCA", new SimpleDateFormat("dd-MM-yyyy").parse("18-11-2002"),1);
        Student s6= new Student("Gloria","Albu", 19, "gloria.albu@myxmail.com", "DKYGWQ", new SimpleDateFormat("dd-MM-yyyy").parse("13-9-2003"),1);
        Student s7= new Student("Serban","Iancu", 23, "serban.iancu@myxmail.com", "RGRULV", new SimpleDateFormat("dd-MM-yyyy").parse("7-10-1999"),1);
        Student s8= new Student("Stefan","Nistor", 22, "stefan.nistor@myxmail.com", "DTQVBI", new SimpleDateFormat("dd-MM-yyyy").parse("24-4-2000"),1);
        Student s9= new Student("Lavinia","Călinescu", 19, "lavinia.călinescu@myxmail.com", "YQRDXC", new SimpleDateFormat("dd-MM-yyyy").parse("17-9-2003"),1);
        Student s10= new Student("Aurel","Sava", 24, "aurel.sava@myxmail.com", "MDBBAR", new SimpleDateFormat("dd-MM-yyyy").parse("19-4-1998"),1);
        Student s11= new Student("Rodica","Rusu", 20, "rodica.rusu@myxmail.com", "CCMGFU", new SimpleDateFormat("dd-MM-yyyy").parse("16-8-2002"),2);
        Student s12= new Student("Angelica","Sava", 21, "angelica.sava@myxmail.com", "AYJRGG", new SimpleDateFormat("dd-MM-yyyy").parse("19-7-2001"),2);
        Student s13= new Student("Monica","Cristea", 23, "monica.cristea@myxmail.com", "FVOERO", new SimpleDateFormat("dd-MM-yyyy").parse("22-11-1999"),2);
        Student s14= new Student("Codruț","Georgescu", 24, "codruț.georgescu@myxmail.com", "MUCUWR", new SimpleDateFormat("dd-MM-yyyy").parse("7-12-1998"),2);
        Student s15= new Student("Horia","Moisescu", 20, "horeahoria.moisescu@myxmail.com", "FTRGME", new SimpleDateFormat("dd-MM-yyyy").parse("13-11-2002"),2);
        Student s16= new Student("Sofia","Frățilă", 21, "sofia.frățilă@myxmail.com", "TJXAZJ", new SimpleDateFormat("dd-MM-yyyy").parse("26-3-2001"),2);
        Student s17= new Student("Cristian","Mihăilescu", 24, "cristian.mihăilescu@myxmail.com", "KVNDLS", new SimpleDateFormat("dd-MM-yyyy").parse("3-8-1998"),2);
        Student s18= new Student("Gelu","Ursu", 22, "gelu.ursu@myxmail.com", "ROAWIG", new SimpleDateFormat("dd-MM-yyyy").parse("23-6-2000"),2);
        Student s19= new Student("Zoe","Barbu", 24, "zoe.barbu@myxmail.com", "NEFTYV", new SimpleDateFormat("dd-MM-yyyy").parse("19-5-1998"),2);
        Student s20= new Student("Panait","Sava", 23, "panait.sava@myxmail.com", "HCEHFY", new SimpleDateFormat("dd-MM-yyyy").parse("11-7-1999"),2);

        studentMap.put(1,s1);
        studentMap.put(2,s2);
        studentMap.put(3,s3);
        studentMap.put(4,s4);
        studentMap.put(5,s5);
        studentMap.put(6,s6);
        studentMap.put(7,s7);
        studentMap.put(8,s8);
        studentMap.put(9,s9);
        studentMap.put(10,s10);
        studentMap.put(11,s11);
        studentMap.put(12,s12);
        studentMap.put(13,s13);
        studentMap.put(14,s14);
        studentMap.put(15,s15);
        studentMap.put(16,s16);
        studentMap.put(17,s17);
        studentMap.put(18,s18);
        studentMap.put(19,s19);
        studentMap.put(20,s20);

        ArrayList<Integer> sIdList1 = new ArrayList<>();
        ArrayList<Integer> cIdList1 = new ArrayList<>();
        ArrayList<Integer> sIdList2 = new ArrayList<>();
        ArrayList<Integer> cIdList2 = new ArrayList<>();

        for (int i = 1; i <= 20; i++){
            if(i<=10) sIdList1.add(i);
            else sIdList2.add(i);
        }

        Class c1 = new Class("A",10,5, sIdList1, cIdList1);
        Class c2 = new Class("B",10,15, sIdList2, cIdList2);

        classMap.put(c1.getId(),c1);
        classMap.put(c2.getId(),c2);

        Professor p1= new Professor("Paula","Diaconu", 64, "paula.diaconu@myxmail.com", "ZBXEPU", new SimpleDateFormat("dd-MM-yyyy").parse("7-2-1958"));
        Professor p2= new Professor("Barbu","Nițu", 48, "barbu.nițu@myxmail.com", "SUZVRG", new SimpleDateFormat("dd-MM-yyyy").parse("21-8-1974"));
        Professor p3= new Professor("Andrei","Dima", 49, "andrei.dima@myxmail.com", "KQLLZC", new SimpleDateFormat("dd-MM-yyyy").parse("28-7-1973"));
        Professor p4= new Professor("Sandu","Pușcașu", 54, "sandu.pușcașu@myxmail.com", "WLYYAL", new SimpleDateFormat("dd-MM-yyyy").parse("13-3-1968"));
        Professor p5= new Professor("Eugen","Stan", 65, "eugen.stan@myxmail.com", "FGNJAG", new SimpleDateFormat("dd-MM-yyyy").parse("26-11-1957"));
        Professor p6= new Professor("Cosma","Tudor", 51, "cosma.tudor@myxmail.com", "BUYFKE", new SimpleDateFormat("dd-MM-yyyy").parse("21-11-1971"));
        Professor p7= new Professor("Ruxandra","Tabacu", 42, "ruxandra.tabacu@myxmail.com", "CPWYWP", new SimpleDateFormat("dd-MM-yyyy").parse("14-8-1980"));
        Professor p8= new Professor("Victor","Ursu", 46, "victor.ursu@myxmail.com", "KBDJNB", new SimpleDateFormat("dd-MM-yyyy").parse("9-2-1976"));
        Professor p9= new Professor("Basarab","Ifrim", 55, "basarab.ifrim@myxmail.com", "MLAVLW", new SimpleDateFormat("dd-MM-yyyy").parse("6-5-1967"));
        Professor p10= new Professor("Melania", "Dabija", 64, "melania.dabija@myxmail.com", "DDSYOT", new SimpleDateFormat("dd-MM-yyyy").parse("7-7-1958"));

        professorMap.put(1,p1);
        professorMap.put(2,p2);
        professorMap.put(3,p3);
        professorMap.put(4,p4);
        professorMap.put(5,p5);
        professorMap.put(6,p6);
        professorMap.put(7,p7);
        professorMap.put(8,p8);
        professorMap.put(9,p9);
        professorMap.put(10,p10);

        Subject sub1 = new Subject("Geometry 1"); //1
        Subject sub2 = new Subject("Geometry 2"); //2
        Subject sub3 = new Subject("Algebra"); //3
        Subject sub4 = new Subject("Calculus 1");//4
        Subject sub5 = new Subject("Calculus 2");//5

        subjectMap.put(1,sub1);
        subjectMap.put(2,sub2);
        subjectMap.put(3,sub3);
        subjectMap.put(4,sub4);
        subjectMap.put(5,sub5);


        Course cou1 = new Course(1,1,3,"10:00","Lab 3");
        Course cou2 = new Course(3,10,1,"10:00","Lab 1");
        Course cou3 = new Course(2,2,2,"10:00","Classroom 2-A");
        Course cou4 = new Course(3,3,1,"12:00","Main Amphitheatre");
        Course cou5 = new Course(3,4,3,"16:00","Lab 1-A");
        Course cou6 = new Course(2,5,4,"08:00","Classroom 23");
        Course cou7 = new Course(1,5,7,"14:00","Main Amphitheatre");
        Course cou8 = new Course(2,1,5,"18:00","Classroom 35");
        Course cou9 = new Course(2,2,6,"16:00","Lab 4");
        Course cou10 = new Course(1,6,6,"12:00","Classroom 1-B");
        Course cou11 = new Course(5,7,7,"14:00","Amphitheatre 2");
        Course cou12 = new Course(4,8,4,"12:00","Lab 1");
        Course cou13 = new Course(1,9,5,"08:00","Classroom 24");
        Course cou14 = new Course(4,9,4,"10:00","Classroom 15");
        Course cou15 = new Course(5,5,4,"12:00","Lab 2");

        courseMap.put(1,cou1);
        courseMap.put(2,cou2);
        courseMap.put(3,cou3);
        courseMap.put(4,cou4);
        courseMap.put(5,cou5);
        courseMap.put(6,cou6);
        courseMap.put(7,cou7);
        courseMap.put(8,cou8);
        courseMap.put(9,cou9);
        courseMap.put(10,cou10);
        courseMap.put(11,cou11);
        courseMap.put(12,cou12);
        courseMap.put(13,cou13);
        courseMap.put(14,cou14);
        courseMap.put(15,cou15);

        for(int i=1;i<=15;i++){
            if(i <= 8) classMap.get(1).getCourseIdList().add(i);
            else classMap.get(2).getCourseIdList().add(i);
        }

        Assignment a = new Assignment(1,0.5,new SimpleDateFormat("dd-MM-yyyy").parse("2-4-2022"),
                "Finish exercises left in workbook");

        cou1.getAssignmentList().add(1);

        Work w = new Work(1,1,new SimpleDateFormat("dd-MM-yyyy").parse("28-03-2022"),false);

        s1.getWorks().add(w);

        assignmentMap.put(1,a);

        mainMenu();
    }


}
