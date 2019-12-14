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
public class Maintenance extends Model {

    private static final long serialVersionUID=1L;

    @TableId(value = "wid", type = IdType.AUTO)
    private Integer wid;

    private Integer hid;

    private Integer bid;

    private String wtime;

    private String wremark;

    private Integer eid;

    /***自己加的**/
    @TableField(exist = false)
    private String haddress;
    @TableField(exist = false)
    private String hfh;
    @TableField(exist = false)
    private String wxename;
    @TableField(exist = false)
    private String bsename;
    @TableField(exist = false)
    private Integer bseid;
    @TableField(exist = false)
    private Integer wxeid;
    @TableField(exist = false)
    private String bremark;
    @TableField(exist = false)
    private String mtime;
}
