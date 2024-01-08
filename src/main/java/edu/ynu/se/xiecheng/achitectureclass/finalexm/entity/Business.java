package edu.ynu.se.xiecheng.achitectureclass.finalexm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@ToString(exclude = {"shops", "items"})
@Getter
@Setter
@Entity
@DiscriminatorValue("1")
public class Business extends User {
    @Column
    private String businessName;
    @Column
    private String businessEmail;

    @OneToMany(mappedBy = "business",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("business")
    @JsonIgnore
    private Set<Shop> shops;

    @OneToMany(mappedBy = "business",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("business")
    @JsonIgnore
    private Set<Item> items;

    /**
     * 判断门店是否已存在
     * @param shop
     * @return
     */
    private Boolean shopExist(Shop shop){
        for(Shop tempshop : shops){
            if(tempshop.getShopName().equals(shop.getShopName()))
               return true;
        }
        return false;
    }

    /**
     * 商家创建门店
     * @param shop
     */
    public Shop createShop(Shop shop){
        if(shopExist(shop)){
            return null;
        }
        shop.setBusiness(this);
        shops.add(shop);
        return shop;
    }

    /**
     * 判断商品是否已存在
     * @param item
     * @return
     */
    private Boolean itemExist(Item item){
        for (Item tempitem:items){
            if(tempitem.getItemName().equals(item.getItemName()))
                return true;
        }
        return false;
    }

    /**
     * 商家新建商品
     * 新建的商品默认不上架
     * @param item
     */
    public Item createItem(Item item){
        if(itemExist(item)){
            return null;
        }
        item.setBusiness(this);
        items.add(item);
        return item;
    }

    /**
     * 列出商家门店
     * @return
     */
    public List<Shop> listShops(){
        return new ArrayList<>(shops);
    }

    /**
     * 商家在门店确认已支付的订单
     * @param order
     */
    public String confirmOrder(Order order){
        if(order.isPayState()){
            order.setConfirm(1);
            return "确认成功";
        }
        return "订单未支付";

    }

    /**
     * 商家在门店取消已支付的订单
     * 订单状态变为已退款
     * 软删除此订单
     * @param order
     */
    public String cancelOrder(Order order){
        if(order.isPayState()){
            order.setConfirm(-1);
            return "退款成功";
        }
        return "订单未支付";
    }

    /**
     * 商家上架门店商品
     * @param shopItem
     */
    public void onsaleShopItem(ShopItem shopItem){
        shopItem.setOnsale(true);
    }

    public List<Item> listItems(){
        return new ArrayList<>(items);
    }
}