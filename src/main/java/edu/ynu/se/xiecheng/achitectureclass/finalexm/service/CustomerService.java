package edu.ynu.se.xiecheng.achitectureclass.finalexm.service;

import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.dao.CustomerDao;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.dao.OrderDao;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.dao.ShopItemDao;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class CustomerService extends LogicService<CustomerDao, Customer,Long> {
    public CustomerService(@Autowired CustomerDao dao) {
        super(dao);
    }

    @Resource
    OrderDao orderDao;
    @Resource
    ShopItemDao shopItemDao;

    /**
     * 顾客登录
     * @param customerName
     * @param password
     * @return
     */
    public Customer login(String customerName, String password) {
        User customer = getDAO().getCustomerByCustomerNameAndPassword(customerName, password);
        return (Customer) customer;
    }

    /**
     * 顾客下单
     * @param shopItemId
     * @param customerId
     * @return
     */
    @Transactional
    public Order placeOrder(Long shopItemId,Integer quantity, Long customerId) {
        Customer customer = getDAO().getReferenceById(customerId);
        ShopItem shopItem=shopItemDao.getReferenceById(shopItemId);
        Order order = customer.placeOrder(shopItem,quantity);
        getDAO().save(customer);
        orderDao.save(order);
        return order;
    }

    /**
     * 顾客支付订单
     * @param orderId
     */
    @Transactional
    public void payOrder(Long orderId) {
        Order order = orderDao.getReferenceById(orderId);
        Customer customer = order.getCustomer();
        customer.payOrder(order);
        getDAO().save(customer);
        orderDao.save(order);
    }

    /**
     * 顾客取消未支付的订单
     * 逻辑删除
     * @param orderId
     * @return
     */
    @Transactional
    public String cancelOrder(Long orderId) {
        Order order = orderDao.getReferenceById(orderId);
        Customer customer = order.getCustomer();
        getDAO().save(customer);
        orderDao.save(order);
        return customer.cancelOrder(order);
    }
}
