package io.github.eyulingo.Entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="Goods")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "good_id")
    private Long goodId;

    @Column(name = "good_name")
    private String goodName;

    @Column(name = "store_id")
    private String storeId;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "discount")
    private BigDecimal discount;

    @Column(name = "storage")
    private String storage;

    @Column(name = "description")
    private String description;

    @Column(name="good_image_id")
    private String goodImageId;

    public Goods(){

    }
    public void setStorage(String storage) {
        this.storage = storage;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStorage() {
        return storage;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public String getDescription() {
        return description;
    }

    public Long getGoodId() {
        return goodId;
    }

    public String getGoodImageId() {
        return goodImageId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setGoodImageId(String goodImageId) {
        this.goodImageId = goodImageId;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}
