package hibernate;

import model.Group;
import model.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;



import java.time.LocalDate;
import java.util.HashSet;

public class StudentRepositoryHibernate {
    public static void main(String[] args) {


            Student student = new Student("Shulman", "Illya", LocalDate.of(1998, 07, 29));
//            Group group = new Group();
//            group.setName("Delphi");
//            group.setGroupList(new HashSet<>());
//            Group g = new GroupRepositoryHIbernate().getGroupById(1);
//            g.setName("Math0");
//            new GroupRepositoryHIbernate().addGroup(group);
           // session.saveOrUpdate(g);
     new StudentRepositoryHibernate().addStudent(student);
        }



    public Student addStudent(Student student){
        Transaction tx = null;
        try(Session session = HibernateFactory.HibernateUtil.getSessionFactory().openSession()){
            tx = session.beginTransaction();
            session.save(student);
            tx.commit();
            return student;

        }
    }


    }

