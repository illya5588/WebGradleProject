package model;

import com.sun.istack.NotNull;
import exceptions.DateException;
import exceptions.NameException;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"surname","name","birth_date"}))
@Inheritance(strategy = InheritanceType.JOINED)
public  class User {
    private static final int MAX_LENGTH_NAME = 15;
    private static final int MAX_LENGHT_SURNAME = 20;
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    protected int ID;

    @Column(name="surname")
    protected String surname;

    @Column(name = "name")
    protected String name;

    @Column(name = "birth_date")
    protected LocalDate DOB;

    //TODO javax validation
    // @NotNull
    @Column(nullable = false)
    protected String login;

    @Column(nullable = false)
    protected String password;

    @Column(name = "role")
    protected String role;

    @Column(name = "base64")
    protected String base64;

    @Column(nullable = false)
    boolean confirmed;

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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
