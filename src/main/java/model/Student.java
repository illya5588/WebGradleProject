package model;

import exceptions.DateException;
import exceptions.NameException;

import java.time.LocalDate;
import java.time.Period;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Student extends User implements Comparable<Student> {


    UUID student_uuid;
    private float averageMark;
    protected Map<Subject, Mark> marks;
    protected int student_ID;
    protected Group group;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    private static final int MIN_STUDENT_AGE = 15;

    //student_id, subject_id, mark // students_subjects or marks   2, 8, 10 - students, 1, 5 - subjects


    // 2, 1, 70; 2, 5, 100

    {
        student_uuid = genID();
    }

    public void setStudent_uuid(UUID student_uuid) {
        this.student_uuid = student_uuid;
    }

    public Student(String surname, String name, LocalDate date) {
        //super(date);
        try {

            this.setSurname(surname);
        } catch (NameException e) {
            this.surname = "X";

        }
        try {
            this.setName(name);

        } catch (NameException e) {

            this.name = "X";
        }

        if (Period.between(date, LocalDate.now()).getYears() > MIN_STUDENT_AGE) {
            this.DOB = date;
        } else {
            throw new DateException("Student is too young! Student age should to be greater than " + MIN_STUDENT_AGE + " !");
        }

    }

    public int getStudent_ID() {
        return student_ID;
    }

    public void setStudent_ID(int student_ID) {
        this.student_ID = student_ID;
    }

    // Equals and HashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return
                surname.equals(student.surname) &&
                        name.equals(student.name) &&
                        DOB.equals(student.DOB);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, name, DOB);
    }

    public UUID genID() {
        return UUID.randomUUID();


    }


    //Getters and Setters

    public Map<Subject, Mark> getMarks() {
        return marks;
    }

    public void setMarks(Map<Subject, Mark> marks) {
        this.marks = marks;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return super.toString().concat("\n average mark= " + String.valueOf(averageMark).concat("\n Student ID:").concat(String.valueOf(student_uuid)).concat("\n ").concat("\n\n"));

    }

    public String getName() {
        return name;
    }

    public UUID getStudent_uuid() {
        return student_uuid;
    }

    public float getAverageMark() {
        return averageMark;
    }

    public LocalDate getDOB() {
        return DOB;
    }

    public void setAverageMark(float averageMark) {
        this.averageMark = averageMark;
    }


    @Override
    public int compareTo(Student o) {
        if (this.equals(o)) {
            return 0;
        }
        if (this.averageMark >= o.getAverageMark()) {
            return 1;
        }
        return -1;


    }
}

