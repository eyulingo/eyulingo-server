package io.github.eyulingo.Entity;


import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "re_phone")
    private String rePhone;

    @Column(name="re_address")
    private String reAddress;

    @Column(name="deliver_method")
    private String deliverMethod;

    @Column(name = "status")
    private String status;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "order_time")
    private Timestamp orderTime;

    public Orders(){

    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getDeliverMethod() {
        return deliverMethod;
    }

    public String getReAddress() {
        return reAddress;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getRePhone() {
        return rePhone;
    }

    public void setDeliverMethod(String deliverMethod) {
        this.deliverMethod = deliverMethod;
    }

    public String getStatus() {
        return status;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public void setReAddress(String reAddress) {
        this.reAddress = reAddress;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setRePhone(String rePhone) {
        this.rePhone = rePhone;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}