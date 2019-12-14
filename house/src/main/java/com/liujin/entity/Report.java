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
 * @since 2019-11-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Report extends Model {

    private static final long serialVersionUID=1L;

    @TableId(value = "bid", type = IdType.AUTO)
    private Integer bid;

    private Integer hid;

    private Integer eid;

    private Float dkd;

    private Float skd;

    private Float mkd;

    private String mtime;


    /*********自己加的*********/
    @TableField(exist = false)
    private String haddress;
    @TableField(exist = false)
    private String sname;
    @TableField(exist = false)
    private String aname;
    @TableField(exist = false)
    private String hfh;
    @TableField(exist = false)
    private Integer hflag;
    @TableField(exist = false)
    private Integer mid;
    @TableField(exist = false)
    private String ename;
}
