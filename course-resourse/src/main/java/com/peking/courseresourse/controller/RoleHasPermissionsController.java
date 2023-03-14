package com.peking.courseresourse.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.peking.courseresourse.entity.RoleHasPermissionsEntity;
import com.peking.courseresourse.service.RoleHasPermissionsService;
import utils.PageUtils;
import utils.R;



/**
 * 角色关联权限
 *
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-03-14 20:50:46
 */
@RestController
@RequestMapping("courseresourse/rolehaspermissions")
public class RoleHasPermissionsController {
    @Autowired
    private RoleHasPermissionsService roleHasPermissionsService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = roleHasPermissionsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		RoleHasPermissionsEntity roleHasPermissions = roleHasPermissionsService.getById(id);

        return R.ok().put("roleHasPermissions", roleHasPermissions);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody RoleHasPermissionsEntity roleHasPermissions){
		roleHasPermissionsService.save(roleHasPermissions);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public R update(@RequestBody RoleHasPermissionsEntity roleHasPermissions){
		roleHasPermissionsService.updateById(roleHasPermissions);

        return R.ok();
    }

    /**
     * 删除
     */
    @GetMapping("/delete")
    public R delete( Integer id){
		roleHasPermissionsService.removeById(id);
        return R.ok();
    }

}
