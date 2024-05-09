package utilites;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author anhth
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory = buildSectionFactory();

    private static SessionFactory buildSectionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Lỗi 1");
            return null;
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return  sessionFactory;
    }
    
    public static  void shutdown() {
        getSessionFactory().close();
    }
}
