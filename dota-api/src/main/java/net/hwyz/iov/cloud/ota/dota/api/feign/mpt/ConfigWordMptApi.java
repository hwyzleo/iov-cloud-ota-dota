package net.hwyz.iov.cloud.ota.dota.api.feign.mpt;

import jakarta.servlet.http.HttpServletResponse;
import net.hwyz.iov.cloud.framework.common.web.domain.AjaxResult;
import net.hwyz.iov.cloud.framework.common.web.page.TableDataInfo;
import net.hwyz.iov.cloud.ota.dota.api.contract.ConfigWordFieldMpt;
import net.hwyz.iov.cloud.ota.dota.api.contract.ConfigWordMpt;
import net.hwyz.iov.cloud.ota.dota.api.contract.ConfigWordProfileMpt;

/**
 * 配置字相关管理后台接口
 *
 * @author hwyz_leo
 */
public interface ConfigWordMptApi {

    /**
     * 分页查询配置字
     *
     * @param configWord 配置字
     * @return 配置字列表
     */
    TableDataInfo list(ConfigWordMpt configWord);

    /**
     * 分页查询配置字配置文件
     *
     * @param configWordCode    配置字代码
     * @param configWordProfile 配置字配置文件
     * @return 配置字配置文件列表
     */
    TableDataInfo listProfile(String configWordCode, ConfigWordProfileMpt configWordProfile);

    /**
     * 分页查询配置字字段
     *
     * @param configWordCode  配置字代码
     * @param configWordField 配置字字段
     * @return 配置字字段列表
     */
    TableDataInfo listField(String configWordCode, ConfigWordFieldMpt configWordField);

    /**
     * 导出配置字
     *
     * @param response   响应
     * @param configWord 配置字
     */
    void export(HttpServletResponse response, ConfigWordMpt configWord);

    /**
     * 根据配置字ID获取配置字
     *
     * @param configWordId 配置字ID
     * @return 配置字
     */
    AjaxResult getInfo(Long configWordId);

    /**
     * 根据配置字配置文件ID获取配置字配置文件
     *
     * @param configWordCode      配置字代码
     * @param configWordProfileId 配置字配置文件ID
     * @return 配置字配置文件
     */
    AjaxResult getProfileInfo(String configWordCode, Long configWordProfileId);

    /**
     * 根据配置字字段ID获取配置字字段
     *
     * @param configWordCode    配置字代码
     * @param configWordFieldId 配置字字段ID
     * @return 配置字字段
     */
    AjaxResult getFieldInfo(String configWordCode, Long configWordFieldId);

    /**
     * 新增配置字
     *
     * @param configWord 配置字
     * @return 结果
     */
    AjaxResult add(ConfigWordMpt configWord);

    /**
     * 新增配置字配置文件
     *
     * @param configWordCode    配置字代码
     * @param configWordProfile 配置字配置文件
     * @return 结果
     */
    AjaxResult addProfile(String configWordCode, ConfigWordProfileMpt configWordProfile);

    /**
     * 新增配置字字段
     *
     * @param configWordCode  配置字代码
     * @param configWordField 配置字字段
     * @return 结果
     */
    AjaxResult addField(String configWordCode, ConfigWordFieldMpt configWordField);

    /**
     * 修改保存配置字
     *
     * @param configWord 配置字
     * @return 结果
     */
    AjaxResult edit(ConfigWordMpt configWord);

    /**
     * 修改保存配置字配置文件
     *
     * @param configWordCode    配置字代码
     * @param configWordProfile 配置字配置文件
     * @return 结果
     */
    AjaxResult editProfile(String configWordCode, ConfigWordProfileMpt configWordProfile);

    /**
     * 修改保存配置字字段
     *
     * @param configWordCode  配置字代码
     * @param configWordField 配置字字段
     * @return 结果
     */
    AjaxResult editField(String configWordCode, ConfigWordFieldMpt configWordField);

    /**
     * 删除配置字
     *
     * @param configWordIds 配置字ID数组
     * @return 结果
     */
    AjaxResult remove(Long[] configWordIds);

    /**
     * 删除配置字配置文件
     *
     * @param configWordCode       配置字代码
     * @param configWordProfileIds 配置字配置文件ID数组
     * @return 结果
     */
    AjaxResult removeProfile(String configWordCode, Long[] configWordProfileIds);

    /**
     * 删除配置字字段
     *
     * @param configWordCode     配置字代码
     * @param configWordFieldIds 配置字字段ID数组
     * @return 结果
     */
    AjaxResult removeField(String configWordCode, Long[] configWordFieldIds);

}
