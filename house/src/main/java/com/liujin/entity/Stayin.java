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
 * 登记入住模块
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Stayin extends Model<Stayin> {

    private static final long serialVersionUID=1L;
    /**
     * 主键 登记入住编号
     */
    @TableId(value = "mid", type = IdType.AUTO)
    private Integer mid;
    /**
     * 入住时间
     */
    private String mdate;
    /**
     * 外键 员工编号
     */
    private Integer eid;
    /**
     * 外键 客户编号
     */
    private Integer cid;
    /**
     * 外键 房屋资料编号
     */
    private Integer hid;
    /**
     * 图片
     */
    private String mimg;
    /**
     * 房屋面积
     */
    private Float myj;
    /**
     * 房屋租金
     */
    private Float myzj;
    /**
     * 状态
     */
    private Integer mflag;
    /**
     * 收租时间
     */
    private  String mbegintime;

    /***************************自加字段**********************************/
    /**
     * 员工姓名 不与数据库映射
     */
    @TableField(exist = false)
    private String ename;
    /**
     * 客户姓名
     */
    @TableField(exist = false)
    private String cname;
    /**
     * 客户地址
     */
    @TableField(exist = false)
    private String haddress;

    /**
     * 房屋户号
     */
    @TableField(exist = false)
    private String hfh;

    /**
     * 客户电话
     */
    @TableField(exist = false)
    private String ctel;
    /**
     * 备用电话
     */
    @TableField(exist = false)
    private String ctel1;
    /**
     * 区域
     */
    @TableField(exist = false)
    private String aname;
    /**
     * 类型
     */
    @TableField(exist = false)
    private String sname;
    /**
     * 面积
     */
    @TableField(exist = false)
    private String hmj;
    /**
     * 租金
     */
    @TableField(exist = false)
    private String hmoney;
    @TableField(exist = false)
    private Integer sid;
    @TableField(exist = false)
    private Integer aid;
    @TableField(exist = false)
    private String hhx;
    @TableField(exist = false)
    private Float total;
}
