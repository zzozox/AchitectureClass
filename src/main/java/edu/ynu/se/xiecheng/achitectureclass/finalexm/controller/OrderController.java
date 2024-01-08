package edu.ynu.se.xiecheng.achitectureclass.finalexm.controller;

import edu.ynu.se.xiecheng.achitectureclass.common.controller.LogicController;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.dao.OrderDao;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.entity.LineItem;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.entity.Order;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController extends LogicController<OrderService, OrderDao, Order,Long> {
    public OrderController(@Autowired OrderService service){
        super(service);
    }

    /**
     * 列出订单的订单详项
     * 对应前端payOrder页面的getLineItems
     * @param orderId
     * @return
     */
    @ApiOperation("列出订单项")
    @PostMapping("/listLineItems/{orderId}")
    public List<LineItem> listLineItem(@ApiParam @PathVariable Long orderId){
       return getService().listLineItems(orderId);
    }
}