PAO PROJECT - Neagu Anastasia Elena 234

This project aims to emulate a school management system that keeps track of students, professors, as well as classes, schedules and grades.

The project will contain the following classes:
STUDENT and PROFESSOR, both having an is-a relationship with the abstract class PERSON. They represent the two types of user this platform aims to interact with.

HOMEWORK, PROJECT, EXAM: the types of assignments a professor can give out. They inherit attributes from the abstract class ASSIGNMENT. The student-side counterpart of those is the class WORK which represents what the student has done for any specific assignment.

SUBJECT, COURSE, CLASS: the methods of organising professors and students. Each class will keep track of the students within it as well as the courses they attend.

The project aims to cover the following actions.

As a student, one can:
  -Log in;
  -Edit their data(email and password - the rest are assumed to be immutable;
  -View the schedule of a class or professor;
  -View upcoming assignment deadlines;
  -View the grades of a given work;
  -View the works they turned in.

As a professor, one can:
  -Log in;
  -Enroll students;
  -View the schedule of a class or professor;
  -Add new assignments;
  -View upcoming deadlines;
  -View all the works turned in by a student;
  -View a list of all the students in a class;
  -Grade a student's work.
