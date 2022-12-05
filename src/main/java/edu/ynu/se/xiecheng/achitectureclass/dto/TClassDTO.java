package edu.ynu.se.xiecheng.achitectureclass.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TClassDTO {
    private Long id;
    private String TTime;
    private String TWeek;
    private String room;
    private Integer capacity;
    /**  这里指的是Teacher.id  */
    private Long teacherId;
    /** 这里指的是Teacher.teacherName */
    private String teacherTeacherName;
    /** 这里指的是Course.id  */
    private Long courseId;
    /** 这里指的是Course.courseName  */
    private String courseCourseName;
    /** 这里指的是Course.creditHours  */
    private Integer courseCreditHours;
    /** 这里指的是Course.credit  */
    private String courseCredit;

}
