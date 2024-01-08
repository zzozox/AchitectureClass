package edu.ynu.se.xiecheng.achitectureclass.finalexm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.ynu.se.xiecheng.achitectureclass.common.entity.LogicEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ToString
@Entity
@Getter
@Setter
@Where(clause = "is_deleted = 0")
@Table(name = "orderList")
public class Order extends LogicEntity {
    @Column
    private Double totalPrice;
    @Column
    @ColumnDefault("false")
    private boolean payState;

    /**
     * 订单确认状态
     * 1为已确认
     * 0为未确认
     * -1为已退款
     */
    @Column
    private Integer confirm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("orders")
    @JsonIgnore
    private Customer customer;

    @OneToMany(mappedBy = "order",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("order")
    @JsonIgnore
    private Set<LineItem> lineItems;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("order")
    @JsonIgnore
    private Shop shop;

    /**
     * 初始化lineItems集合
     */
    public Order() {
        this.lineItems = new HashSet<>();
    }
    /**
     * 订单在未支付时自动加入订单项
     * @param shopItem
     */
    public void addItem(ShopItem shopItem,Integer quantity) {
        if (!this.payState) {
            LineItem lineItem=new LineItem();
            lineItem.setOrder(this);
            lineItem.setShopItem(shopItem);
            lineItem.setQuantity(quantity);
            lineItems.add(lineItem);
        }
    }

    /**
     * 列出order的具体订单项
     * @return
     */
    public List<LineItem> listLineItems(){
        return new ArrayList<>(lineItems);
    }
}
