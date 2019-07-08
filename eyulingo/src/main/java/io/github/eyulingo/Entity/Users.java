package io.github.eyulingo.Entity;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Table(name = "Users")

public class Users implements UserDetails {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "user_phone")
    private String userPhone;

    @Column(name = "cover_id")
    private String imageId;


    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getImageId() {
        return imageId;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths=new ArrayList<GrantedAuthority>();

        auths.add(new SimpleGrantedAuthority("User"));

        return auths;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public String getPassword() { return this.password; }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

