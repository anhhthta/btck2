package DAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import model.ModelFriend;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilites.HibernateUtil;

public class FriendDAO {
    public FriendDAO() {}
    
    public void insert(ModelFriend friend) {
        Transaction tr = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tr = session.beginTransaction();
            session.save(friend);
            tr.commit();
            
            session.close();
        } catch (Exception e) {
            if(tr != null) {
                tr.rollback();
            }
            e.printStackTrace();
        }
    }
    
    public List<ModelFriend> getFriends(int userId) {
        List<ModelFriend> friends = new ArrayList<>();
        
        Transaction tr = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tr = session.beginTransaction();
            String hql = "select f.id, f.friendId, u.name, u.image, u.gender, u.date " +
                        "from friends f " +
                        "join users u on u.id = f.friendId " +
                        "where f.id = :id and f.status = :st " +
                        "union " +
                        "select f.friendId, f.id, u.name, u.image, u.gender, u.date " +
                        "from friends f " +
                        "join users u on u.id = f.id " +
                        "where f.friendId = :id and f.status = :st ";
            
            Query query = session.createSQLQuery(hql);
            query.setParameter("id", userId);
            query.setParameter("st", "accepted");
            
            List<Object[]> results = query.getResultList();
            
            if (results != null && !results.isEmpty()) { 
                for(Object[] rs : results) {
                    friends.add(new ModelFriend(
                            Integer.parseInt(rs[0]+""), 
                            Integer.parseInt(rs[1]+""), 
                            rs[2]+"",
                            rs[3]+"", 
                            "accepted",
                            LocalDate.parse(rs[5]+""),
                            rs[4]+""
                            
                    ));
                    System.out.println();
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
        return friends;
    }
    
    public boolean checkFriend(int id, int fid) {
        boolean isFriend = false;
        Transaction tr = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tr = session.beginTransaction();
            String hql = "select f.id " +
                        "from friends f " +
                        "where (f.friendId = :i and f.id = :fi) or (f.id = :i and f.friendId = :fi);";
            
            Query query = session.createSQLQuery(hql);
            query.setParameter("i", id);
            query.setParameter("fi", fid);
            
            List results = query.getResultList();
            
            if (results != null && !results.isEmpty()) { 
                isFriend = true;
            }
            
            tr.commit();
            session.close();
        } catch (Exception e) {
            if(tr != null) {
                tr.rollback();
            }
            e.printStackTrace();
        }
        
        return isFriend;
    }
    
    public void update(int ownId, int fid, String status) {
        
        Transaction tr = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tr = session.beginTransaction();
            String hql = "update friends set `status` = :st "+
                        "where ((id = :ownId) and (friendId = :fid)) or ((friendId = :ownId) and (id = :fid));";
            
            Query query = session.createSQLQuery(hql);
            query.setParameter("st", status);
            query.setParameter("ownId", ownId);
            query.setParameter("fid", fid);
            query.executeUpdate();
            session.close();
        } catch (Exception e) {
            if(tr != null) {
                tr.rollback();
            }
            e.printStackTrace();
        }
    }
    
    public void delete(int ownId, int fid) {
        Transaction tr = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tr = session.beginTransaction();
            String hql = "delete from `friends` where ((id = :ownId) and (friendId = :fid)) or ((friendId = :ownId) and (id = :fid));";
            Query query = session.createSQLQuery(hql);
            query.setParameter("ownId", ownId);
            query.setParameter("fid", fid);
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
