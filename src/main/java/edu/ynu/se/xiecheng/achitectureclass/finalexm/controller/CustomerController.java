package edu.ynu.se.xiecheng.achitectureclass.finalexm.controller;

import edu.ynu.se.xiecheng.achitectureclass.common.controller.LogicController;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.dao.CustomerDao;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.entity.Customer;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.entity.Order;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController extends LogicController<CustomerService, CustomerDao, Customer,Long> {
    public CustomerController(@Autowired CustomerService service){
        super(service);
    }

    /**
     * 顾客登录
     * 对应前端登录页面的login
     * @param customerName
     * @param password
     * @return
     */
    @ApiOperation("顾客登录")
    @PostMapping("/login")
    public Customer login(@ApiParam @RequestParam String customerName,
                         @ApiParam @RequestParam String password) {
        return getService().login(customerName, password);
    }

    /**
     * 顾客下单
     * 对应前端chooseItem页面的placeOrder
     * @param shopItemId
     * @param customerId
     * @return
     */
    @ApiOperation("顾客下单")
    @PostMapping("/placeOrder")
    public Order placeOrder(@ApiParam @RequestParam Long shopItemId,
                            @ApiParam @RequestParam Integer quantity,
                            @ApiParam @RequestParam Long customerId) {
        return getService().placeOrder(shopItemId, quantity, customerId);
    }

    /**
     * 顾客支付订单
     * 对应前端payOrder页面的payOrder
     * @param orderId
     */
    @ApiOperation("顾客支付订单")
    @PostMapping("/payOrder/{orderId}")
    public void payOrder(@ApiParam @PathVariable Long orderId) {
        getService().payOrder(orderId);
    }

    /**
     * 顾客取消未支付的订单
     * 逻辑删除
     * 对应前端payOrder页面的cancelOrder
     * @param orderId
     * @return
     */
    @ApiOperation("顾客取消未支付订单")
    @PostMapping("/cancelOrder/{orderId}")
    public String cancelOrder(@ApiParam @PathVariable Long orderId) {
        return getService().cancelOrder(orderId);
    }
}