package edu.ynu.se.xiecheng.achitectureclass.service;

import edu.ynu.se.xiecheng.achitectureclass.common.dao.LogicRepository;
import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;
import edu.ynu.se.xiecheng.achitectureclass.dao.StudentDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.TClassDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.Selection;
import edu.ynu.se.xiecheng.achitectureclass.entity.Student;
import edu.ynu.se.xiecheng.achitectureclass.entity.TClass;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class StudentService extends LogicService<Student,Long> {

    @Resource
    private TClassDao classDao;

    @Resource
    private StudentDao studentDao;

    public StudentService(StudentDao sd) {
        super(sd);
        this.studentDao = sd;
    }

    public Selection select(Long stu_id, Long cls_id){
        Student student = studentDao.getReferenceById(stu_id);
        TClass tClass = classDao.getReferenceById(cls_id);
        return select(student,tClass);
    }

    public Selection select(Student stu, TClass cls){
       Selection selection = stu.select(cls);
        studentDao.save(stu);
       return  selection;
    }

    public Selection withDraw(Long stu_id, Long cls_id){
        Student student = studentDao.getReferenceById(stu_id);
        TClass tClass = classDao.getReferenceById(cls_id);
        return withDraw(student,tClass);
    }

    public Selection withDraw(Student stu, TClass cls){
        Selection selection = stu.withdraw(cls);
        studentDao.save(stu);
        return selection;
    }
}
