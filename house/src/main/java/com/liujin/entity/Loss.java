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
public class Loss extends Model<Loss> {

    private static final long serialVersionUID=1L;

    @TableId(value = "bid", type = IdType.AUTO)
    private Integer bid;

    private Integer hid;

    private String mtime;

    private String bremark;

    private Integer eid;

    private Integer bflag;
    /******自己加的******/
    @TableField(exist = false)
    private String haddress;
    @TableField(exist = false)
    private String hhx;
    @TableField(exist = false)
    private String hfh;
    @TableField(exist = false)
    private Integer aid;
    @TableField(exist = false)
    private Integer sid;
    @TableField(exist = false)
    private String ename;
}
