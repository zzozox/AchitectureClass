package edu.ynu.se.xiecheng.achitectureclass;

import edu.ynu.se.xiecheng.achitectureclass.finalexm.entity.Item;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.entity.Shop;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.service.BusinessService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest
public class TestBusiness {

    @Resource
    BusinessService businessService;

    @Test
    public void TestLogin(){
        System.out.println(businessService.login("business","business"));
    }

    @Test
    public void TestListShops(){
        System.out.println(businessService.listShops(1l));
    }
    @Test
    public void TestCreateShop(){
        Shop shop=new Shop();
        shop.setShopName("门店1");
        shop.setShopImg("img");
        businessService.createShop(shop,1l);
    }
    @Test
    public void TestCreateItem(){
        Item item=new Item();
        item.setItemName("商品4");
        item.setItemImg("img");
        businessService.createItem(item,1l);
    }

    @Test
    public void TestConfirmOrder(){
        businessService.confirmOrder(1l);
    }
    @Test
    public void TestCancelOrder(){
        businessService.cancelOrder(1l);
    }

    @Test
    public void TestOnsaleShopItem(){
        businessService.onsaleShopItem(1l);
        businessService.onsaleShopItem(2l);
        businessService.onsaleShopItem(3l);
        businessService.onsaleShopItem(4l);
    }
}
