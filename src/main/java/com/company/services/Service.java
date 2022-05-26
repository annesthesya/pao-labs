package com.company.services;

import com.company.assignments.*;
import com.company.repository.ProfessorRepository;
import com.company.subjects.Class;
import com.company.subjects.Course;
import com.company.subjects.Subject;
import com.company.users.Professor;
import com.company.users.Student;
import com.company.repository.StudentRepository;

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
    private final Scanner scan = new Scanner(System.in);
    private static Service instance;
    private static AuditService as;

    private Service() throws ParseException, InterruptedException, IllegalAccessException {
        init();
    }

    public static Service createInstance() throws ParseException, InterruptedException, IllegalAccessException {
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

    private void studentListGenerator(int id, int uid) throws InterruptedException, IllegalAccessException {
        StackTraceElement[] ste = new Throwable().getStackTrace();
        as.audit(uid, ste[0].getMethodName());
        cls();
        if (!classMap.containsKey(id)) {
            System.out.println("There is no class with that ID.");
            return;
        }
        System.out.println("CLASS " + classMap.get(id).getName());

//        List<Integer> students = classMap.get(id).getStudentIdList();

        System.out.println("\n\tSTUDENT LIST:");
        studentMap.values().stream()
                .filter(student->student.getClassId()==id)
                .forEach(System.out::println);

//        System.out.println("\n\tSTUDENT LIST:");
//        for (Integer student : students) {
//            Student currentStudent = studentMap.get(student);
//            System.out.println("\n\nNAME: " + currentStudent.getName() + " " + currentStudent.getSurname());
//            System.out.println("\nAGE: " + currentStudent.getAge());
//        }
    }

    private void viewScheduleC(int id) throws InterruptedException, IllegalAccessException {
        StackTraceElement[] ste = new Throwable().getStackTrace();
        as.audit(id, ste[0].getMethodName());
        cls();
        System.out.println("\n\tCLASS SCHEDULE GENERATOR" +
                "\nID:");
        
        int cid;
        try {
            cid = scan.nextInt();
            scan.nextLine();
        } catch (InputMismatchException exception) {
            System.out.println("Integers only, please.");
            return;
        }

        if (!classMap.containsKey(cid)) {
            System.out.println("There is no class with that ID.");
            return;
            }

        Class targetClass = classMap.get(cid);

        List<List<String>> schedule = new ArrayList<>(7);

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
            }
        }

        System.out.println("\tSchedule of class " + classMap.get(cid).getName()+"\n");

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

        private void viewScheduleP(int id) throws InterruptedException, IllegalAccessException {
            StackTraceElement[] ste = new Throwable().getStackTrace();
            as.audit(id, ste[0].getMethodName());
            cls();
            System.out.println("\n\tPROFESSOR SCHEDULE GENERATOR" +
                    "\nID:");
            int pid;
            try {
                pid = scan.nextInt();
                scan.nextLine();
            } catch (InputMismatchException exception) {
                System.out.println("Integers only, please.");
                return;
            }
            if (!professorMap.containsKey(pid)) {
                System.out.println("There is no professor with that ID.");
                return;
            }
            String professorName = professorMap.get(pid).getName() + " " + professorMap.get(pid).getSurname();
            System.out.println("\tSchedule of " + professorName);

            List<List<String>> schedule = new ArrayList<>(7);

            for (int i = 0; i < 7; i++)
                schedule.add(new ArrayList<>());

            for (var entry : courseMap.entrySet()){
                Course currentCourse = entry.getValue();
                if (currentCourse.getProfessorId() == pid) {
                    String hour = currentCourse.getHour();
                    String location = currentCourse.getLocation();
                    String subjectName = subjectMap.get(currentCourse.getSubjectId()).getName();
                    String eventLog = hour + " - " + subjectName + " in " + location + "\n";
                    schedule.get(currentCourse.getWeekDay() - 1).add(eventLog);
                }
            }

            if(schedule.isEmpty()){
                System.out.println("\nThe professor has no classes this semester!");
            }

            for (int i = 0; i < 7; i++){
                if (! schedule.get(i).isEmpty()) {
                    Collections.sort(schedule.get(i));

                    if(i == 0){
                        System.out.println("MONDAY");
                    } else if (i==1){
                        System.out.println("TUESDAY");
                    } else if (i==2){
                        System.out.println("WEDNESDAY");
                    } else if (i==3){
                        System.out.println("THURSDAY");
                    } else if (i==4){
                        System.out.println("FRIDAY");
                    } else if (i==5){
                        System.out.println("SATURDAY");
                    } else {
                        System.out.println("SUNDAY");
                    }

                    for (var day : schedule.get(i)) {
                        System.out.println(day);
                    }
                }
            }
    }

    private void viewSchedule(int id) throws InterruptedException, IllegalAccessException {
        StackTraceElement[] ste = new Throwable().getStackTrace();
        as.audit(id, ste[0].getMethodName());
        cls();
        System.out.println("\n\tSCHEDULE GENERATOR" +
                "\n1.Class schedule;" +
                "\n2.Professor schedule.");
        
        int choice = 0;
        try {
            choice = scan.nextInt();
            scan.nextLine();
        } catch (InputMismatchException exception) {
            System.out.println("Integers only, please.");
            return;
        }
        switch(choice){
            case 1: {
                viewScheduleC(id);
                break;
            }
            case 2: {
                viewScheduleP(id);
                break;
            }
        }

    }

    private void viewWorks(int id) throws InterruptedException, IllegalAccessException {
        StackTraceElement[] ste = new Throwable().getStackTrace();
        as.audit(id, ste[0].getMethodName());
        cls();
        if (! studentMap.containsKey(id)){
            System.out.println("Wrong ID!");
            viewWorksP(id);
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

    private void viewDeadlines(int id) throws IllegalAccessException {
        StackTraceElement[] ste = new Throwable().getStackTrace();
        as.audit(id, ste[0].getMethodName());
        if (!classMap.containsKey(id)) {
            System.out.println("There is no class with that ID.");
            return;
        }
        Class targetClass = classMap.get(id);
        System.out.println("DEADLINES OF " + targetClass.getName());
        Date today = new Date(); //gets the current date
        List<Assignment> deadlines =  new ArrayList<>();
        for(var courseId : targetClass.getCourseIdList()){
            Course currentCourse = courseMap.get(courseId);
            for (var assignmentId : currentCourse.getAssignmentList()) {
                if (assignmentMap.get(assignmentId).getDeadline().compareTo(today) > 0){ //checks if the deadline is
                    //after the current date
                    deadlines.add(assignmentMap.get(assignmentId));
                }
            }
        }

        if(deadlines.isEmpty()){
            System.out.println("No upcoming deadlines!");
        }

        AssignmentComparator assignmentC = new AssignmentComparator();
        deadlines.sort(assignmentC);
        for (var deadline : deadlines){
            System.out.println(deadline.getDeadline() + " : " + deadline.getDetails());
        }
    }

//    ------------------------------------------------------------PROFESSOR METHODS-------------------------------------

    private void editProfessorData(int id) throws InterruptedException, ParseException, IllegalAccessException {
        StackTraceElement[] ste = new Throwable().getStackTrace();
        as.audit(id, ste[0].getMethodName());
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

    private void studentEnroll(int id) throws ParseException, InterruptedException, IllegalAccessException {
        StackTraceElement[] ste = new Throwable().getStackTrace();
        as.audit(id, ste[0].getMethodName());
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
        int classId = 0;
        try {
            classId = scan.nextInt();
            scan.nextLine();
        } catch (InputMismatchException exception) {
            System.out.println("Integers only, please.");
            return;
        }
        Student newStudent = new Student(name, surname,2022 - Integer.parseInt(birthday.substring(birthday.length() - 4)),
                email, password, (java.sql.Date) new SimpleDateFormat("dd-MM-yyyy").parse(birthday), classId);
        studentMap.put(newStudent.getId(),newStudent);
        WriteService.write("src/com/company/data/Students.csv", newStudent, Student.class);
        System.out.println("SUMMARY:" + studentMap.get(newStudent.getId()).toString());
    }

    private void addAssignment(int id) throws ParseException, IllegalAccessException {
        StackTraceElement[] ste = new Throwable().getStackTrace();
        as.audit(id, ste[0].getMethodName());
        System.out.println("\n\n\tNEW ASSIGNMENT");
        System.out.println("\nCOURSE ID:");
        int courseId = 0;
        try {
            courseId = scan.nextInt();
            scan.nextLine();
        } catch (InputMismatchException exception) {
            System.out.println("Integers only, please.");
            return;
        }
        System.out.println("\nWEIGHT(OUT OF 100):");
        int weight = 0;
        try {
            weight = scan.nextInt();
            scan.nextLine();
        } catch (InputMismatchException exception) {
            System.out.println("Integers only, please.");
            return;
        }
        weight = weight / 100;
        System.out.println("\nDEADLINE(DD-MM-YYYY FORMAT):");
        String deadline = scan.nextLine();
        System.out.println("\nDETAILS:");
        String details = scan.nextLine();
        System.out.println();

        System.out.println("Choose assignment type:" +
                "\n\t1. Homework;" +
                "\n\t2. Project;" +
                "\n\t3. Exam.");
        int choice = 0;
        try {
            choice = scan.nextInt();
            scan.nextLine();
        } catch (InputMismatchException exception) {
            System.out.println("Integers only, please.");
            return;
        }
        switch(choice){
            case 1: {
                System.out.println("NUMBER OF EXERCISES:");
                int nr = 0;
                try {
                    nr = scan.nextInt();
                    scan.nextLine();
                } catch (InputMismatchException exception) {
                    System.out.println("Integers only, please.");
                    return;
                }
                Homework h = new Homework(courseId, weight, new SimpleDateFormat("dd-MM-yyyy").parse(deadline),
                        details, nr);
                assignmentMap.put(h.getId(), h);
                courseMap.get(courseId).getAssignmentList().add(h.getId());
                System.out.println("SUMMARY:" + h.toString());
                break;
            }
            case 2:{
                System.out.println("NUMBER OF STUDENTS ALLOWED TO WORK ON THE SAME PROJECT:");
                int nr = 0;
                try {
                    nr = scan.nextInt();
                    scan.nextLine();
                } catch (InputMismatchException exception) {
                    System.out.println("Integers only, please.");
                    return;
                }
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
                int duration = 0;
                try {
                    duration = scan.nextInt();
                    scan.nextLine();
                } catch (InputMismatchException exception) {
                    System.out.println("Integers only, please.");
                    return;
                }
                System.out.println("SUPERVISOR ID:");
                int supervisor = 0;
                try {
                    supervisor = scan.nextInt();
                    scan.nextLine();
                } catch (InputMismatchException exception) {
                    System.out.println("Integers only, please.");
                    return;
                }
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

    private void viewWorksP(int id) throws InterruptedException, IllegalAccessException {
        StackTraceElement[] ste = new Throwable().getStackTrace();
        as.audit(id, ste[0].getMethodName());
        cls();
        System.out.println("\n\tVIEW STUDENT WORKS");
        System.out.println("STUDENT ID:");
        int sid;
        try {
            sid = scan.nextInt();
            scan.nextLine();
        } catch (InputMismatchException exception) {
            System.out.println("Integers only, please.");
            return;
        }
        viewWorks(sid);
    }

    private void viewDeadlinesP(int id) throws InterruptedException, IllegalAccessException {
        StackTraceElement[] ste = new Throwable().getStackTrace();
        as.audit(id, ste[0].getMethodName());
        cls();
        System.out.println("\n\tVIEW CLASS DEADLINES");
        System.out.println("CLASS ID:");
        int cid;
        try {
            cid = scan.nextInt();
            scan.nextLine();
        } catch (InputMismatchException exception) {
            System.out.println("Integers only, please.");
            return;
        }
        viewDeadlines(cid);
    }

    private void studentListGeneratorP(int id) throws InterruptedException, IllegalAccessException {
        StackTraceElement[] ste = new Throwable().getStackTrace();
        as.audit(id, ste[0].getMethodName());
        System.out.println("\n\tCLASS LIST GENERATOR\nID:");
        int cid;
        try {
            cid = scan.nextInt();
            scan.nextLine();
        } catch (InputMismatchException exception) {
            System.out.println("Integers only, please.");
            return;
        }
        studentListGenerator(cid, id);
    }

    private void gradeWorks(int id) throws IllegalAccessException {
        StackTraceElement[] ste = new Throwable().getStackTrace();
        as.audit(id, ste[0].getMethodName());
        boolean exists = false;
        System.out.println("GRADING MENU");
        System.out.println("STUDENT ID:");
        int studentId = 0;
        try {
            studentId = scan.nextInt();
            scan.nextLine();
        } catch (InputMismatchException exception) {
            System.out.println("Integers only, please.");
            return;
        }
        if (!studentMap.containsKey(studentId)) {
            System.out.println("There is no student with that ID.");
            return;
        }
        System.out.println("WORK ID:");
        int workId = 0;
        try {
            workId = scan.nextInt();
            scan.nextLine();
        } catch (InputMismatchException exception) {
            System.out.println("Integers only, please.");
            return;
        }
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

    private void professorMenu(int id) throws ParseException, InterruptedException, IllegalAccessException {
        //used to call all the methods that professors have access to
        StackTraceElement[] ste = new Throwable().getStackTrace();
        as.audit(id, ste[0].getMethodName());
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
        int choice = -1;
        try {
            choice = scan.nextInt();
            scan.nextLine();
        } catch (InputMismatchException exception) {
            System.out.println("Integers only, please.");
            return;
        }
        switch (choice){
            case 1: {
                editProfessorData(id);
                break;
            }
            case 2: {
                studentEnroll(id);
                break;
            }
            case 3: {
                viewSchedule(id);
                break;
            }
            case 4: {
                addAssignment(id);
                break;
            }
            case 5: {
                viewDeadlinesP(id);
                break;
            }
            case 6: {
                viewWorksP(id);
                break;
            }
            case 7: {
                studentListGeneratorP(id);
                break;
            }
            case 8: {
                gradeWorks(id);
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

    private void professorLogin(int attempts) throws ParseException, InterruptedException, IllegalAccessException {
        cls();
        System.out.println("\n\tPROFESSOR LOGIN");
        System.out.println("\nID:");
        int id = 0;
        try {
            id = scan.nextInt();
            scan.nextLine();
        } catch (InputMismatchException exception) {
            System.out.println("Integers only, please.");
            return;
        }
        System.out.println("\nPASSWORD:");
        String inputPassword = scan.nextLine();
        try{
            if (professorMap.get(id).getPassword().equals(inputPassword)) {
                System.out.println("Login successful!");
                professorMenu(id);
            } else {
                if (attempts < 3) {
                    System.out.println("Wrong ID or password. Attempts left:" + (3 - attempts));
                    professorLogin(++attempts);
                } else {
                    System.out.println("Too many wrong attempts. Returning to main menu.");
                    mainMenu();
                }
            }
        }catch(NullPointerException exception){
            System.out.println("Wrong ID or password. Attempts left:" + (3 - attempts));
            professorLogin(++attempts);
        }
    }

//    ------------------------------------------------------------STUDENT METHODS---------------------------------------

    private void editStudentData(int id) throws InterruptedException, IllegalAccessException {
        StackTraceElement[] ste = new Throwable().getStackTrace();
        as.audit(id, ste[0].getMethodName());
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

    private void submitWork(int id) throws IllegalAccessException {
        StackTraceElement[] ste = new Throwable().getStackTrace();
        as.audit(id, ste[0].getMethodName());
        boolean late = false;
        System.out.println("\n\tWORK SUBMISSION");
        System.out.println("\nASSIGNMENT ID:");
        int assignmentId = 0;
        try {
            assignmentId = scan.nextInt();
            scan.nextLine();
        } catch (InputMismatchException exception) {
            System.out.println("Integers only, please.");
            return;
        }
        scan.nextLine();
        if (!assignmentMap.containsKey(assignmentId)){
            System.out.println("There is no assignment with that ID.");
            return;
        }
        Date turnInTime = new Date();
        if (turnInTime.compareTo(assignmentMap.get(assignmentId).getDeadline()) > 0){
            late = true;
        }
        Work w = new Work(assignmentId,id,turnInTime,late);
        studentMap.get(id).getWorks().add(w);
    }

    private void viewGrade(int id) throws IllegalAccessException {
        StackTraceElement[] ste = new Throwable().getStackTrace();
        as.audit(id, ste[0].getMethodName());
        boolean exists = false;
        System.out.println("\n\tVIEW GRADE");
        System.out.println("\nWORK ID:");
        int workId = 0;
        try {
            workId = scan.nextInt();
            scan.nextLine();
        } catch (InputMismatchException exception) {
            System.out.println("Integers only, please.");
            return;
        }
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

    private void studentMenu(int id) throws ParseException, InterruptedException, IllegalAccessException {
        //used to call all the methods that students have access to
        StackTraceElement[] ste = new Throwable().getStackTrace();
        as.audit(id, ste[0].getMethodName());
        cls();
        System.out.println("\n\tSTUDENT MENU\nChoose the operation you want to perform:"+
                "\n\t1. Edit your data;" +
                "\n\t2. View the schedule of a class or professor;" +
                "\n\t3. Submit work for an assignment;" +
                "\n\t4. View upcoming deadlines;" +
                "\n\t5. View the grade of a work;" +
                "\n\t6. View all the works turned in;" +
                "\n\t0. EXIT.");
        int choice = -1;
        try {
            choice = scan.nextInt();
            scan.nextLine();
        } catch (InputMismatchException exception) {
            System.out.println("Integers only, please.");
            return;
        }
        switch (choice){
            case 1: {
                editStudentData(id);
                break;
            }
            case 2: {
                viewSchedule(id);
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
                return;
            }
            default:{
                System.out.println("That number doesn't exist. Please try again!");
                studentMenu(id);
                break;
            }
        }
        studentMenu(id);
    }

    private void studentLogin(int attempts) throws ParseException, InterruptedException, IllegalAccessException {
        cls();
        System.out.println("\n\tSTUDENT LOGIN");
        System.out.println("\nID:");
        int id = scan.nextInt();
        scan.nextLine();
        System.out.println("\nPASSWORD:");
        String password = scan.nextLine();
        try {
            if (studentMap.get(id) != null && studentMap.get(id).getPassword().equals(password)) {
                System.out.println("Login successful!");
                studentMenu(id);
            } else if (attempts < 3) {
                System.out.printf(studentMap.get(id).getPassword());
                System.out.println("Wrong ID or password. Attempts left:" + (3 - attempts));
                studentLogin(++attempts);
            } else {
                System.out.println("Too many wrong attempts. Returning to main menu.");
                mainMenu();
            }
        }catch(NullPointerException exception){
        System.out.println("Wrong ID or password. Attempts left:" + (3 - attempts));
        studentLogin(++attempts);
        }
    }

    private void mainMenu() throws ParseException, InterruptedException, IllegalAccessException {
        System.out.println("\tHello!\n\nWelcome to the Student Info Portal. Please select the number of the operation you wish to perform:" +
                "\n\t1. PROFESSOR LOGIN;" +
                "\n\t2. STUDENT LOGIN;" +
                "\n\t0. EXIT." +
                "\n\nYour choice: ");
        int choice = -1;
        try {
            choice = scan.nextInt();
            scan.nextLine();
        } catch (InputMismatchException exception) {
            System.out.println("Integers only, please.");
            return;
        }
        switch (choice) {
            case 1: {
                professorLogin(0);
                break;
            }
            case 2: {
                studentLogin(0);
                break;
            }
            case 0: {
                break;
            }
            default: {
                System.out.println("Wrong number. Try again.");
                break;
            }
        }

    }

    public void init() throws ParseException, InterruptedException, IllegalAccessException {

        as = AuditService.createInstance();

        StudentRepository sr = StudentRepository.createStudentRepository();

        sr.createStudentTable();

        List<Student> SL = ReadService.readCSV("src/main/java/com/company/data/Students.csv", Student.class);

        if (SL != null){
            for (int i = 1; i <= SL.size(); i ++){
                studentMap.put(i, SL.get(i-1));
                sr.insertStudent(SL.get(i-1));
            }
        }

        ProfessorRepository pr = ProfessorRepository.createProfessorRepository();

        pr.createProfessorTable();

        List<Professor> PL = ReadService.readCSV("src/main/java/com/company/data/Professors.csv", Professor.class);
        if (PL != null){
            for (int i = 1; i <= PL.size(); i ++){
                professorMap.put(i, PL.get(i-1));
                pr.insertProfessor(PL.get(i-1));
            }
        }

        List<Class> ClL = ReadService.readCSV("src/main/java/com/company/data/Classes.csv", Class.class);
        if (PL != null){
            for (int i = 1; i <= ClL.size(); i ++){
                classMap.put(i, ClL.get(i-1));
            }
        }

        List<Subject> SuL = ReadService.readCSV("src/main/java/com/company/data/Subjects.csv", Subject.class);
        if (SuL != null){
            for (int i = 1; i <= SuL.size(); i ++){
                subjectMap.put(i, SuL.get(i-1));
            }
        }

        List<Course> CoL = ReadService.readCSV("src/main/java/com/company/data/Courses.csv", Course.class);
        if (CoL != null){
            for (int i = 1; i <= CoL.size(); i ++){
                courseMap.put(i, CoL.get(i-1));
            }
        }

//
//        Professor p1= new Professor("Paula","Diaconu", 64, "paula.diaconu@myxmail.com", "ZBXEPU", new SimpleDateFormat("dd-MM-yyyy").parse("7-2-1958"));
//
//        WriteService WS;
//
//        WriteService.write("src\\com\\company\\data\\Professors.csv", p1, Professor.class);
//
//        Assignment a = new Assignment(1,0.5,new SimpleDateFormat("dd-MM-yyyy").parse("2-4-2022"),
//                "Finish exercises left in workbook");
//
//        cou1.getAssignmentList().add(1);
//
//        Work w = new Work(1,1,new SimpleDateFormat("dd-MM-yyyy").parse("28-03-2022"),false);
//
//        s1.getWorks().add(w);
//
//        assignmentMap.put(1,a);

        mainMenu();
    }


}
