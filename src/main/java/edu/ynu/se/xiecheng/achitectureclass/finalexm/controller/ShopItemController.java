package edu.ynu.se.xiecheng.achitectureclass.finalexm.controller;

import edu.ynu.se.xiecheng.achitectureclass.common.controller.LogicController;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.dao.ShopItemDao;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.entity.ShopItem;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.service.ShopItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopitem")
public class ShopItemController extends LogicController<ShopItemService, ShopItemDao, ShopItem,Long> {
    public ShopItemController(@Autowired ShopItemService service){
        super(service);
    }
}