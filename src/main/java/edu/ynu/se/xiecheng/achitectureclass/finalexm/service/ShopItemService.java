package edu.ynu.se.xiecheng.achitectureclass.finalexm.service;

import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.dao.ShopItemDao;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.entity.ShopItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopItemService extends LogicService<ShopItemDao, ShopItem,Long> {
    public ShopItemService(@Autowired ShopItemDao dao) {
        super(dao);
    }
}
