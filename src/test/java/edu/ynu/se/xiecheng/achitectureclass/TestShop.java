package edu.ynu.se.xiecheng.achitectureclass;

import edu.ynu.se.xiecheng.achitectureclass.finalexm.service.ShopService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class TestShop {

    @Resource
    ShopService shopService;

    @Test
    public void TestOnsaleItem(){
        shopService.onsaleItem(1l,1l,5.0);
        shopService.onsaleItem(2l,1l,6.0);
        shopService.onsaleItem(3l,1l,7.0);
        shopService.onsaleItem(4l,1l,8.0);
    }

    @Test
    public void TestListOrders(){
        //跨域是能正常请求到的
        System.out.println(shopService.listOrders(1l));
    }
}
