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
import com.peking.courseresourse.entity.UserHasRolesEntity;
import com.peking.courseresourse.service.UserHasRolesService;
import utils.PageUtils;
import utils.R;



/**
 * 用户权限关联表
 *
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-03-14 20:50:46
 */
@RestController
@RequestMapping("courseresourse/userhasroles")
public class UserHasRolesController {
    @Autowired
    private UserHasRolesService userHasRolesService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userHasRolesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		UserHasRolesEntity userHasRoles = userHasRolesService.getById(id);

        return R.ok().put("userHasRoles", userHasRoles);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody UserHasRolesEntity userHasRoles){
		userHasRolesService.save(userHasRoles);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public R update(@RequestBody UserHasRolesEntity userHasRoles){
		userHasRolesService.updateById(userHasRoles);

        return R.ok();
    }

    /**
     * 删除
     */
    @GetMapping("/delete")
    public R delete( Integer id){
		userHasRolesService.removeById(id);
        return R.ok();
    }

}
