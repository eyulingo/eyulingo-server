package io.github.eyulingo.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="GoodComments")
public class GoodComments implements Serializable {
    @Id
    @Column(name ="good_id")
    private Long goodId;

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "star")
    private Long star;

    @Column(name = "good_comment")
    private String gooodComment;

    public GoodComments(){

    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    public Long getGoodId() {
        return goodId;
    }

    public void setStar(Long star) {
        this.star = star;
    }

    public Long getStar() {
        return star;
    }

    public String getGooodComment() {
        return gooodComment;
    }

    public String getUserId() {
        return userId;
    }

    public void setGooodComment(String gooodComment) {
        this.gooodComment = gooodComment;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
