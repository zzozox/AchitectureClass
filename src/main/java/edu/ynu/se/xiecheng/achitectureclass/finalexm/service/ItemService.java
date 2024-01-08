package edu.ynu.se.xiecheng.achitectureclass.finalexm.service;

import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.dao.ItemDao;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService extends LogicService<ItemDao, Item,Long> {
    public ItemService(@Autowired ItemDao dao) {
        super(dao);
    }
}
