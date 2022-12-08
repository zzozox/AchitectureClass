package edu.ynu.se.xiecheng.achitectureclass.service;

import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;
import edu.ynu.se.xiecheng.achitectureclass.dao.CourseDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.TClassDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.TeacherDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.Course;
import edu.ynu.se.xiecheng.achitectureclass.entity.Student;
import edu.ynu.se.xiecheng.achitectureclass.entity.TClass;
import edu.ynu.se.xiecheng.achitectureclass.entity.Teacher;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class TClassService extends LogicService<TClassDao,TClass,Long> {
    @Resource
    private TeacherDao teacherDao;
    @Resource
    private CourseDao courseDao;
    public TClassService(@Autowired TClassDao dao) {
        super(dao);
    }
    public List<Student> listStudents(Long cls_id){
        TClass cls=getDAO().getReferenceById(cls_id);
        return cls.listStudents();
    }
    public Teacher assignTeacher(Long cls_id, Long t_id){
        TClass cls=getDAO().getReferenceById(cls_id);
        Teacher teacher=teacherDao.getReferenceById(t_id);
        cls.assign(teacher);
        getDAO().save(cls);
        return  teacher;
    }
    public Course assignCourse(Long c_id, Long cls_id){
        Course course=courseDao.getReferenceById(c_id);
        TClass cls=getDAO().getReferenceById(cls_id);
        cls.assign(course);
        getDAO().save(cls);
        return course;
    }
}
