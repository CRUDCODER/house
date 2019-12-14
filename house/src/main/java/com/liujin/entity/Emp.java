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
public class Emp extends Model<Emp> {

    private static final long serialVersionUID=1L;

    @TableId(value = "eid", type = IdType.AUTO)
    private Integer eid;

    private Integer pid;

    private Integer jid;

    private String ename;

    private String epsw;

    private String erealname;

    private String etel;

    private String eaddress;

    private Integer eflag;

    private String eremark;
    @TableField(exist = false)
    private String pname;
    @TableField(exist = false)
    private String jname;

    @TableField(exist = false)
    private Dept dept;
    @TableField(exist = false)
    private Jobs jobs;
}
