package edu.ynu.se.xiecheng.achitectureclass.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@DiscriminatorValue("0")
public class Student extends User{
    @Column
    private String studentName;
    @Column
    private String major;
    @Column
    private String grade;
    @Column
    private String school;

    @OneToMany(mappedBy = "student",
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"student"})
    private Set<Selection> selections;


    public Selection select(TClass cls){
        /**
         *   教学班已经满了，不能再选课。
         */
        if (cls.getSelections().size() >= cls.getCapacity())
            return null;
        /**
         * 教学班已经选过了，不能重复选课。
         */
        Selection selection = cls.getSelection(this);
        if (selection!=null)
            return selection;
        selection = new Selection();
        selection.setStudent(this);
        selection.setTclass(cls);
        selections.add(selection);
        return selection;
    }

    public Selection withdraw(TClass cls){
        cls.getSelections().remove(cls.getSelection(this));
        return cls.getSelection(this);
    }
}
