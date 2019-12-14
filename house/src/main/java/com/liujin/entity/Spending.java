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
 * @since 2019-11-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Spending extends Model {

    private static final long serialVersionUID=1L;

    @TableId(value = "zid", type = IdType.AUTO)
    private Integer zid;

    private Integer eid;

    private Integer hid;

    private Float zmoney;

    private String ztm;

    private String ztime;

    private String zremark;

    @TableField(exist = false)
    private String haddress;
    @TableField(exist = false)
    private String hfh;
    @TableField(exist = false)
    private String ename;

}
