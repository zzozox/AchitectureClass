package edu.ynu.se.xiecheng.achitectureclass.finalexm.dao;

import edu.ynu.se.xiecheng.achitectureclass.common.dao.LogicDAO;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.entity.Customer;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerDao extends LogicDAO<Customer,Long> {
    /**
     * 顾客登录
     * @param customerName
     * @param password
     * @return
     */
    @Query("SELECT u FROM User u WHERE u.type = 0 AND u.username = :customerName AND u.password = :password")
    User getCustomerByCustomerNameAndPassword(@Param("customerName") String customerName,
                                              @Param("password") String password);
}
