package edu.ynu.se.xiecheng.achitectureclass.finalexm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;


@ToString(exclude = "orders")
@Entity
@Getter
@Setter
@DiscriminatorValue("0")
public class Customer extends User {
    @Column
    private String customerName;
    @Column
    private String customerEmail;

    @OneToMany(mappedBy = "customer",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("customer")
    @JsonIgnore
    private Set<Order> orders;

    /**
     * 顾客下单
     * @param shopItem
     * @return
     */
    public Order placeOrder(ShopItem shopItem,Integer quantity){
        for (Order tempOrder : orders) {
            /**
             * 有未支付订单
             * 且新加入商品与未支付订单所属商家相同
             * 则将新添商品加入未支付订单
             */
            if(!tempOrder.isPayState()&&(tempOrder.getShop().equals(shopItem.getShop()))){
                tempOrder.addItem(shopItem,quantity);
                tempOrder.setTotalPrice(tempOrder.getTotalPrice()+shopItem.getShopItemPrice()*quantity);
                return tempOrder;
            }
        }
        /**
         * 不满足前面if条件则新建order
         */
        Order newOrder=new Order();
        newOrder.setCustomer(this);
        newOrder.setShop(shopItem.getShop());
        newOrder.setConfirm(0);
        newOrder.setTotalPrice(shopItem.getShopItemPrice()*quantity);
        newOrder.addItem(shopItem,quantity);
        return newOrder;
    }

    /**
     * 顾客支付订单
     */
    public void payOrder(Order order) {
        order.setPayState(true);
    }

    /**
     * 顾客取消未支付的订单
     * 逻辑删除
     */
    public String cancelOrder(Order order) {
        if (!order.isPayState()) {
            order.setIsDeleted(1);
            return "取消订单成功";
        }
        return "订单已支付，不能取消";
    }
}