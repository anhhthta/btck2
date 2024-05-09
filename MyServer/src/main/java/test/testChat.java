/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import model.ModelUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utilites.HibernateUtil;

/**
 *
 * @author anhth
 */
public class testChat {
    public static void main(String[] args) {
        try {
            SessionFactory s = HibernateUtil.getSessionFactory();
            if(s != null) {
                Session session = s.openSession();
                try {
                    Transaction tr = session.beginTransaction();
                    ModelUser u = new ModelUser();
                    u.setUserName("N:anh");
                    u.setPassword("P:anh");
                    session.save(u);
                    tr.commit();
                } catch (Exception e) {
                } finally {
                    session.close();
                }
            }
        } catch (Exception e) {
        }
    }
}
