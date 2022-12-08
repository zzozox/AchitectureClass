package edu.ynu.se.xiecheng.achitectureclass.service;

import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;
import edu.ynu.se.xiecheng.achitectureclass.dao.CourseDao;

import edu.ynu.se.xiecheng.achitectureclass.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CourseService extends LogicService<CourseDao,Course,Long> {
    public CourseService(@Autowired CourseDao dao) {
        super(dao);
    }
}
