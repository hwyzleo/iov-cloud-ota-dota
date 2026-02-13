package net.hwyz.iov.cloud.ota.dota.service.infrastructure.repository.dao;

import net.hwyz.iov.cloud.ota.dota.service.infrastructure.repository.po.ConfigWordProfilePo;
import net.hwyz.iov.cloud.framework.mysql.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 配置字配置文件表 DAO
 * </p>
 *
 * @author hwyz_leo
 * @since 2026-02-13
 */
@Mapper
public interface ConfigWordProfileDao extends BaseDao<ConfigWordProfilePo, Long> {

    /**
     * 根据配置字代码和配置文件代码查询配置字配置文件
     *
     * @param configWordCode 配置字代码
     * @param code           配置文件代码
     * @return 配置字配置文件
     */
    ConfigWordProfilePo selectPoByConfigWordCodeAndCode(String configWordCode, String code);

}
