package DAO;

import connection.DTBCS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.ModelSendMessage;
import model.ModelUser;

public class MesageDAO {
    private final Connection con;

    
    public MesageDAO() {
        this.con = DTBCS.getInstance().getConnection();
    }
    
    public void insertMessage(ModelSendMessage data) throws SQLException {
        String sql = "insert into `mesage` (`from`, `time`, `content`, `to`) VALUES (?, ?, ?, ?);";
    
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, data.getUser().getUserID());
        pst.setTimestamp(2, Timestamp.valueOf(data.getTime()));
        pst.setString(3, data.getText());
        pst.setInt(4, data.getTo());

        
        pst.executeUpdate();
        pst.close();
    }
    
    public List<ModelSendMessage> getMessage() throws SQLException{
        List<ModelSendMessage> list = new ArrayList<>();
        
        String sql = "select u.name, m.`from`, m.content, m.time , m.to " +
                    "from mesage m " +
                    "join users u on u.id = m.`from` "+
                    "order by m.time;";
        PreparedStatement pst = con.prepareStatement(sql);
        
        ResultSet rs = pst.executeQuery();
        while(rs.next()) {
            ModelUser user = new ModelUser();
            user.setUserName(rs.getString(1));
            user.setUserID(rs.getInt(2));
            System.out.println("sv: "+user.getUserID());
            list.add(new ModelSendMessage(
                    user,
                    rs.getString(3),
                    rs.getTimestamp(4).toLocalDateTime(),
                    rs.getInt(5)
            ));
        }
        return list;
    }
    
}
