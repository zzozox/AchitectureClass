package edu.ynu.se.xiecheng.achitectureclass.finalexm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.ynu.se.xiecheng.achitectureclass.common.entity.LogicEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@ToString(exclude = {"order", "shopItem"})
@Getter
@Setter
@Entity
@Where(clause = "is_deleted = 0")
public class LineItem extends LogicEntity {
    @Column
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("lineItems")
    @JsonIgnore
    private Order order;

    @ManyToOne(fetch =FetchType.LAZY)
    @JsonIgnoreProperties("lineItems")
    @JsonIgnore
    private ShopItem shopItem;
}
