package model;

import java.util.Objects;

public class Subject {

    private String name;
    private int term;
    private SubjectType type;
    private Integer ID;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public SubjectType getType() {
        return type;
    }

    public void setType(SubjectType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return term == subject.term &&
                name.equals(subject.name) &&
                type == subject.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, term, type);
    }

    @Override
    public String toString() {
        return name +
                "(" + term +
                ") - " + type +
                ' ';
    }
}
