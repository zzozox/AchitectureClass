package edu.ynu.se.xiecheng.achitectureclass.finalexm.service;

import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.dao.ItemDao;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.dao.ShopDao;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.entity.Item;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.entity.Order;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.entity.Shop;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.entity.ShopItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ShopService extends LogicService<ShopDao, Shop,Long> {
    public ShopService(@Autowired ShopDao dao) {
        super(dao);
    }

    @Resource
    ItemDao itemDao;

    /**
     * 门店上架商品
     * @param itemId
     * @param shopId
     */
    @Transactional
    public ShopItem onsaleItem(Long itemId, Long shopId, Double price){
        Shop shop= getDAO().getReferenceById(shopId);
        Item item=itemDao.getReferenceById(itemId);
        ShopItem result=shop.onsaleItem(item,price);
        getDAO().save(shop);
        return result;
    }

    /**
     * 列出门店所有订单
     * @param shopId
     * @return
     */
    @Transactional
    public List<Order> listOrders(Long shopId){
        Shop shop= getDAO().getReferenceById(shopId);
        return shop.listOrders();
    }

    /**
     * 列出门店所有商品
     * @param shopId
     * @return
     */
    @Transactional
    public List<ShopItem> listShopItems(Long shopId){
        Shop shop= getDAO().getReferenceById(shopId);
        return shop.listShopItems();
    }
}
