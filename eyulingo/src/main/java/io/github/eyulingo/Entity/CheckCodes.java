package io.github.eyulingo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "checkcodes")
public class CheckCodes {
    @Id
    @Column(name = "phone_num")
    private String phoneNum;

    @Column(name = "check_code")
    private String checkCode;

    @Column(name = "time")
    private Timestamp time;

    public CheckCodes(){

    }

    public String getCheckCode() {
        return checkCode;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
