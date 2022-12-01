package edu.ynu.se.xiecheng.achitectureclass.common.service;

import edu.ynu.se.xiecheng.achitectureclass.common.dao.LogicRepository;
import edu.ynu.se.xiecheng.achitectureclass.common.entity.LogicEntity;

import java.io.Serializable;
import java.util.List;

public class LogicService<T extends LogicEntity, ID extends Serializable>{

    public LogicService(LogicRepository<T,ID> lr){
        this.logicRepository = lr;
    }
    protected LogicRepository<T,ID> logicRepository;

    public T  GET(ID id){
        return logicRepository.getReferenceById(id);
    }

    public List<T> getAll(){
       return logicRepository.findAll();
    }

    public T  PUT(T  entity){
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
