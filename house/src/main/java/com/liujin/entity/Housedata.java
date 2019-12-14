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
 * @since 2019-10-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Housedata extends Model<Housedata> {

    private static final long serialVersionUID=1L;

    @TableId(value = "hid", type = IdType.AUTO)
    private Integer hid;

    private Integer sid;

    private Integer aid;

    private String haddress;

    private String hfh;

    private String hhx;

    private String hmj;

    private String hcx;

    private Float hmoney;

    private Float hwf;

    private Float hdx;

    private Float hsf;

    private Float hmq;

    private Float dkd;

    private Float skd;

    private Float mkd;

    private String hjp;

    private String hremark;

    private String himg;

    private Integer hflag;

    /******自己加的字段*****/
    @TableField(exist = false)
    private String aname;
    @TableField(exist = false)
    private String sname;
    @TableField(exist = false)
    private String flag;
    @TableField(exist = false)
    private Integer hmj2;
}
