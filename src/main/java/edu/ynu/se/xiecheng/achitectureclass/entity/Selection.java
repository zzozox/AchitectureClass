package edu.ynu.se.xiecheng.achitectureclass.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.ynu.se.xiecheng.achitectureclass.common.entity.LogicEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Where(clause = "is_deleted = 0")
public class Selection extends LogicEntity {
    @Column
    private Double midScore;
    @Column
    private Double finalScore;
    @Column
    private Double performanceScore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"selections"})
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"selections"})
    private TClass tclass;
}
