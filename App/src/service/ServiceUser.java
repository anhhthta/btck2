package service;

//
//package service;
//
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import connection.DTBCS;
//import model.ModelUser;
//
//public class ServiceUser {
//    private Connection con;
//    
//    public ModelUser login(ModelUser login) throws SQLException{
//        con = DTBCS.getConnection();
//        
//        ModelUser data = null;
//        String sql = "select userID, userName, email from `user` where BINARY(email)= ? and BINARY(`password`)= ? limit 1";
//        PreparedStatement pst = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//        
//        pst.setString(1, login.getEmail());
//        pst.setString(2, login.getPassword());
//
//        ResultSet rs = pst.executeQuery();
//        if(rs.first()){
//            int userID = rs.getInt(1);
//            String userName = rs.getString(2);
//            String email = rs.getString(3);
//            data = new ModelUser(userID, userName, email, "");
//        }
//        rs.close();
//        pst.close();
//        DTBCS.closeConnection(con);
//        return data;
//    }
//    
//    public void insertUser(ModelUser user) throws SQLException {
//        con = DTBCS.getConnection();
//
//        String sql = "insert into `user` (userName, email, `password`) values (?,?,?)" ;
//        
//        PreparedStatement pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
//        pst.setString(1, user.getUserName());
//        pst.setString(2, user.getEmail());
//        pst.setString(3, user.getPassword());
//        pst.execute();
//        ResultSet rs = pst.getGeneratedKeys();
//        rs.first();
//        int userID = rs.getInt(1);
//        rs.close();
//        pst.close();
//        DTBCS.closeConnection(con);
//        user.setUserID(userID);
//    }
//    
//    public boolean checkDuplicateEmail(String user) throws SQLException {
//        con = DTBCS.getConnection();
//        boolean duplicate = false;
//        String sql = "select userID from `user` where email=? limit 1";
//        
//        PreparedStatement pst = con.prepareStatement(sql);
//        pst.setString(1, user);
//        ResultSet rs = pst.executeQuery();
//        while(rs.next()){
//            duplicate = true;
//        }
//        rs.close();
//        pst.close();
//        DTBCS.closeConnection(con);
//        DTBCS.closeConnection(con);
//        return duplicate;
//    }
//}
