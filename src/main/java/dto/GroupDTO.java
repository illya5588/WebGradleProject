package dto;

import model.Group;
import model.Student;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

public class GroupDTO {
    protected String name;
    protected LocalDate dateOfCreation;
    protected int id;

    public int getStudentsNumber() {
        return studentsNumber;
    }

    public void setStudentsNumber(int studentsNumber) {
        this.studentsNumber = studentsNumber;
    }

    protected int studentsNumber;

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


    public GroupDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GroupDTO(String name, LocalDate dateOfCreation) {
        this.name = name;
        this.dateOfCreation = dateOfCreation;


    }

    public GroupDTO fromGroupToDto(Group group){
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setId(group.getId());
        groupDTO.setName(group.getName());
        groupDTO.setDateOfCreation(group.getDateOfCreation());
        groupDTO.setStudentsNumber(group.getGroupList().size());
        return groupDTO;
    }
    @Override
    public String toString() {
        return "Name: " + name + "\n Created on: " + dateOfCreation;
    }


}
