package edu.ynu.se.xiecheng.achitectureclass.finalexm.controller;

import edu.ynu.se.xiecheng.achitectureclass.common.controller.LogicController;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.dao.BusinessDao;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.entity.Business;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.entity.Item;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.entity.Shop;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.service.BusinessService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/business")
public class BusinessController extends LogicController<BusinessService, BusinessDao, Business,Long> {
    public BusinessController(@Autowired BusinessService service){
        super(service);
    }

    /**
     * 商家登录
     * 登陆成功则返回值为1
     * @param businessName
     * @param password
     * @return
     */
    @ApiOperation("商家登录")
    @PostMapping("/login")
    public Business login(@ApiParam @RequestParam String businessName,
                         @ApiParam @RequestParam String password){
        return getService().login(businessName,password);
    }

    /**
     * 查看门店
     * @param businessId
     * @return
     */
    @ApiOperation("商家查看门店")
    @PostMapping("/listShops/{businessId}")
    public List<Shop> listShops(@ApiParam @PathVariable Long businessId){
        return getService().listShops(businessId);
    }

    /**
     * 新建门店
     * @param shopName
     * @param shopImg
     * @param businessId
     */
    @ApiOperation("商家新建门店")
    @PostMapping("/createShop")
    public Shop createShop(@ApiParam @RequestParam String shopName,
                           @ApiParam @RequestParam String shopImg,
                           @ApiParam @RequestParam Long businessId){
        Shop shop=new Shop();
        shop.setShopName(shopName);
        shop.setShopImg(shopImg);
        return getService().createShop(shop, businessId);
    }

    /**
     * 新建商品
     * @param itemName
     * @param itemImg
     * @param businessId
     * @return
     */
    @ApiOperation("商家新建商品")
    @PostMapping("/createItem")
    public Item createItem(@ApiParam @RequestParam String itemName,
                           @ApiParam @RequestParam String itemImg,
                           @ApiParam @RequestParam Long businessId){
        Item item=new Item();
        item.setItemName(itemName);
        item.setItemImg(itemImg);
        return getService().createItem(item, businessId);
    }


    /**
     * 商家在门店确认已支付的订单
     * @param orderId
     */
    @ApiOperation("商家确认已支付订单")
    @PostMapping("/confirmOrder/{orderId}")
    public String confirmOrder(@ApiParam @PathVariable Long orderId){
        return getService().confirmOrder(orderId);
    }

    /**
     * 商家在门店取消已支付的订单
     * @param orderId
     * @return
     */
    @ApiOperation("商家取消已支付订单")
    @PostMapping("/cancelOrder/{orderId}")
    public String cancelOrder(@ApiParam @PathVariable Long orderId){
        return getService().cancelOrder(orderId);
    }


    /**
     * 商家上架门店商品
     * @param shopItemId
     */
    @ApiOperation("商家上架门店商品")
    @PostMapping("/onsaleShopItem/{shopItemId}")
    public void onsaleShopItem(@ApiParam @PathVariable Long shopItemId){
        getService().onsaleShopItem(shopItemId);
    }

    @ApiOperation("列出商家商品")
    @PostMapping("/listItems/{businessId}")
    public List<Item> listItems(@ApiParam @PathVariable Long businessId){
        return getService().listItems(businessId);
    }
}
