//
//package model;
//
//import java.io.Serializable;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//public class ModelUser implements Serializable{
//    private int userID;
//    private String userName;
//    private String email;
//    private String password;
//    private String repassword;
//    private String veridyCode;
//    private String gender;
//    private String image;    
//    private boolean status;
//    
//    public ModelUser(int userID, String userName, String email, String gender, String image, boolean status) {
//        this.userID = userID;
//        this.userName = userName;
//        this.email = email;
//        this.gender = gender;
//        this.image = image;
//        this.status = status;
//    }
//
//    public ModelUser(int userID, String userName, String email, String password, String repassword, String veridyCode) {
//        this.userID = userID;
//        this.userName = userName;
//        this.email = email;
//        this.password = password;
//        this.repassword = repassword;
//        this.veridyCode = veridyCode;
//    }
//    
//    public ModelUser(int userID, String userName, String email, String password, String repassword) {
//        this.userID = userID;
//        this.userName = userName;
//        this.email = email;
//        this.password = password;
//        this.repassword = repassword;
//    }
//
//
//    public ModelUser(int userID, String userName, String email, String password) {
//        this.userID = userID;
//        this.userName = userName;
//        this.email = email;
//        this.password = password;
//    }
//    
//    public ModelUser(int userID, String userName, String email) {
//        this.userID = userID;
//        this.userName = userName;
//        this.email = email;
//    }
//    
//    public ModelUser(String email, String password, String repassword) {
//        this.email = email;
//        this.password = password;
//        this.repassword = repassword;
//    }
//    
//    public ModelUser(int userId, String userName) {
//        this.userID = userId;
//        this.userName = userName;
//    }
//
//    public ModelUser(String email, String password) {
//        this.email = email;
//        this.password = password;
//    }
//    
//    public ModelUser(Object json) {
//        JSONObject obj = (JSONObject) json;
//        try {
//            userID = obj.getInt("userID");
//            userName = obj.getString("userName");
//            gender = obj.getString("gender");
//            image = obj.getString("image");
//            status = obj.getBoolean("status");
//        } catch (Exception e) {
//            System.err.println(e);
//        }
//    }
//    
//    public int getUserID() {
//        return userID;
//    }
//
//    public void setUserID(int userID) {
//        this.userID = userID;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getVeridyCode() {
//        return veridyCode;
//    }
//
//    public void setVeridyCode(String veridyCode) {
//        this.veridyCode = veridyCode;
//    }
//
//    public String getRepassword() {
//        return repassword;
//    }
//
//    public void setRepassword(String repassword) {
//        this.repassword = repassword;
//    }
//    
//    public boolean comparePass() {
//        return this.password.equals(this.repassword);
//    }
//
//    @Override
//    public String toString() {
//        return userName;
//    }
//    
//    
//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }
//
//    public boolean isStatus() {
//        return status;
//    }
//
//    public void setStatus(boolean status) {
//        this.status = status;
//    }
//    
//    public JSONObject toJSONObjectRegister() {
//        try {
//            JSONObject json = new JSONObject();
//            json.put("userName", userName);
//            json.put("email", email);
//            json.put("gender", gender);
//            json.put("image", image);
//            json.put("password", password);
//            return json;
//        } catch (JSONException ex) {
//            return null;
//        }
//    }
//    
//    
//    public JSONObject toJSONObjectLogin() {
//        try {
//            JSONObject json = new JSONObject();
//            json.put("email", email);
//            json.put("password", password);
//            return json;
//        } catch (JSONException ex) {
//            return null;
//        }
//    }
//}



package model;

import java.io.Serializable;

public class ModelUser  implements Serializable{
    private int userID;
    private String userName;
    private String email;
    private String password;
    private String repassword;
    private String veridyCode;
    private String gender;
    private String image;
    private boolean status;

    public ModelUser(int userID, String userName, String email, String gender, String image, boolean status) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.gender = gender;
        this.image = image;
        this.status = status;
    }   
        
    public ModelUser(int userID, String userName, String email, String password, String image) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.image = image;
    }
    
    
    public ModelUser(int userID, String userName, String email, String password, String repassword, String veridyCode) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.repassword = repassword;
        this.veridyCode = veridyCode;
    }

    public ModelUser(int userID, String userName, String email, String password) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
    
    public ModelUser(int userID, String userName, String email) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
    }
    
    public ModelUser(String email, String password, String repassword) {
        this.email = email;
        this.password = password;
        this.repassword = repassword;
    }
    
    public ModelUser(int userId, String userName) {
        this.userID = userId;
        this.userName = userName;
    }

    public ModelUser(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    public ModelUser() {
    }
    
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVeridyCode() {
        return veridyCode;
    }

    public void setVeridyCode(String veridyCode) {
        this.veridyCode = veridyCode;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }
    
    public boolean comparePass() {
        return this.password.equals(this.repassword);
    }
    
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return userName;
    }
}
