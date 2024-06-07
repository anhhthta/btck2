package DAO;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.imageio.ImageIO;
import javax.persistence.Query;
import javax.swing.ImageIcon;
import model.ModelFriend;
import model.ModelMessage;
import model.ModelUser;
import model.RequestFriend;
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

        if (!isExist) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                tr = session.beginTransaction();
                
                message.setSuccess(true);
                message.setMessage("Sign Up Success");
                message.setData(user);
                
//======================= ENCODE ==================
                String imgString = "";

                Image avatar = new ImageIcon(getClass().getResource("/icon/avatar2-50.png")).getImage();
                BufferedImage bufferedImage = new BufferedImage(avatar.getWidth(null), avatar.getHeight(null), BufferedImage.TYPE_INT_ARGB);
                bufferedImage.getGraphics().drawImage(avatar, 0, 0, null);
                ByteArrayOutputStream baos = new ByteArrayOutputStream(10000);
                ImageIO.write(bufferedImage, "png", baos);
                byte[] imageInByte = baos.toByteArray();
                imgString = Base64.getEncoder().encodeToString(imageInByte);
                
                String encodeEmail = Base64.getEncoder().encodeToString(user.getEmail().getBytes("UTF-8"));
                String encodePass = Base64.getEncoder().encodeToString(user.getPassword().getBytes("UTF-8"));
                
                user.setEmail(encodeEmail);
                user.setPassword(encodePass);
                user.setImageString(imgString);
//===================================================
                user.setStatus(true);
                user.setDate(LocalDate.now());
                user.setGender("Other");
                session.save(user);
                tr.commit();
                
                session.close();
            } catch (Exception e) {
                if (tr != null) {
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

    public boolean checkEmail(String email) {
        Transaction tr = null;
//            encode

        boolean c = false;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            
            tr = session.beginTransaction();
            String encodeEmail = Base64.getEncoder().encodeToString(email.getBytes("UTF-8"));

            String hql = "select u.userID from ModelUser u where u.email = :e";

            Query query = session.createQuery(hql);
            query.setParameter("e", encodeEmail);
            List results = query.getResultList();

            if (!results.isEmpty()) {
                c = true;
            }
            tr.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return c;
    }
//    

    public ModelMessage login(ModelUser login) {
        Transaction tr = null;

        ModelMessage msg = new ModelMessage();
        msg.setSuccess(false);
        msg.setMessage("password or account is incorrect!");

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tr = session.beginTransaction();
            String encodeEmail = Base64.getEncoder().encodeToString(login.getEmail().getBytes("UTF-8"));
            String encodePass = Base64.getEncoder().encodeToString(login.getPassword().getBytes("UTF-8"));

            System.out.println("email: " +encodeEmail);
            System.out.println("pass: "+encodePass);
            
            String hql = "from ModelUser u where u.email = :e and u.password = :p and status = 1";

            Query query = session.createQuery(hql);
            query.setParameter("e", encodeEmail);
            query.setParameter("p", encodePass);

            List results = query.getResultList();
            if (results != null && !results.isEmpty()) {
                login = (ModelUser) results.get(0);
                msg.setSuccess(true);
                msg.setData(login);
            }

            tr.commit();

            session.close();
        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
            }
            e.printStackTrace();
        }

        return msg;
    }

    public List<ModelUser> getUsers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from ModelUser", ModelUser.class).list();
        }
    }

     public List<RequestFriend> getRequestUsers(int userId, String search) {
        List<RequestFriend> requests = new ArrayList<>();
        
        Transaction tr = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tr = session.beginTransaction();
            String hql = "select " +
                        "coalesce(f.id, fr.id, -10) requesterId, coalesce(fr.id, f.friendId, -10) ownId, "+
                        "u.id userId, u.name, u.image, coalesce(fr.status, f.status, 'no') status from users u " +
                        "left join friends f on f.id = u.id and f.friendId = :id " +
                        "left join friends fr on fr.friendId = u.id and fr.id = :id " +
                        "where u.id != :id and u.name like :se";
            
            Query query = session.createSQLQuery(hql);
            query.setParameter("id", userId);
            query.setParameter("se", "%"+search+"%");
            
            List<Object[]> results = query.getResultList();
            
            if (results != null && !results.isEmpty()) { 
                for(Object[] rs : results) {
                    requests.add(new RequestFriend(
                            Integer.parseInt(rs[0]+""), 
                            new ModelFriend(
                                Integer.parseInt(rs[1]+""), 
                                Integer.parseInt(rs[2]+""), 
                                rs[3]+"",
                                rs[4]+"", 
                                rs[5]+""
                        )));
                }
            }
            
            tr.commit();
            session.close();
        } catch (Exception e) {
            if(tr != null) {
                tr.rollback();
            }
            e.printStackTrace();
        }
        return requests;
    }
    
    public void update(ModelUser user) {
        Transaction tr = null;

        ModelMessage msg = new ModelMessage();
        msg.setSuccess(false);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tr = session.beginTransaction();
            
            String encode = Base64.getEncoder().encodeToString(user.getPassword().getBytes("UTF-8"));
            user.setPassword(encode);
            
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
            if (tr != null) {
                tr.rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete(ModelUser user) {
        Transaction tr = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tr = session.beginTransaction();
            session.delete(user);

            tr.commit();
            session.close();
        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
            }
            e.printStackTrace();
        }
    }
}
