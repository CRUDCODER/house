package com.liujin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class Income extends Model {

    private static final long serialVersionUID=1L;

    @TableId(value = "sid", type = IdType.AUTO)
    private Integer sid;

    private Integer eid;

    private Integer hid;

    private Float smoney;

    private String stm;

    private String stime;

    private String sremark;


    /********自己加的**********/

    @TableField(exist = false)
    private String haddress;
    @TableField(exist = false)
    private String hfh;
    @TableField(exist = false)
    private String ename;

}
