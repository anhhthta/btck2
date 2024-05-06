package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DTBCS {
    private static DTBCS instance;
    private Connection connection;
    
    public static DTBCS getInstance() {
        if(instance == null) {
            System.out.println("222");
            instance = new DTBCS();
        }
        
        return instance;
    }
    
    private DTBCS() {
    }
    
    public void connection() {
        try {
            System.out.println("1");
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            String url = "jdbc:mySQL://localhost:3306/chatApp";
            String userName = "root";
            String pass = "123456789";
            
            connection = DriverManager.getConnection(url, userName, pass);
        } catch (SQLException ex) {
            ex.printStackTrace();
//            Logger.getLogger(DTBCS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
