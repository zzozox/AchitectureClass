package edu.ynu.se.xiecheng.achitectureclass.finalexm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.ynu.se.xiecheng.achitectureclass.common.entity.LogicEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@ToString(exclude = {"shop", "item", "lineItems"})
@Getter
@Setter
@Entity
@Where(clause = "is_deleted = 0")
public class ShopItem extends LogicEntity {
    @Column
    private Double shopItemPrice;
    @Column
    private boolean onsale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("shopItems")
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("shopItems")
    private Item item;

    @OneToMany(mappedBy = "shopItem",
            fetch = FetchType.LAZY)
    @JsonIgnoreProperties("shopItem")
    @JsonIgnore
    private Set<LineItem> lineItems;
}
