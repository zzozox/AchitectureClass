package edu.ynu.se.xiecheng.achitectureclass.finalexm.entity;

import edu.ynu.se.xiecheng.achitectureclass.common.entity.LogicEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.util.DigestUtils;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Inheritance
@Where(clause = "is_deleted = 0")
@DiscriminatorColumn(name = "type")
public abstract class User extends LogicEntity {
    @Column
    protected String username;

    @Column
    protected String password;

    /**
     * 创建所有User时，密码经过md5加密
     * @param psd
     */
    public void setPassword(String psd){
        this.password = DigestUtils.md5DigestAsHex(psd.getBytes());
    }

    @Column(updatable = false, insertable = false)
    protected Integer type;
}
