package edu.ynu.se.xiecheng.achitectureclass.service;

import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;
import edu.ynu.se.xiecheng.achitectureclass.dao.CourseDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.StudentDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.TClassDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.Course;
import edu.ynu.se.xiecheng.achitectureclass.entity.Selection;
import edu.ynu.se.xiecheng.achitectureclass.entity.Student;
import edu.ynu.se.xiecheng.achitectureclass.entity.TClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class CourseService extends LogicService<Course,Long> {
    @Resource
    private CourseDao courseDao;
    public CourseService(@Autowired CourseDao dao) {
        super(dao);
        this.courseDao = dao;
    }
}
