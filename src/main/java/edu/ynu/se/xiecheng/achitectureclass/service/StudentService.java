package edu.ynu.se.xiecheng.achitectureclass.service;

import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;
import edu.ynu.se.xiecheng.achitectureclass.dao.StudentDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.TClassDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.Selection;
import edu.ynu.se.xiecheng.achitectureclass.entity.Student;
import edu.ynu.se.xiecheng.achitectureclass.entity.TClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
public class StudentService extends LogicService<Student,Long> {
    @Resource
    private TClassDao classDao;
    @Resource
    private StudentDao studentDao;
    public StudentService(@Autowired StudentDao sd) {
        super(sd);
        this.studentDao = sd;
    }
    /** 用于Controller调用
     * @param stu_id 学生ID
     * @param cls_id 教学班ID
     * @return     */
    @Transactional
    public Selection select(Long stu_id, Long cls_id){
        Student student = studentDao.getReferenceById(stu_id);
        TClass tClass = classDao.getReferenceById(cls_id);
        Selection selection = student.select(tClass);
        studentDao.save(student);
        return selection;
    }

    /**
     *  用于controller层调用
     * @param stu_id 学生ID
     * @param cls_id 教学班ID
     * @return
     */
    public Selection withDraw(Long stu_id, Long cls_id){
        Student student = studentDao.getReferenceById(stu_id);
        TClass tClass = classDao.getReferenceById(cls_id);
        Selection selection = student.withdraw(tClass);
        studentDao.save(student);
        return selection;
    }

    /** @param stu_id 学号
     * @return 还学生的所有课程
     */
    public List<TClass> getMyClasses(Long stu_id){
        Student stu = studentDao.getReferenceById(stu_id);
        return stu.getMyClasses();
    }
}
