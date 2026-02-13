package net.hwyz.iov.cloud.ota.dota.service.infrastructure.repository.dao;

import net.hwyz.iov.cloud.ota.dota.service.infrastructure.repository.po.ConfigWordFieldPo;
import net.hwyz.iov.cloud.framework.mysql.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 配置字字段表 DAO
 * </p>
 *
 * @author hwyz_leo
 * @since 2026-02-13
 */
@Mapper
public interface ConfigWordFieldDao extends BaseDao<ConfigWordFieldPo, Long> {

    /**
     * 根据配置字代码和字段代码查询字段
     *
     * @param configWordCode 配置字代码
     * @param code           字段代码
     * @return 字段
     */
    ConfigWordFieldPo selectPoByConfigWordCodeAndCode(String configWordCode, String code);

}
