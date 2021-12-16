package my.qunz.myDemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 首页应用表
 * </p>
 *
 * @author zouzhaowei
 * @since 2021-10-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("app_service")
public class AppService implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 网址
     */
    private String appUrl;

    /**
     * 封面地址
     */
    private String thumbUrl;

    /**
     * 宣传图
     */
    private String propagateUrl;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 0:未删除;1:已删除
     */
    private Integer deleted;


}
