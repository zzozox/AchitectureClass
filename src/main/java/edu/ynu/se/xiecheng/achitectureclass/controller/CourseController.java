package edu.ynu.se.xiecheng.achitectureclass.controller;

import edu.ynu.se.xiecheng.achitectureclass.common.controller.LogicController;
import edu.ynu.se.xiecheng.achitectureclass.dao.CourseDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.Course;
import edu.ynu.se.xiecheng.achitectureclass.service.CourseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "课程实体的控制器")
@RestController
@RequestMapping("/course")
public class CourseController extends LogicController<CourseService, CourseDao,Course,Long> {
    public CourseController(@Autowired CourseService service){
        super(service);
    }
}
