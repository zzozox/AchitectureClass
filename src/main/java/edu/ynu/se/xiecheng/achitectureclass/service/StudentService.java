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
public class StudentService extends LogicService<StudentDao,Student,Long> {
    @Resource
    private TClassDao classDao;
    public StudentService(@Autowired StudentDao sd) {
        super(sd);
    }
    /** 用于Controller调用
     * @param stu_id 学生ID
     * @param cls_id 教学班ID
     * @return    选课信息 */
    @Transactional
    public Selection select(Long stu_id, Long cls_id){
        Student student = getDAO().getReferenceById(stu_id);
        TClass tClass = classDao.getReferenceById(cls_id);
        Selection selection = student.select(tClass);
        getDAO().save(student);
        return selection;
    }

    /**
     *  用于controller层调用
     * @param stu_id 学生ID
     * @param cls_id 教学班ID
     * @return 退课信息
     */
    public Selection withDraw(Long stu_id, Long cls_id){
        Student student = getDAO().getReferenceById(stu_id);
        TClass tClass = classDao.getReferenceById(cls_id);
        Selection selection = student.withdraw(tClass);
        getDAO().save(student);
        return selection;
    }

    /** @param stu_id 学号
     * @return 还学生的所有课程
     */
    public List<TClass> getMyClasses(Long stu_id){
        Student stu = getDAO().getReferenceById(stu_id);
        return stu.listMyClasses();
    }

    /**
     * 获取学生的课程表
     * @param stu_id 学号
     * @return 二维表格式的课程表
     */
    public List<List<String>> getTimeTable(Long stu_id){
        Student stu = getDAO().getReferenceById(stu_id);
        return stu.listTimeTable();
    }
}
