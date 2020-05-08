package model;

import java.time.LocalDate;


public class Teacher extends User {
    protected String department;
    protected int ID;

    public String getDepartment() {
        return department;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "Full Name  " +
                ",  department='" + department + '\'' +
                " DOB=" + DOB +
                '}';
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    protected Teacher() {
        //super(LocalDate.now());
    }

    public Teacher(String surname, String name, LocalDate DOB) {
        //super(DOB);
        this.surname = surname;
        this.name = name;
        this.DOB = DOB;
    }

}
