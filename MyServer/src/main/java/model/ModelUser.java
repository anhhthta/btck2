package model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name = "users")
public class ModelUser  implements Serializable{  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int userID;
    @Column(name = "name")
    private String userName;
    private LocalDate date;
    @Lob
    private String email;
    private String gender;
    @Lob
    @Column(name = "image")
    private String image;
    @Lob
    private String password;
    @Transient
    private String repassword;
    private boolean status;
    @Transient
    private String veridyCode;

    public ModelUser(int userID, String userName, LocalDate date, String email, String gender, String image, String password, boolean status) {
        this.userID = userID;
        this.userName = userName;
        this.date = date;
        this.email = email;
        this.gender = gender;
        this.image = image;
        this.password = password;
        this.status = status;
    }
    
    public ModelUser(int userID, String userName, String email, String gender, String image, boolean status) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.gender = gender;
        this.image = image;
        this.status = status;
    }

    public ModelUser(int userID, String userName, String email, String password, String repassword) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.repassword = repassword;
    }

    public ModelUser(String email, String password, String repassword) {
        this.email = email;
        this.password = password;
        this.repassword = repassword;
    }

    public ModelUser(int userID, String userName, String email) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getVeridyCode() {
        return veridyCode;
    }

    public void setVeridyCode(String veridyCode) {
        this.veridyCode = veridyCode;
    }

    
    public boolean comparePass() {
        return this.password.equals(this.repassword);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
