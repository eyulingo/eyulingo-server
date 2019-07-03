package io.github.eyulingo.Entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "OrderItems")
public class OrderItems implements Serializable {
    @Id
    @Column(name = "store_id")
    private Long storeId;

    @Id
    @Column(name = "good_id")
    private Long goodId;

    @Column(name = "current_price")
    private BigDecimal currentPrice;

    @Column(name = "amount")
    private Long amount;

    public OrderItems(){}

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getAmount() {
        return amount;
    }

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }
}
