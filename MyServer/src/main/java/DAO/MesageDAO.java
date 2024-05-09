package DAO;

import connection.DTBCS;

import java.sql.Connection;
import java.util.List;
import model.ModelSendMessage;
import model.ModelUser;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilites.HibernateUtil;

public class MesageDAO {
    private final Connection con;

    
    public MesageDAO() {
        this.con = DTBCS.getInstance().getConnection();
    }
    
    public void insertMessage(ModelSendMessage data) {
        Transaction tr = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            tr = session.beginTransaction();
            data.setFrom(data.getUser().getUserID());
            session.save(data);
            tr.commit();
            
            session.close();
        } catch(Exception e){
            if(tr != null) {
                tr.rollback();
            }
            e.printStackTrace();
        }
    }
    
    public List<ModelSendMessage> getMessage(){
        List<ModelUser> users = new UserDAO().getUsers("<;.?//>");
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            List<ModelSendMessage> list = session.createQuery("from ModelSendMessage", ModelSendMessage.class).list();
            
            for(ModelUser user : users) {
                for(ModelSendMessage m : list) {
                    if(m.getFrom() == user.getUserID()) {
                        m.setUser(user);
                    }
                }
            }

            return list;
        }
    }
}
