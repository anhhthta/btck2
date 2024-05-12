package DAO;


import java.util.List;
import javax.persistence.Query;
import model.ModelSendMessage;
import model.ModelUser;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilites.HibernateUtil;

public class MesageDAO {

    public void insertMessage(ModelSendMessage data) {
        Transaction tr = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tr = session.beginTransaction();
            data.setFrom(data.getUser().getUserID());
            session.save(data);
            tr.commit();

            session.close();
        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<ModelSendMessage> getMessage() {
        List<ModelUser> users = new UserDAO().getUsers();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<ModelSendMessage> list = session.createQuery("from ModelSendMessage", ModelSendMessage.class).list();

            for (ModelUser user : users) {
                for (ModelSendMessage m : list) {
                    if (m.getFrom() == user.getUserID()) {
                        m.setUser(user);
                    }
                }
            }

            return list;
        }
    }

    public void delete(int id) {
        Transaction tr = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tr = session.beginTransaction();
            String hql = "delete from ModelSendMessage where fromId = :fid or toId = :tid";
            Query query = session.createQuery(hql);
            query.setParameter("fid", id);
            query.setParameter("tid", id);
            query.executeUpdate();
            
            session.close();
        } catch (Exception e) {
            if(tr != null) {
                tr.rollback();
            }
            e.printStackTrace();
        }
    }
}
