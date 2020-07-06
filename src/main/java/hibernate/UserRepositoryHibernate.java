package hibernate;

import jdbc.PasswordUtility;
import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserRepositoryHibernate {
    static final int SALT_LENGTH = 20;

    public static void main(String[] args) {


        User user = new User();
        user.setName("Illya");
        user.setSurname("Shulman");
        user.setDOB(LocalDate.of(1997, 5, 28));
        user.setRole("student");
        user.setLogin("shulman");
        user.setPassword("12345");
        //new UserRepositoryHibernate().getUsersByCurrentMonthViaSqlStatement();
        new UserRepositoryHibernate().getUsersByCurrentMonthViaStream();

    }
    public List<User> getUsersByCurrentMonthViaStream(){
        Transaction tx = null;
        List<User> allUsers = new ArrayList<>();
        try(Session session = HibernateFactory.HibernateUtil.getSessionFactory().openSession()){
            String hql = "SELECT u FROM User u";
            Query query = session.createQuery(hql);
            allUsers = query.list();
            return allUsers.stream().filter(user -> user.getDOB()
                    .getMonth().equals(LocalDate.now().getMonth()))
                    .collect(Collectors.toList());
        }
    }
    public List<User> getAllUsers(){
        Transaction tx = null;
        List<User> allUsers = new ArrayList<>();
        try(Session session = HibernateFactory.HibernateUtil.getSessionFactory().openSession()){
            String hql = "SELECT u FROM User u";
            Query query = session.createQuery(hql);
            allUsers = query.list();
            return allUsers;
        }
    }
    public List<User> getUsersByCurrentMonthViaHqlStatement(){
        List<User> result = new ArrayList<>();
        Transaction tx = null;
        try(Session session = HibernateFactory.HibernateUtil.getSessionFactory().openSession()){
            String hql = "FROM User u WHERE EXTRACT(MONTH FROM u.birth_date)=:EXTRACT(MONTH FROM date)";
            Query query = session.createQuery(hql);
            NativeQuery nativeQuery;
            query.setParameter("date",LocalDate.now());
            result=query.list();
            return result;
        }
    }
    public boolean isUserPresent(User user){
        Transaction tx = null;
        try(Session session = HibernateFactory.HibernateUtil.getSessionFactory().openSession()){
            String hql = "FROM User u WHERE u.surname =:surname and u.name =:name and u.birth_date =:dateOfBirth ";
            Query query = session.createQuery(hql);
            query.setParameter("surname",user.getSurname());
            query.setParameter("name", user.getName());
            query.setParameter("dateOfBirth", user.getDOB());
            List<User> result = query.list();
            if(result.size()==0){
                return false;
            }
            else {
                return true;
            }
        }
    }
    public Optional<Integer> getUserIdByFullInformation(User user){
        Transaction tx = null;
        try(Session session = HibernateFactory.HibernateUtil.getSessionFactory().openSession()){
            String hql = "FROM User u WHERE u.surname =:surname and u.name =:name and u.birth_date =:dateOfBirth ";
            Query query = session.createQuery(hql);
            query.setParameter("surname",user.getSurname());
            query.setParameter("name", user.getName());
            query.setParameter("dateOfBirth", user.getDOB());

            List<User>results = query.list();

            user = results.get(0);
            Optional<Integer> id = Optional.of(user.getID());
            return id;

        }

    }

    public User getUserByLoginAndPassword(String login, String password) {
        Transaction tx = null;
        try (Session session = HibernateFactory.HibernateUtil.getSessionFactory().openSession()) {

            String hql = "FROM User u WHERE u.login = :login and u.password = 'xfzwTHtCxnGmNCG/E4ibLWN+A2BCG9qNkrdsoBpgx6w='";
            Query query = session.createQuery(hql);
            query.setParameter("login", login);
            List results = query.list();
            return (User) results.get(0);

        }


    }

    public static void addUser(User user) {

        Session session = (Session) HibernateFactory.HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Integer userIdSaved = null;
        try {
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null)
                tx.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }


    }

    public void confirmUserWithRoleById(int id, String role) {

        Transaction tx = null;
        try (Session session = HibernateFactory.HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            User user = session.get(User.class, id);
            user.setRole(role);
            user.setConfirmed(true);
            session.update(user);
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null)
                tx.rollback();
            ex.printStackTrace();
        }

    }

    public static void addUserWithSecuredPassword(User user) {
        String salt = PasswordUtility.getSalt(SALT_LENGTH);
        user.setPassword(PasswordUtility.generateSecurePassword(user.getPassword(), salt));
        user.setBase64(salt);
        Session session = (Session) HibernateFactory.HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Integer userIdSaved = null;
        try {
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null)
                tx.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }


    }

    public static void deleteUser(User user) {

        Session session = (Session) HibernateFactory.HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Integer userIdSaved = null;
        try {
            tx = session.beginTransaction();
            session.delete(user);
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null)
                tx.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }


    }

    public static void updateUser(User user) {

        Session session = (Session) HibernateFactory.HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Integer userIdSaved = null;
        try {
            tx = session.beginTransaction();
            session.update(user);
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null)
                tx.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }


    }

}

