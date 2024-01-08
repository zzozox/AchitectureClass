package edu.ynu.se.xiecheng.achitectureclass.finalexm.controller;

import edu.ynu.se.xiecheng.achitectureclass.common.controller.LogicController;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.dao.ShopDao;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.entity.Order;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.entity.Shop;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.entity.ShopItem;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.service.ShopService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController extends LogicController<ShopService, ShopDao, Shop,Long> {
    public ShopController(@Autowired ShopService service){
        super(service);
    }

    /**
     * 商家在门店上架商品
     * 对应前端newShopItem页面的onsaleItem
     * @param itemId
     * @param shopId
     */
    @ApiOperation("商家在门店上架商品")
    @PostMapping("/onsaleItem")
    public ShopItem onsaleItem(@ApiParam @RequestParam Long itemId,
                               @ApiParam @RequestParam Long shopId,
                               @ApiParam @RequestParam Double price){
        return getService().onsaleItem(itemId, shopId, price);
    }

    /**
     * 列出商家的门店下所有订单
     * 对应前端showOrders页面的getOrders
     * @param shopId
     * @return
     */
    @ApiOperation("列出门店所有订单")
    @PostMapping("/listOrders/{shopId}")
    public List<Order> listOrders(@ApiParam @PathVariable Long shopId){
        return getService().listOrders(shopId);
    }

    /**
     * 列出门店所有商品
     * 对应前端shopItems页面的getShopItems
     * 对应前端chooseItem页面的getShopItems
     * @param shopId
     * @return
     */
    @ApiOperation("列出门店所有商品")
    @PostMapping("/listShopItems/{shopId}")
    public List<ShopItem> listShopItems(@ApiParam @PathVariable Long shopId){
        return getService().listShopItems(shopId);
    }
}
