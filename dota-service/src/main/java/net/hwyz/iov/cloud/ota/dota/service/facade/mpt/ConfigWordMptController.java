package net.hwyz.iov.cloud.ota.dota.service.facade.mpt;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.framework.audit.annotation.Log;
import net.hwyz.iov.cloud.framework.audit.enums.BusinessType;
import net.hwyz.iov.cloud.framework.common.web.controller.BaseController;
import net.hwyz.iov.cloud.framework.common.web.domain.AjaxResult;
import net.hwyz.iov.cloud.framework.common.web.page.TableDataInfo;
import net.hwyz.iov.cloud.framework.security.annotation.RequiresPermissions;
import net.hwyz.iov.cloud.framework.security.util.SecurityUtils;
import net.hwyz.iov.cloud.ota.dota.api.contract.ConfigWordFieldMpt;
import net.hwyz.iov.cloud.ota.dota.api.contract.ConfigWordMpt;
import net.hwyz.iov.cloud.ota.dota.api.contract.ConfigWordProfileMpt;
import net.hwyz.iov.cloud.ota.dota.api.feign.mpt.ConfigWordMptApi;
import net.hwyz.iov.cloud.ota.dota.service.application.service.ConfigWordAppService;
import net.hwyz.iov.cloud.ota.dota.service.facade.assembler.ConfigWordFieldMptAssembler;
import net.hwyz.iov.cloud.ota.dota.service.facade.assembler.ConfigWordMptAssembler;
import net.hwyz.iov.cloud.ota.dota.service.facade.assembler.ConfigWordProfileMptAssembler;
import net.hwyz.iov.cloud.ota.dota.service.infrastructure.repository.po.ConfigWordFieldPo;
import net.hwyz.iov.cloud.ota.dota.service.infrastructure.repository.po.ConfigWordPo;
import net.hwyz.iov.cloud.ota.dota.service.infrastructure.repository.po.ConfigWordProfilePo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 配置字相关管理接口实现类
 *
 * @author hwyz_leo
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/mpt/configWord")
public class ConfigWordMptController extends BaseController implements ConfigWordMptApi {

    private final ConfigWordAppService configWordAppService;

    /**
     * 分页查询配置字
     *
     * @param configWord 配置字
     * @return 配置字列表
     */
    @RequiresPermissions("ota:dota:configWord:list")
    @Override
    @GetMapping(value = "/list")
    public TableDataInfo list(ConfigWordMpt configWord) {
        logger.info("管理后台用户[{}]分页查询配置字", SecurityUtils.getUsername());
        startPage();
        List<ConfigWordPo> configWordPoList = configWordAppService.search(configWord.getDeviceCode(), configWord.getCode(),
                configWord.getName(), getBeginTime(configWord), getEndTime(configWord));
        List<ConfigWordMpt> configWordMptList = ConfigWordMptAssembler.INSTANCE.fromPoList(configWordPoList);
        return getDataTable(configWordPoList, configWordMptList);
    }

    /**
     * 分页查询配置字配置文件
     *
     * @param configWordCode    配置字代码
     * @param configWordProfile 配置字配置文件
     * @return 配置字配置文件列表
     */
    @RequiresPermissions("ota:dota:configWord:list")
    @Override
    @GetMapping(value = "/{configWordCode}/profile/list")
    public TableDataInfo listProfile(@PathVariable String configWordCode, ConfigWordProfileMpt configWordProfile) {
        logger.info("管理后台用户[{}]分页查询配置字[{}]配置文件", SecurityUtils.getUsername(), configWordCode);
        startPage();
        List<ConfigWordProfilePo> configWordProfilePoList = configWordAppService.searchProfile(configWordProfile.getConfigWordCode(),
                configWordProfile.getCode(), configWordProfile.getName(), getBeginTime(configWordProfile), getEndTime(configWordProfile));
        List<ConfigWordProfileMpt> configWordProfileMptList = ConfigWordProfileMptAssembler.INSTANCE.fromPoList(configWordProfilePoList);
        return getDataTable(configWordProfilePoList, configWordProfileMptList);
    }

    /**
     * 分页查询配置字字段
     *
     * @param configWordCode  配置字代码
     * @param configWordField 配置字字段
     * @return 配置字字段列表
     */
    @RequiresPermissions("ota:dota:configWord:list")
    @Override
    @GetMapping(value = "/{configWordCode}/field/list")
    public TableDataInfo listField(@PathVariable String configWordCode, ConfigWordFieldMpt configWordField) {
        logger.info("管理后台用户[{}]分页查询配置字[{}]字段", SecurityUtils.getUsername(), configWordCode);
        startPage();
        List<ConfigWordFieldPo> configWordFieldPoList = configWordAppService.searchField(configWordField.getConfigWordCode(),
                configWordField.getCode(), configWordField.getName(), getBeginTime(configWordField), getEndTime(configWordField));
        List<ConfigWordFieldMpt> configWordFieldMptList = ConfigWordFieldMptAssembler.INSTANCE.fromPoList(configWordFieldPoList);
        return getDataTable(configWordFieldPoList, configWordFieldMptList);
    }

    /**
     * 导出配置字
     *
     * @param response   响应
     * @param configWord 配置字
     */
    @Log(title = "配置字管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("ota:dota:configWord:export")
    @Override
    @PostMapping("/export")
    public void export(HttpServletResponse response, ConfigWordMpt configWord) {
        logger.info("管理后台用户[{}]导出配置字", SecurityUtils.getUsername());
    }

    /**
     * 根据配置字ID获取配置字
     *
     * @param configWordId 配置字ID
     * @return 配置字
     */
    @RequiresPermissions("ota:dota:configWord:query")
    @Override
    @GetMapping(value = "/{configWordId}")
    public AjaxResult getInfo(@PathVariable Long configWordId) {
        logger.info("管理后台用户[{}]根据配置字ID[{}]获取配置字", SecurityUtils.getUsername(), configWordId);
        ConfigWordPo configWordPo = configWordAppService.getConfigWordById(configWordId);
        return success(ConfigWordMptAssembler.INSTANCE.fromPo(configWordPo));
    }

    /**
     * 根据配置字配置文件ID获取配置字配置文件
     *
     * @param configWordCode      配置字代码
     * @param configWordProfileId 配置字配置文件ID
     * @return 配置字配置文件
     */
    @RequiresPermissions("ota:dota:configWord:query")
    @Override
    @GetMapping(value = "/{configWordCode}/profile/{configWordProfileId}")
    public AjaxResult getProfileInfo(@PathVariable String configWordCode, @PathVariable Long configWordProfileId) {
        logger.info("管理后台用户[{}]根据配置字[{}]配置文件ID[{}]获取配置字配置文件", SecurityUtils.getUsername(), configWordCode, configWordProfileId);
        ConfigWordProfilePo configWordProfilePo = configWordAppService.getConfigWordProfileById(configWordCode, configWordProfileId);
        return success(ConfigWordProfileMptAssembler.INSTANCE.fromPo(configWordProfilePo));
    }

    /**
     * 根据配置字字段ID获取配置字字段
     *
     * @param configWordCode    配置字代码
     * @param configWordFieldId 配置字字段ID
     * @return 配置字字段
     */
    @RequiresPermissions("ota:dota:configWord:query")
    @Override
    @GetMapping(value = "/{configWordCode}/field/{configWordFieldId}")
    public AjaxResult getFieldInfo(@PathVariable String configWordCode, @PathVariable Long configWordFieldId) {
        logger.info("管理后台用户[{}]根据配置字[{}]字段ID[{}]获取配置字字段", SecurityUtils.getUsername(), configWordCode, configWordFieldId);
        ConfigWordFieldPo configWordFieldPo = configWordAppService.getConfigWordFieldById(configWordCode, configWordFieldId);
        return success(ConfigWordFieldMptAssembler.INSTANCE.fromPo(configWordFieldPo));
    }

    /**
     * 新增配置字
     *
     * @param configWord 配置字
     * @return 结果
     */
    @Log(title = "配置字管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("ota:dota:configWord:add")
    @Override
    @PostMapping
    public AjaxResult add(@Validated @RequestBody ConfigWordMpt configWord) {
        logger.info("管理后台用户[{}]新增配置字[{}]", SecurityUtils.getUsername(), configWord.getCode());
        if (!configWordAppService.checkCodeUnique(configWord.getId(), configWord.getCode())) {
            return error("新增配置字'" + configWord.getCode() + "'失败，配置字代码已存在");
        }
        ConfigWordPo configWordPo = ConfigWordMptAssembler.INSTANCE.toPo(configWord);
        configWordPo.setCreateBy(SecurityUtils.getUserId().toString());
        return toAjax(configWordAppService.createConfigWord(configWordPo));
    }

    /**
     * 新增配置字配置文件
     *
     * @param configWordCode    配置字代码
     * @param configWordProfile 配置字配置文件
     * @return 结果
     */
    @Log(title = "配置字管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("ota:dota:configWord:edit")
    @Override
    @PostMapping("/{configWordCode}/profile")
    public AjaxResult addProfile(@PathVariable String configWordCode, @Validated @RequestBody ConfigWordProfileMpt configWordProfile) {
        logger.info("管理后台用户[{}]新增配置字[{}]配置文件[{}]", SecurityUtils.getUsername(), configWordCode, configWordProfile.getCode());
        if (!configWordAppService.checkProfileCodeUnique(configWordProfile.getId(), configWordCode, configWordProfile.getCode())) {
            return error("新增配置字配置文件'" + configWordProfile.getCode() + "'失败，配置字配置文件代码已存在");
        }
        ConfigWordProfilePo configWordProfilePo = ConfigWordProfileMptAssembler.INSTANCE.toPo(configWordProfile);
        configWordProfilePo.setCreateBy(SecurityUtils.getUserId().toString());
        return toAjax(configWordAppService.createConfigWordProfile(configWordCode, configWordProfilePo));
    }

    /**
     * 新增配置字字段
     *
     * @param configWordCode  配置字代码
     * @param configWordField 配置字字段
     * @return 结果
     */
    @Log(title = "配置字管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("ota:dota:configWord:edit")
    @Override
    @PostMapping("/{configWordCode}/field")
    public AjaxResult addField(@PathVariable String configWordCode, @Validated @RequestBody ConfigWordFieldMpt configWordField) {
        logger.info("管理后台用户[{}]新增配置字[{}]字段[{}]", SecurityUtils.getUsername(), configWordCode, configWordField.getCode());
        if (!configWordAppService.checkFieldCodeUnique(configWordField.getId(), configWordCode, configWordField.getCode())) {
            return error("新增配置字字段'" + configWordField.getCode() + "'失败，配置字字段代码已存在");
        }
        ConfigWordFieldPo configWordFieldPo = ConfigWordFieldMptAssembler.INSTANCE.toPo(configWordField);
        configWordFieldPo.setCreateBy(SecurityUtils.getUserId().toString());
        return toAjax(configWordAppService.createConfigWordField(configWordCode, configWordFieldPo));
    }

    /**
     * 修改保存配置字
     *
     * @param configWord 配置字
     * @return 结果
     */
    @Log(title = "配置字管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("ota:dota:configWord:edit")
    @Override
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody ConfigWordMpt configWord) {
        logger.info("管理后台用户[{}]修改保存配置字[{}]", SecurityUtils.getUsername(), configWord.getCode());
        if (!configWordAppService.checkCodeUnique(configWord.getId(), configWord.getCode())) {
            return error("修改保存配置字'" + configWord.getCode() + "'失败，配置字代码已存在");
        }
        ConfigWordPo configWordPo = ConfigWordMptAssembler.INSTANCE.toPo(configWord);
        configWordPo.setModifyBy(SecurityUtils.getUserId().toString());
        return toAjax(configWordAppService.modifyConfigWord(configWordPo));
    }

    /**
     * 修改保存配置字配置文件
     *
     * @param configWordCode    配置字代码
     * @param configWordProfile 配置字配置文件
     * @return 结果
     */
    @Log(title = "配置字管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("ota:dota:configWord:edit")
    @Override
    @PutMapping("/{configWordCode}/profile")
    public AjaxResult editProfile(@PathVariable String configWordCode, @Validated @RequestBody ConfigWordProfileMpt configWordProfile) {
        logger.info("管理后台用户[{}]修改保存配置字[{}]配置文件[{}]", SecurityUtils.getUsername(), configWordCode, configWordProfile.getCode());
        if (!configWordAppService.checkProfileCodeUnique(configWordProfile.getId(), configWordCode, configWordProfile.getCode())) {
            return error("修改保存配置字配置文件'" + configWordProfile.getCode() + "'失败，配置字配置文件代码已存在");
        }
        ConfigWordProfilePo configWordProfilePo = ConfigWordProfileMptAssembler.INSTANCE.toPo(configWordProfile);
        configWordProfilePo.setModifyBy(SecurityUtils.getUserId().toString());
        return toAjax(configWordAppService.modifyConfigWordProfile(configWordCode, configWordProfilePo));
    }

    /**
     * 修改保存配置字字段
     *
     * @param configWordCode  配置字代码
     * @param configWordField 配置字字段
     * @return 结果
     */
    @Log(title = "配置字管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("ota:dota:configWord:edit")
    @Override
    @PutMapping("/{configWordCode}/field")
    public AjaxResult editField(@PathVariable String configWordCode, @Validated @RequestBody ConfigWordFieldMpt configWordField) {
        logger.info("管理后台用户[{}]修改保存配置字[{}]字段[{}]", SecurityUtils.getUsername(), configWordCode, configWordField.getCode());
        if (!configWordAppService.checkFieldCodeUnique(configWordField.getId(), configWordCode, configWordField.getCode())) {
            return error("修改保存配置字字段'" + configWordField.getCode() + "'失败，配置字字段代码已存在");
        }
        ConfigWordFieldPo configWordFieldPo = ConfigWordFieldMptAssembler.INSTANCE.toPo(configWordField);
        configWordFieldPo.setModifyBy(SecurityUtils.getUserId().toString());
        return toAjax(configWordAppService.modifyConfigWordField(configWordCode, configWordFieldPo));
    }

    /**
     * 删除配置字
     *
     * @param configWordIds 配置字ID数组
     * @return 结果
     */
    @Log(title = "配置字管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("ota:dota:configWord:remove")
    @Override
    @DeleteMapping("/{configWordIds}")
    public AjaxResult remove(@PathVariable Long[] configWordIds) {
        logger.info("管理后台用户[{}]删除配置字[{}]", SecurityUtils.getUsername(), configWordIds);
        return toAjax(configWordAppService.deleteConfigWordByIds(configWordIds));
    }

    /**
     * 删除配置字配置文件
     *
     * @param configWordCode       配置字代码
     * @param configWordProfileIds 配置字配置文件ID数组
     * @return 结果
     */
    @Log(title = "配置字管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("ota:dota:configWord:edit")
    @Override
    @DeleteMapping("/{configWordCode}/field/{configWordProfileIds}")
    public AjaxResult removeProfile(@PathVariable String configWordCode, @PathVariable Long[] configWordProfileIds) {
        logger.info("管理后台用户[{}]删除配置字[{}]配置文件[{}]", SecurityUtils.getUsername(), configWordCode, configWordProfileIds);
        return toAjax(configWordAppService.deleteConfigWordProfileByIds(configWordCode, configWordProfileIds));
    }

    /**
     * 删除配置字字段
     *
     * @param configWordCode     配置字代码
     * @param configWordFieldIds 配置字字段ID数组
     * @return 结果
     */
    @Log(title = "配置字管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("ota:dota:configWord:edit")
    @Override
    @DeleteMapping("/{configWordCode}/field/{configWordFieldIds}")
    public AjaxResult removeField(@PathVariable String configWordCode, @PathVariable Long[] configWordFieldIds) {
        logger.info("管理后台用户[{}]删除配置字[{}]字段[{}]", SecurityUtils.getUsername(), configWordCode, configWordFieldIds);
        return toAjax(configWordAppService.deleteConfigWordFieldByIds(configWordCode, configWordFieldIds));
    }
}
