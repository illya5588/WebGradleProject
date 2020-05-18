package model;

import exceptions.DateException;
import exceptions.NameException;

import java.time.LocalDate;
import java.util.Objects;


public  class User {
    private static final int MAX_LENGTH_NAME = 15;
    private static final int MAX_LENGHT_SURNAME = 20;
    protected String surname;
    protected String name;
    protected LocalDate DOB;
    protected int ID;



    public void setSurname(String surname) throws NameException {
        if (surname.length() <= MAX_LENGHT_SURNAME && surname.length() > 1) {
        this.surname = surname;
         } else {


            throw new NameException(surname, ": surname lenght should to be between 1 and "+MAX_LENGHT_SURNAME+" characters ");
          }
    }

    public void setName(String name) throws NameException {
        if (name.length() <= MAX_LENGTH_NAME && name.length() > 1) {
        this.name = name;
        } else {


            throw new NameException(name, ": name lenght should to be between 1 and "+MAX_LENGTH_NAME+" characters ");
         }
    }

    public void setDOB(LocalDate DOB) throws DateException {

        if(DOB.isAfter(LocalDate.now())) throw new DateException("This date of birth is not valid! ");
        this.DOB = DOB;

    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSurname() {
        return surname;
    }


    public String getName() {
        return name;
    }

    public LocalDate getDOB() {
        return DOB;
    }


    @Override
    public String toString() {
        return
                "\n" + surname + '\n' + name + '\n' +DOB;
    }

//    public User(LocalDate DOB) {
//        this.setDOB(DOB);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return surname.equals(user.surname) &&
                name.equals(user.name) &&
                DOB.equals(user.DOB);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, name, DOB);
    }

}
