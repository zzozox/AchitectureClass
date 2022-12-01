package edu.ynu.se.xiecheng.achitectureclass.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.ynu.se.xiecheng.achitectureclass.common.entity.LogicEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@Entity
@Getter
@Setter
@Where(clause = "is_deleted = 0")
public class TClass extends LogicEntity {
    @Column
    private String TTime;
    @Column
    private String TWeek;
    @Column
    private String room;
    @Column
    private Integer capacity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("clses")
    private Course course;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("clses")
    private Teacher teacher;
    @OneToMany(mappedBy = "tclass",
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("tclass")
    private Set<Selection> selections;


    /**
     * 判断学生是否在该教学班中
     * @param stu 学生实体
     * @return true or false
     */
    public  Boolean isInClass(Student stu){
        AtomicReference<Boolean> result = new AtomicReference<>(false);
        selections.forEach(selection -> {
            if(selection.getStudent().equals(stu))
                result.set(true);
        });
        return result.get();
    }

    public Selection getSelection(Student stu){
        AtomicReference<Selection> result = new AtomicReference<>(null);
        selections.forEach(selection -> {
            if(selection.getStudent().equals(stu))
                result.set(selection);
        });
        return  result.get();
    }

    /**
     * 获得该教学班的学生名单
     * @return  学生名单列表 */
    public List<Student> listStudents(){
        List<Student> results = new ArrayList<>();
        selections.forEach(selection -> {
            results.add(selection.getStudent());
        });
        return results;
    }

    /** 设置任课教师
     * @param teacher
     * @return 已设置的任课教师 */
    public Teacher assign(Teacher teacher){
        this.teacher = teacher;
        return  teacher;
    }

    /** 设置教学班的课程
     * @param course
     * @return 已设置的课程  */
    public Course assign(Course course){
        this.course = course;
        return  course;
    }

}
