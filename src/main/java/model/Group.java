package model;

import exceptions.DateException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.util.LinkedHashSet;
import java.util.Set;

public class Group {
    protected String name;
    protected LocalDate dateOfCreation;
    protected int id;

    public void setGroupList(Set<Student> groupList) {
        this.groupList = groupList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public Set<Student> getGroupList() {
        return groupList;
    }

    protected Set<Student> groupList = new LinkedHashSet<>();


    public Group() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Group(String name, LocalDate dateOfCreation) {
        this.name = name;
        this.dateOfCreation = dateOfCreation;


    }


    @Override
    public String toString() {
        return "Name: " + name + "\n Created on: " + dateOfCreation;
    }


}
