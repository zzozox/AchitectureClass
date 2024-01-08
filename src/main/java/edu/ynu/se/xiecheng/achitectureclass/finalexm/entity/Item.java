package edu.ynu.se.xiecheng.achitectureclass.finalexm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.ynu.se.xiecheng.achitectureclass.common.entity.LogicEntity;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@ToString(exclude = {"business", "shopItems"})
@Getter
@Setter
@Entity
@Where(clause = "is_deleted = 0")
public class Item extends LogicEntity {
    @Column
    private String itemName;
    @Column
    private Double itemPrice;
    @Column
    private String Description;
    @Column String itemImg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("item")
    @JsonIgnore
    private Business business;

    @OneToMany(mappedBy = "item",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("item")
    @JsonIgnore
    private Set<ShopItem> shopItems;
}
