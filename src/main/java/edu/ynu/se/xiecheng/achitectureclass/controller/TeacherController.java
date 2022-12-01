package edu.ynu.se.xiecheng.achitectureclass.controller;

import edu.ynu.se.xiecheng.achitectureclass.common.controller.BaseController;
import edu.ynu.se.xiecheng.achitectureclass.entity.Course;
import edu.ynu.se.xiecheng.achitectureclass.entity.Teacher;
import edu.ynu.se.xiecheng.achitectureclass.service.CourseService;
import edu.ynu.se.xiecheng.achitectureclass.service.TeacherService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "教师实体的控制器")
@RestController
@RequestMapping("/teacher")
public class TeacherController extends BaseController<Teacher,Long> {

    private TeacherService service;

    public TeacherController(@Autowired TeacherService service){
        super(service);
        this.service =  service;
    }

}
