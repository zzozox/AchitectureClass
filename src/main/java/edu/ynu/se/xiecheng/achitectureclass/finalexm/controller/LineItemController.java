package edu.ynu.se.xiecheng.achitectureclass.finalexm.controller;

import edu.ynu.se.xiecheng.achitectureclass.common.controller.LogicController;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.dao.LineItemDao;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.entity.LineItem;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.service.LineItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lineitem")
public class LineItemController extends LogicController<LineItemService, LineItemDao, LineItem,Long> {
    public LineItemController(@Autowired LineItemService service){
        super(service);
    }
}