package edu.ynu.se.xiecheng.achitectureclass.finalexm.service;

import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.dao.LineItemDao;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.entity.LineItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LineItemService extends LogicService<LineItemDao, LineItem,Long> {
    public LineItemService(@Autowired LineItemDao dao) {
        super(dao);
    }
}
