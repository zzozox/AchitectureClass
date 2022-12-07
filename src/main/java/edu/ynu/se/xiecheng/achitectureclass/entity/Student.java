package edu.ynu.se.xiecheng.achitectureclass.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
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


    @JsonIgnore
    public Selection select(TClass cls){
        /**
         *   教学班已经满了，不能再选课。
         */
        if (cls.getSelections().size() >= cls.getCapacity())
            return null;
        /**
         * 教学班已经选过了，不能重复选课。
         */
        Selection selection = cls.findSelection(this);
        if (selection!=null)
            return selection;
        selection = new Selection();
        selection.setStudent(this);
        selection.setTclass(cls);
        selections.add(selection);
        return selection;
    }

    public List<TClass> listMyClasses(){
       List<TClass> classes = new ArrayList<>();
        selections.forEach(selection -> {
            classes.add(selection.getTclass());
        });
        return classes;
    }


    /**
     * 获取我的课程表
     * @return
     */
    public List<List<String>> listTimeTable(){
        List<TClass> classes = this.listMyClasses();
        List<List<String>> table = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            List<String> courses =new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                courses.add("");
            }
            table.add(courses);
        }
        classes.forEach(cls->{
            table.get(Integer.parseInt(cls.getTWeek())).
                    set(Integer.parseInt(cls.getTTime()),
                            cls.getCourse().getCourseName()+" "
                                    +cls.getRoom()+" "
                                    +cls.getTeacher().getTeacherName());
        });
        return table;
    }



    public Selection withdraw(TClass cls){
        Selection selection = cls.findSelection(this);
        cls.getSelections().remove(selection);
        return selection;
    }
}
