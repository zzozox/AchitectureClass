package edu.ynu.se.xiecheng.achitectureclass.common.service;

import edu.ynu.se.xiecheng.achitectureclass.common.dao.LogicDAO;
import edu.ynu.se.xiecheng.achitectureclass.common.entity.LogicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;

public class LogicService<T extends LogicEntity, ID extends Serializable>{

    public LogicService(LogicDAO<T,ID> lr){
        this.logicRepository = lr;
    }
    protected LogicDAO<T,ID> logicRepository;

    public T  GET(ID id){
        return logicRepository.getReferenceById(id);
    }

    public List<T> getAll(){
       return logicRepository.findAll();
    }

    /**
     * 基础的分页查询
     * @param page 第几页
     * @param size 每页的大小
     * @return 返回一个分页集合
     */
    public Page<T> getAll(int page, int size){
        Pageable pageable = PageRequest.of(page,size, Sort.by("createTime").descending());
        return logicRepository.findAll(pageable);
    }

    public T  PUT(T entity){
       return logicRepository.save(entity);
    }

    public T  POST(T  entity){
        return logicRepository.save(entity);
    }

    public void DELETE(T entity){
        logicRepository.delete(entity);
    }

    public void DELETE(ID id){
        logicRepository.deleteById(id);
    }

}
