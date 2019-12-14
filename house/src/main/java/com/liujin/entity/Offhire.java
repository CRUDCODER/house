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
 * 登记停租模块
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Offhire extends Model<Offhire> {

    private static final long serialVersionUID=1L;

    @TableId(value = "tid", type = IdType.AUTO)
    private Integer tid;

    private Integer mid;

    private Integer eid;

    private Float tyzj;

    private String mtime;

    private String mremark;



    /***自己加的**/
    /**
     * 判断是否损坏 1未损坏 0损坏
     */
    @TableField(exist = false)
    private Integer flag;
}
