package edu.ynu.se.xiecheng.achitectureclass.finalexm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.ynu.se.xiecheng.achitectureclass.common.entity.LogicEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@ToString(exclude = {"business", "shopItems", "orders"})
@Getter
@Setter
@Entity
@Where(clause = "is_deleted = 0")
public class Shop extends LogicEntity {
    @Column
    private String shopName;
    @Column
    private String shopImg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("shops")
    @JsonIgnore
    private Business business;

    @OneToMany(mappedBy = "shop",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("shop")
    @JsonIgnore
    private Set<ShopItem> shopItems;

    @OneToMany(mappedBy = "shop",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("shop")
    @JsonIgnore
    private Set<Order> orders;

    /**
     * 判断该商品是否已上架
     * @param item
     * @return
     */
    private Boolean shopItemExist(Item item){
        AtomicReference<Boolean> result = new AtomicReference<>(false);
        shopItems.forEach(tempShopItem -> {
            if(tempShopItem.getItem().equals(item))
                result.set(true);
            result.set(false);
        });
        return result.get();
    }

    /**
     * 商家在门店上架商品
     * @param item
     * @return
     */
    public ShopItem onsaleItem(Item item,Double price){
        if(shopItemExist(item)){
            return null;
        }
        ShopItem shopItem=new ShopItem();
        shopItem.setShop(this);
        shopItem.setItem(item);
        shopItem.setShopItemPrice(price);
        shopItems.add(shopItem);
        return shopItem;
    }

    /**
     * 列出门店订单
     * @return
     */
    public List<Order> listOrders(){
        return new ArrayList<>(orders);
    }

    /**
     * 列出门店所有商品
     * @return
     */
    public List<ShopItem> listShopItems(){return new ArrayList<>(shopItems);}

}
