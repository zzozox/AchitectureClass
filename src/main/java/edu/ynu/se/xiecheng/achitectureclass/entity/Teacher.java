package edu.ynu.se.xiecheng.achitectureclass.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@DiscriminatorValue("1")
public class Teacher extends User{
    @Column
    private String teacherName;
    @Column
    private String school;
    @Column
    private String research;

    @OneToMany(mappedBy = "teacher",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("teacher")
    private Set<TClass> clses;

    /**
     * 录入期中成绩方法
     * @param cls 教学班
     * @param stu 学生
     * @param midScore 期中成绩
     */
    public void rankMidScore(TClass cls, Student stu, Double midScore){
        /** 不是自己的教学班，不能打分 */
        if (!clses.contains(cls))
            return;

        /** 设置期中成绩 */
       Selection selection = cls.getSelection(stu);
       selection.setMidScore(midScore);
    }
}
