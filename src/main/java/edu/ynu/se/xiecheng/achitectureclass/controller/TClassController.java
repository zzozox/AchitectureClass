package edu.ynu.se.xiecheng.achitectureclass.controller;

import edu.ynu.se.xiecheng.achitectureclass.common.controller.BaseController;
import edu.ynu.se.xiecheng.achitectureclass.entity.Course;
import edu.ynu.se.xiecheng.achitectureclass.entity.Student;
import edu.ynu.se.xiecheng.achitectureclass.entity.TClass;
import edu.ynu.se.xiecheng.achitectureclass.entity.Teacher;
import edu.ynu.se.xiecheng.achitectureclass.service.CourseService;
import edu.ynu.se.xiecheng.achitectureclass.service.TClassService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "教学班实体的控制器")
@RestController
@RequestMapping("/class")
public class TClassController extends BaseController<TClass,Long> {
    private TClassService service;
    public TClassController(@Autowired TClassService service){
        super(service);
        this.service =  service;}
    @GetMapping("/students")
    @ApiOperation("获取教学班花名册")
    public List<Student> listStudents(@ApiParam("教学班ID") Long cls_id){
        return  service.listStudents(cls_id);}
    //可以尝试写一个DTO版本，过滤掉前端不需要的学生信息
    @GetMapping("/assignTeacher")
    @ApiOperation("分配任课教师")
    public Teacher assignTeacher(@ApiParam("教学班ID") Long cls_id,
                                 @ApiParam("教师ID")Long t_id){
        return  service.assignTeacher(cls_id,t_id);
    }
    @GetMapping("/assignCourse")
    @ApiOperation("设置教学班的课程")
    public Course assignCourse(@ApiParam("教学班ID") Long cls_id,
                                 @ApiParam("课程ID")Long c_id){
        return  service.assignCourse(c_id,cls_id);
    }
}
