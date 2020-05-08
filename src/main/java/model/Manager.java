package model;

import java.time.LocalDate;


public class Manager extends User {



    protected Manager() {
        //super(LocalDate.now());
    }

    public Manager(String surname, String name, LocalDate DOB) {
        //super(DOB);
        this.surname = surname;
        this.name = name;
        this.DOB = DOB;
    }




}
