package io.github.eyulingo.Entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Users")
public class Users  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "user_phone")
    private String userPhone;

    @Column(name = "cover_id")
    private String imageId;

    public Users(Users sUser) {
        userId = sUser.userId;
        userName = sUser.userName;
        password = sUser.password;
        userPhone = sUser.userPhone;
        imageId = sUser.imageId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public String getImageId() {
        return imageId;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}

