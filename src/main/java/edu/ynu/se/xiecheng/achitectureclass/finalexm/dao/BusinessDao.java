package edu.ynu.se.xiecheng.achitectureclass.finalexm.dao;

import edu.ynu.se.xiecheng.achitectureclass.common.dao.LogicDAO;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.entity.Business;
import edu.ynu.se.xiecheng.achitectureclass.finalexm.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BusinessDao extends LogicDAO<Business,Long> {
    /**
     * 商家登录
     * @param businessName
     * @param password
     * @return
     */
    @Query("SELECT u FROM User u WHERE u.type = 1 AND u.username = :businessName AND u.password = :password")
    User getBusinessByBusinessNameAndPassword(@Param("businessName") String businessName,
                                              @Param("password") String password);
}
