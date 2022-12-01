package edu.ynu.se.xiecheng.achitectureclass.controller;

import edu.ynu.se.xiecheng.achitectureclass.common.controller.BaseController;
import edu.ynu.se.xiecheng.achitectureclass.entity.Selection;
import edu.ynu.se.xiecheng.achitectureclass.entity.Student;
import edu.ynu.se.xiecheng.achitectureclass.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "学生实体的控制器")
@RestController
@RequestMapping("/student")
public class StudentController extends BaseController<Student,Long> {

    private StudentService studentService;

    public StudentController(@Autowired StudentService ss){
        super(ss);
        studentService =  ss;
    }


    @ApiOperation("学生选课")
    @GetMapping("/select")
    public Selection select(@ApiParam("学号") Long stu_id,@ApiParam("教学班号") Long cls_id){
        return studentService.select(stu_id,cls_id);
    }
}
