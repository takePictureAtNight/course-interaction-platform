package com.peking.courseresourse.controller;

import java.util.Arrays;
import java.util.Map;

import annotation.SysLog;
import dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.peking.courseresourse.entity.UserEntity;
import com.peking.courseresourse.service.UserService;
import utils.PageUtils;
import utils.R;
import utils.UserHolder;


/**
 * 仅该表中的用户可登录系统
 *
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-03-14 20:50:46
 */
@Slf4j
@RestController
@RequestMapping("courseresourse/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = userService.queryPage(params);

        return R.ok().put("page", page);
    }

    @PostMapping("/login")
    public R login(@RequestBody UserEntity userEntity) {
        log.info("login线程:{}", Thread.currentThread().getId());
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userEntity, userDTO);
        log.info("userDTO" + userDTO);
        UserHolder.saveUser(userDTO);
        return R.ok("登录成功");
    }

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        UserEntity user = userService.getById(id);

        return R.ok().put("user", user);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody UserEntity user) {
        userService.save(user);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public R update(@RequestBody UserEntity user) {
        userService.updateById(user);

        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除用户")
    @GetMapping("/delete")
    public R delete(Integer id) {
        userService.removeById(id);
        return R.ok();
    }

}
