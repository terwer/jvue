package com.terwergreen.jvueserver.api.admin;

import com.terwergreen.jvueserver.service.MetaService;
import com.terwergreen.jvueserver.util.RestResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 标签管理
 *
 * @author Terwer
 * @version 1.0
 * 2019/3/24 20:51
 **/
@RestController
@RequestMapping(value = "api/admin/meta", produces = "application/json;charset=utf-8")
public class MetaManageApi {
    @Autowired
    private MetaService metaService;

    /**
     * 获取所有属性
     *
     * @return {@see List<MetaDto>}
     */
    @ApiOperation("获取所有属性")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "type", value = "类型")
    })
    @PostMapping("list")
    public RestResponse getAll(@RequestParam String type) {
        return RestResponse.ok(metaService.getMetaList(type));
    }

    /**
     * 根据name删除分类
     *
     * @param name 属性名
     * @param type 属性类型 {@see Types#CATEGORY},{@see Types#TAG}
     * @return {@see RestResponse.ok()}
     */
    @PostMapping("del")
    public RestResponse deleteMeta(@RequestParam String name, @RequestParam String type) {
        if (metaService.deleteMeta(name, type)) {
            return RestResponse.ok();
        }
        return RestResponse.fail();
    }

    /**
     * 添加一个分类
     *
     * @param name 属性名
     * @param type 属性类型 {@see Types#CATEGORY},{@see Types#TAG}
     * @return {@see RestResponse.ok()}
     */
    @PostMapping("save")
    public RestResponse saveMeta(@RequestParam String name, @RequestParam String type) {
        if (metaService.saveMeta(name, type)) {
            return RestResponse.ok();
        }
        return RestResponse.fail();
    }

    /**
     * 根据id修改分类
     *
     * @param id   属性id
     * @param name 新属性名
     * @param type 新属性类型
     * @return 结果
     */
    @PostMapping("update/{id}")
    public RestResponse updateMeta(@PathVariable Integer id, @RequestParam String name, @RequestParam String type) {
        if (metaService.updateMeta(id, name, type)) {
            return RestResponse.ok();
        }
        return RestResponse.fail();
    }
}
