package net.hwyz.iov.cloud.ota.dota.api.contract;

import lombok.*;
import net.hwyz.iov.cloud.framework.common.web.domain.BaseRequest;

import java.util.Date;

/**
 * 管理后台配置字字段
 *
 * @author hwyz_leo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ConfigWordFieldMpt extends BaseRequest {

    /**
     * 主键
     */
    private Long id;

    /**
     * 配置字代码
     */
    private String configWordCode;

    /**
     * 配置字配置文件代码
     */
    private String configWordProfileCode;

    /**
     * 字段代码
     */
    private String code;

    /**
     * 字段名称
     */
    private String name;

    /**
     * 字节偏移量
     */
    private Integer byteOffset;

    /**
     * 位偏移量
     */
    private Integer bitOffset;

    /**
     * 位长度
     */
    private Integer bitLength;

    /**
     * 值类型
     */
    private String valueType;

    /**
     * 字节序
     */
    private String endianness;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

}
