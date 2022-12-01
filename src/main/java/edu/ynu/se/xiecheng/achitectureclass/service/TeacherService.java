package edu.ynu.se.xiecheng.achitectureclass.service;

import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;
import edu.ynu.se.xiecheng.achitectureclass.dao.CourseDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.TeacherDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.Course;
import edu.ynu.se.xiecheng.achitectureclass.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class TeacherService extends LogicService<Teacher,Long> {

    @Resource
    private TeacherDao teacherDao;

    public TeacherService(@Autowired TeacherDao dao) {
        super(dao);
        this.teacherDao = dao;
    }

}
