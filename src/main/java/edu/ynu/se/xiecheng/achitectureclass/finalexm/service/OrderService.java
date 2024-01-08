package edu.ynu.se.xiecheng.achitectureclass.finalexm.service;

import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.dao.OrderDao;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.entity.LineItem;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService extends LogicService<OrderDao, Order,Long> {
    public OrderService(@Autowired OrderDao dao) {
        super(dao);
    }

    /**
     * 列出订单详情项
     * @param orderId
     * @return
     */
    @Transactional
    public List<LineItem> listLineItems(Long orderId){
        Order order=getDAO().getReferenceById(orderId);
        return order.listLineItems();
    }
}
