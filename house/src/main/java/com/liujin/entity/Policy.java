package com.liujin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.BlobTypeHandler;
import org.apache.ibatis.type.JdbcType;

/**
 * <p>
 * 
 * </p>
 *
 * @author liujin
 * @since 2019-11-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Policy extends Model {
    private static final long serialVersionUID=1L;
    @TableId(value = "cid", type = IdType.AUTO)
    private Integer cid;
    private String ctitle;
    private String ctime;
    @TableField(jdbcType = JdbcType.BLOB,typeHandler = BlobTypeHandler.class)
    private byte[] cremark;
    @TableField(exist = false)
    private String content;
}
