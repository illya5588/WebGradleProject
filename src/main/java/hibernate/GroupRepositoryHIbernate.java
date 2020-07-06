package hibernate;

import model.Group;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.Query;

public class GroupRepositoryHIbernate {


    public Group addGroup(Group group) {

        try (Session session = HibernateFactory.HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            if(group.getId()!=0){
                Group g = getGroupById(group.getId());
                 g=group;
                session.update(g);

            } else {
                session.save(group);
            }
            tx.commit();
       //     session.saveOrUpdate(group);


        }

        return group;
    }

    public Group getGroupById(int id) {
        Transaction tx = null;
        try (Session session = HibernateFactory.HibernateUtil.getSessionFactory().openSession()) {
            Group group = null;
            String hql = "FROM Group g WHERE g.id =: id ";
            Query<Group> query = session.createQuery(hql);
            query.setParameter("id", id);
            group = query.getSingleResult();
            return group;
        }
    }
}
