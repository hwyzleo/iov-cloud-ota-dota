package net.hwyz.iov.cloud.ota.dota.service.infrastructure.repository.dao;

import net.hwyz.iov.cloud.ota.dota.service.infrastructure.repository.po.ConfigWordPo;
import net.hwyz.iov.cloud.framework.mysql.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 配置字表 DAO
 * </p>
 *
 * @author hwyz_leo
 * @since 2026-02-13
 */
@Mapper
public interface ConfigWordDao extends BaseDao<ConfigWordPo, Long> {

    /**
     * 根据配置字代码获取配置字
     *
     * @param code 配置字代码
     * @return 配置字
     */
    ConfigWordPo selectPoByCode(String code);

}
