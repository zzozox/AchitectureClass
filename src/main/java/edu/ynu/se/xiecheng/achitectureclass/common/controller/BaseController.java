package edu.ynu.se.xiecheng.achitectureclass.common.controller;

import edu.ynu.se.xiecheng.achitectureclass.common.entity.LogicEntity;
import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.List;


@Api(tags ="基础Controller")
public abstract class BaseController<T extends LogicEntity, ID extends Serializable>{
    protected LogicService<T,ID> logicService;

    public BaseController(LogicService<T,ID> ls){
        this.logicService = ls;
    }

    @GetMapping("/get")
    @ApiOperation("通过ID获取实体")
    public T GET(@RequestParam ID id){
        return logicService.GET(id);
    }

    @ApiOperation("获取所有实体")
    @GetMapping("/getall")
    public List<T> getAll(){
        return logicService.getAll();
    }

    @ApiOperation("分页获取所有实体")
    @GetMapping("/getallPage")
    public Page<T> getAll(@RequestParam int page, @RequestParam int size){
        return logicService.getAll(page,size);
    }

    @ApiOperation("修改实体")
    @PostMapping("/put")
    public T PUT(@RequestBody T entity){
        return logicService.PUT(entity);
    }

    @ApiOperation("创建实体")
    @PostMapping("/post")
    public T POST(@RequestBody T  entity){
        return logicService.POST(entity);
    }

    @ApiOperation("删除实体")
    @PostMapping("/delete")
    public void DELETE(@RequestBody T entity){
        logicService.DELETE(entity);
    }

    @ApiOperation("通过ID删除实体")
    @GetMapping("/delete")
    public void DELETE(@RequestParam ID id){
        logicService.DELETE(id);
    }
}
