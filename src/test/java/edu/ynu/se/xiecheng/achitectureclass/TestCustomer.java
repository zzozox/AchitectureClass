package edu.ynu.se.xiecheng.achitectureclass;

import edu.ynu.se.xiecheng.achitectureclass.finalexm.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class TestCustomer {
    @Resource
    CustomerService customerService;

    @Test
    public void TestLogin(){
        System.out.println(customerService.login("customer","customer"));
    }

    @Test
    public void TestPlaceOrder(){
        customerService.placeOrder(1l,1,2l);
        customerService.placeOrder(2l,2,2l);
    }

    @Test
    public void TestPayOrder() {
        customerService.payOrder(1l);
    }

    @Test
    public void TestCancelOrder(){customerService.cancelOrder(2l);}
}
