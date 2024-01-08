package edu.ynu.se.xiecheng.achitectureclass.finalexm.service;

import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;

import edu.ynu.se.xiecheng.achitectureclass.finalexm.dao.BusinessDao;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.dao.OrderDao;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.dao.ShopItemDao;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BusinessService extends LogicService<BusinessDao, Business,Long> {

    public BusinessService(@Autowired BusinessDao dao) {
        super(dao);
    }

    @Resource
    ShopItemDao shopItemDao;
    @Resource
    OrderDao orderDao;
    /**
     * 商家登录
     * 登陆成功则返回值为1
     * @param businessName
     * @param password
     * @return
     */
    public Business login(String businessName,String password){
        User business= getDAO().getBusinessByBusinessNameAndPassword(businessName,password);
        return (Business) business;
    }

    /**
     * 查看门店
     * @param businessId
     * @return
     */
    @Transactional
    public List<Shop> listShops(Long businessId){
         Business business= getDAO().getReferenceById(businessId);
         return business.listShops();
    }

    /**
     * 新建门店
     * @param shop
     * @param businessId
     * @return
     */
    @Transactional
    public Shop createShop(Shop shop,Long businessId){
        Business business= getDAO().getReferenceById(businessId);
        business.createShop(shop);
        getDAO().save(business);
        return shop;
    }

    /**
     * 新建商品
     * @param item
     * @param businessId
     * @return
     */
    @Transactional
    public Item createItem(Item item,Long businessId){
        Business business= getDAO().getReferenceById(businessId);
        business.createItem(item);
        getDAO().save(business);
        return item;
    }


    /**
     * 商家在门店确认已支付的订单
     * @param orderId
     */
    @Transactional
    public String confirmOrder(Long orderId){
        Order order= orderDao.getReferenceById(orderId);
        Business business= order.getShop().getBusiness();
        String result=business.confirmOrder(order);
        getDAO().save(business);
        orderDao.save(order);
        return result;
    }

    /**
     * 商家在门店取消已支付的订单
     * @param orderId
     * @return
     */
    @Transactional
    public String cancelOrder(Long orderId){
        Order order= orderDao.getReferenceById(orderId);
        Business business= order.getShop().getBusiness();
        String result=business.cancelOrder(order);
        getDAO().save(business);
        orderDao.save(order);
        return result;
    }


    /**
     * 商家上架门店商品
     * @param shopItemId
     */
    @Transactional
    public void onsaleShopItem(Long shopItemId){
        ShopItem shopItem= shopItemDao.getReferenceById(shopItemId);
        Business business=shopItem.getShop().getBusiness();
        business.onsaleShopItem(shopItem);
        getDAO().save(business);
        shopItemDao.save(shopItem);
    }

    /**
     * 列出商家商品
     * @param businessId
     * @return
     */
    @Transactional
    public List<Item> listItems(Long businessId){
        Business business= getDAO().getReferenceById(businessId);
        return business.listItems();
    }
}
