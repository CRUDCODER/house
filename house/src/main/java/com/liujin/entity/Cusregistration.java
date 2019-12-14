package com.liujin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author liujin
 * @since 2019-10-19
 * 客户登记模块
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Cusregistration extends Model<Cusregistration> {

    private static final long serialVersionUID=1L;
    /**
     * 主键值 客户编号
     */
    @TableId(value = "cid", type = IdType.AUTO)
    private Integer cid;
    /**
     * 外键 员工编号
     */
    private Integer eid;
    /**
     * 客户名称
     */
    private String cname;
    /**
     * 客户性别
     */
    private String csex;
    /**
     * 客户电话
     */
    private String ctel;
    /**
     * 客户备用电话
     */
    private String ctel1;
    /**
     * 客户身份证
     */
    private String ccard;

    //员工姓名
    @TableField(exist = false)
    private String ename;

}
