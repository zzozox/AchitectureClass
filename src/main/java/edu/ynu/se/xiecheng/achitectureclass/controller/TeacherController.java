package edu.ynu.se.xiecheng.achitectureclass.controller;

import edu.ynu.se.xiecheng.achitectureclass.common.controller.BaseController;
import edu.ynu.se.xiecheng.achitectureclass.entity.Course;
import edu.ynu.se.xiecheng.achitectureclass.entity.Selection;
import edu.ynu.se.xiecheng.achitectureclass.entity.Teacher;
import edu.ynu.se.xiecheng.achitectureclass.service.CourseService;
import edu.ynu.se.xiecheng.achitectureclass.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
    @ApiOperation("评分-期中成绩")
    @GetMapping("/midscore")
    public Selection rankMidScore(@ApiParam("教师编号") Long t_id, @ApiParam("教学班号")Long cls_id,
                                  @ApiParam("学号") Long stu_id, @ApiParam("期中成绩") Double midScore){

        return  service.rankMidScore(t_id,cls_id,stu_id,midScore);
    }
}
