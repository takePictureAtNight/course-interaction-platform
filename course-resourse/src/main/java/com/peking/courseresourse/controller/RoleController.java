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
import com.peking.courseresourse.entity.RoleEntity;
import com.peking.courseresourse.service.RoleService;
import utils.PageUtils;
import utils.R;



/**
 * 系统角色
 *
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-03-14 20:50:46
 */
@RestController
@RequestMapping("courseresourse/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = roleService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		RoleEntity role = roleService.getById(id);

        return R.ok().put("role", role);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody RoleEntity role){
		roleService.save(role);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public R update(@RequestBody RoleEntity role){
		roleService.updateById(role);

        return R.ok();
    }

    /**
     * 删除
     */
    @GetMapping("/delete")
    public R delete( Integer id){
		roleService.removeById(id);
        return R.ok();
    }

}
