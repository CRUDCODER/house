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
 * @since 2019-10-25
 * 登记续费模块
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Charge extends Model {

    private static final long serialVersionUID=1L;

    @TableId(value = "yid", type = IdType.AUTO)
    private Integer yid;

    private Integer mid;

    private Integer eid;

    private Float myzj;

    private String mbegintime;

    /************************自己加的**************************/
    @TableField(exist = false)
    private String ename;
    @TableField(exist = false)
    private String haddress;
    @TableField(exist = false)
    private String hfh;
    @TableField(exist = false)
    private String cname;
    @TableField(exist = false)
    private String ctel;
}
