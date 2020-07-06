package model;

import exceptions.DateException;

import javax.persistence.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.util.LinkedHashSet;
import java.util.Set;
@Entity
@Table(name = "groups", uniqueConstraints = @UniqueConstraint(columnNames = {"name","creation_date"}))
public class Group {

    @Id
    @GeneratedValue
    protected int id;

    protected String name;

    public Group(String name) {
        this.name = name;
    }

    @Column(name="creation_date")
    protected LocalDate dateOfCreation = LocalDate.now();

    @OneToMany (mappedBy = "group")
    protected Set<Student> groupList = new LinkedHashSet<>();


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
