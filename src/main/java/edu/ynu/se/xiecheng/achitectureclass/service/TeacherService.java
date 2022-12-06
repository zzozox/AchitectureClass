package edu.ynu.se.xiecheng.achitectureclass.service;

import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;
import edu.ynu.se.xiecheng.achitectureclass.dao.CourseDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.StudentDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.TClassDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.TeacherDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class TeacherService extends LogicService<Teacher,Long> {
    @Resource
    private TeacherDao teacherDao;
    @Resource
    private StudentDao studentDao;
    @Resource
    private TClassDao tClassDao;

    public TeacherService(@Autowired TeacherDao dao) {
        super(dao);
        this.teacherDao = dao;
    }

    public Selection rankMidScore(Long t_id, Long cls_id, Long stu_id, Double midScore){
        Teacher teacher = teacherDao.getReferenceById(t_id);
        Student student = studentDao.getReferenceById(stu_id);
        TClass tClass = tClassDao.getReferenceById(cls_id);
        Selection selection = teacher.rankMidScore(tClass,student,midScore);
        teacherDao.save(teacher);
        return selection;
    }

}
