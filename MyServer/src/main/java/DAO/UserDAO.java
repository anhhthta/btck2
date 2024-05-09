
package DAO;


import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import javax.imageio.ImageIO;
import javax.persistence.Query;
import javax.swing.ImageIcon;
import model.ModelMessage;
import model.ModelUser;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilites.HibernateUtil;

public class UserDAO {
    

    public UserDAO() {
    }

    public synchronized ModelMessage insert(ModelUser user) {
        Transaction tr = null;
        ModelMessage message = new ModelMessage();

        boolean isExist = checkEmail(user.getEmail());
        
        if(!isExist) {
            try(Session session = HibernateUtil.getSessionFactory().openSession()){
                tr = session.beginTransaction();
//                ENCODE
                String imgString = "";
                
                Image avatar = new ImageIcon(getClass().getResource("/icon/avatar2-50.png")).getImage();
                BufferedImage bufferedImage = new BufferedImage(avatar.getWidth(null), avatar.getHeight(null), BufferedImage.TYPE_INT_ARGB);
                bufferedImage.getGraphics().drawImage(avatar, 0, 0, null);
                ByteArrayOutputStream baos = new ByteArrayOutputStream(10000);
                ImageIO.write(bufferedImage, "png", baos);
                byte[] imageInByte = baos.toByteArray();
                imgString = Base64.getEncoder().encodeToString(imageInByte);
                
                user.setImage(imgString);
                
                user.setStatus(true);
                user.setDate(LocalDate.now());
                user.setGender("Other");
                session.save(user);
                tr.commit();
                
                message.setSuccess(true);
                message.setMessage("Sign Up Success");
                message.setData(user);
                session.close();
            } catch(Exception e){
                if(tr != null) {
                    tr.rollback();
                }
                e.printStackTrace();
            }
        } else {
            message.setSuccess(false);
            message.setMessage("User Already Exist");
        }
        
        return message;
    }
    
    public boolean checkEmail(String email){
        Transaction tr = null;
        boolean c = false;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            tr = session.beginTransaction();
            String hql = "select u.userID from ModelUser u where u.email = :e";
            
            Query query = session.createQuery(hql);
            query.setParameter("e", email);
            List results = query.getResultList();
            
            if(!results.isEmpty()) {
                c = true;
            }
            tr.commit();
            session.close();
        } catch(Exception e) {
           e.printStackTrace();
        }
        
        return c;
    }
//    
    public ModelMessage login(ModelUser login){
        Transaction tr = null;

        ModelMessage msg = new ModelMessage();
        msg.setSuccess(false);

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            tr = session.beginTransaction();
            String hql = "from ModelUser u where u.email = :e and u.password = :p and status = 1";
            
            Query query = session.createQuery(hql);
            query.setParameter("e", login.getEmail());
            query.setParameter("p", login.getPassword());


            List results = query.getResultList();
            if(results != null && !results.isEmpty()) {
                login = (ModelUser) results.get(0);
                msg.setSuccess(true);
                msg.setData(login);
            }
            
            tr.commit();

            session.close();
        } catch (Exception e) {
            if(tr != null) {
                tr.rollback();
            }
            e.printStackTrace();
        }
        
        return msg;
    }
    
    public List<ModelUser> getUsers(String exitUser) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("from ModelUser", ModelUser.class).list();
        }
    }
     
    public void update(ModelUser user){
        Transaction tr = null;

        ModelMessage msg = new ModelMessage();
        msg.setSuccess(false);

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            tr = session.beginTransaction();
            
            session.saveOrUpdate(user);
//            String hql = "update ModelUser set password = :p where id = :i";
//            
//            Query query = session.createQuery(hql);
//            query.setParameter("p", user.getPassword());
//            query.setParameter("i", user.getUserID());
//            
//            query.executeUpdate();
            tr.commit();
            session.close();
        } catch (Exception e) {
            if(tr != null) {
                tr.rollback();
            }
            e.printStackTrace();
        }
    }
}
