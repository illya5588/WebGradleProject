package dto;

import model.Group;
import model.Mark;
import model.Subject;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

public class StudentDTO {
    protected String surname;
    protected String name;
    protected LocalDate dateOfBirth;
    UUID student_uuid;
    private float averageMark;
    protected Map<Subject, Mark> marks;
    protected int student_ID;
    protected Group group;
    protected int groupId;

    public StudentDTO() {
    }

    public StudentDTO(String surname, String name, LocalDate dateOfBirth) {
        this.surname = surname;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public UUID getStudent_uuid() {
        return student_uuid;
    }

    public void setStudent_uuid(UUID student_uuid) {
        this.student_uuid = student_uuid;
    }

    public float getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(float averageMark) {
        this.averageMark = averageMark;
    }

    public Map<Subject, Mark> getMarks() {
        return marks;
    }

    public void setMarks(Map<Subject, Mark> marks) {
        this.marks = marks;
    }

    public int getStudent_ID() {
        return student_ID;
    }

    public void setStudent_ID(int student_ID) {
        this.student_ID = student_ID;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

}
