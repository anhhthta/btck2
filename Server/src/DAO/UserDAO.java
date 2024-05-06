
package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.DTBCS;
import java.util.ArrayList;
import java.util.List;
import model.ModelMessage;
import model.ModelUser;

public class UserDAO {
    
    private final Connection con;

    
    public UserDAO() {
        this.con = DTBCS.getInstance().getConnection();
    }
    
    public synchronized ModelMessage register(ModelUser user) {
        ModelMessage message = new ModelMessage();
        try {
            String sql;
            PreparedStatement pst;
            int id;
            
            boolean isExist = checkGmail(user.getEmail());
            
            if(!isExist) {
                con.setAutoCommit(false);
                
                sql = "insert into `users` (name, email, `password`) values (?,?,?)" ;
                
                pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                pst.setString(1, user.getUserName());
                pst.setString(2, user.getEmail());
                pst.setString(3, user.getPassword());
                
                pst.execute();
                ResultSet rs2 = pst.getGeneratedKeys();
                
                rs2.first();
                int userID = rs2.getInt(1);
                rs2.close();
                pst.close();
                user.setUserID(userID);
                con.commit();
                con.setAutoCommit(true);
                message.setSuccess(true);
                message.setMessage("Sign Up Success");
                message.setData(new ModelUser(userID, user.getUserName(), user.getEmail() , "", "", true));
            } else {
                message.setSuccess(false);
                message.setMessage("User Already Exist");
            }
        } catch (SQLException ex) {
            message.setMessage("Server Error");
            message.setSuccess(false);
            try {
                if(con.getAutoCommit()== false) {
                    con.rollback();
                    con.setAutoCommit(true);
                }
            } catch (Exception e) {
            }
        }
        return message;
    }
    
    public ModelMessage login(ModelUser login) throws SQLException{        
        ModelUser data = null;
        ModelMessage msg = new ModelMessage();
        msg.setSuccess(false);

        String sql = "select id, name, email, gender, imageString from `users` where BINARY(email)= ? and BINARY(`password`)= ? and status = '1' limit 1";
        PreparedStatement pst = con.prepareStatement(sql);
        
        pst.setString(1, login.getEmail());
        pst.setString(2, login.getPassword());

        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            int userID = rs.getInt(1);
            String userName = rs.getString(2);
            String email = rs.getString(3);
            msg.setSuccess(true);
            msg.setData(new ModelUser(userID, userName, email));
        }
        rs.close();
        pst.close();
        return msg;
    }
    
    
    public ModelUser getUser(ModelUser u) throws SQLException {
        ModelUser user = null;
        String sql = "select id, name, email, gender, imageString from users where status = '1' and email = ?;";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, u.getEmail());
        ResultSet rs = pst.executeQuery();
        if(rs.next()) {
            int userID = rs.getInt(1);
            String userName = rs.getString(2);
            String email = rs.getString(3);
            String gender = rs.getString(4);
            String imageString = rs.getString(5);
            
            user = new ModelUser(userID, userName, email, gender, imageString, true);
        }
        
        rs.close();
        pst.close();
        return user;
    }
    
    public List<ModelUser> getUsers(String exitUser) throws SQLException {
        System.out.println("exit: " +exitUser);
        List<ModelUser> list = new ArrayList<>();
        String sql = "select id, name, email, gender, imageString from users where status = '1' and id <> ?;";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, exitUser);
        ResultSet rs = pst.executeQuery();
        while(rs.next()) {
            int userID = rs.getInt(1);
            String userName = rs.getString(2);
            String email = rs.getString(3);
            String gender = rs.getString(4);
            String imageString = rs.getString(5);
            
            list.add(new ModelUser(userID, userName, email, gender, imageString, true));
        }
        
        rs.close();
        pst.close();
        return list;
    }
    
    public boolean checkGmail(String email) throws SQLException {
        boolean gmail = false;
        
        String sql = "select id from `users` where BINARY(email)= ? limit 1";
        PreparedStatement pst = con.prepareStatement(sql);
        
        pst.setString(1, email);

        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            gmail = true;
        }
        System.out.println(gmail);
        rs.close();
        pst.close();
        return gmail;
    }
}

////    
//    public void updatePass(String email, String pass) throws SQLException {
////        
////        con = DTBCS.getConnection();
////        
////        String sql = "UPDATE `users` SET `password` = ? WHERE (BINARY(email)= ?) limit 1";
////        PreparedStatement pst = con.prepareStatement(sql);
////        
////        pst.setString(1, pass);        
////        pst.setString(2, email);
////        
////        pst.executeUpdate();
////        pst.close();
////        DTBCS.closeConnection(con);        
//    }
//    





//        
//    
//    
//    public boolean checkGmail(String email) throws SQLException {
//        boolean gmail = false;
//        
////        con = DTBCS.getConnection();
////        
////        String sql = "select id from `users` where BINARY(email)= ? limit 1";
////        PreparedStatement pst = con.prepareStatement(sql);
////        
////        pst.setString(1, email);
////
////        ResultSet rs = pst.executeQuery();
////        if(rs.next()){
////            gmail = true;
////        }
////        rs.close();
////        pst.close();
////        DTBCS.closeConnection(con);
//        return gmail;
//    }
//    
//    public ArrayList<ModelUser> getIdName(int id) throws SQLException {
////        con = DTBCS.getConnection();
//        
//        ArrayList<ModelUser> user = new ArrayList<>();
////        
////        String sql = "select id, name from users where businessId = ?";
////        PreparedStatement pst = con.prepareStatement(sql);
////        
////        pst.setInt(1, id);
////
////        ResultSet rs = pst.executeQuery();
////        while(rs.next()){
////            user.add(
////                    new ModelUser(
////                        rs.getInt(1),
////                        rs.getString(2)
////                    )
////            );
////        }
////        rs.close();
////        pst.close();
////        DTBCS.closeConnection(con);
//        return user;
//    }
