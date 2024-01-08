package edu.ynu.se.xiecheng.achitectureclass.finalexm.controller;

import edu.ynu.se.xiecheng.achitectureclass.common.controller.LogicController;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.dao.ItemDao;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.entity.Item;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController extends LogicController<ItemService, ItemDao, Item,Long> {
    public ItemController(@Autowired ItemService service){
        super(service);
    }
}
