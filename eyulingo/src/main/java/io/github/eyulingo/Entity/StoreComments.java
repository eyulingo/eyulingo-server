package io.github.eyulingo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = " StoreComments")
public class StoreComments implements Serializable {
    @Id
    @Column(name = "store_id")
    private Long storeId;

    @Id
    @Column(name ="user_id")
    private Long userId;

    @Column(name = "star")
    private Long star;

    @Column(name = "store_comments")
    private String storeComments;

    public StoreComments(){

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public void setStar(Long star) {
        this.star = star;
    }

    public Long getStar() {
        return star;
    }

    public String getStoreComments() {
        return storeComments;
    }

    public void setStoreComments(String storeComments) {
        this.storeComments = storeComments;
    }
}
