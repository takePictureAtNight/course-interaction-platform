package com.peking.courseresourse.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import annotation.SysLog;
import cn.hutool.core.util.IdUtil;
import com.google.code.kaptcha.Producer;
import com.peking.courseresourse.vo.LoginVo;
import com.peking.courseresourse.vo.RegisterVo;
import dto.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.peking.courseresourse.entity.UserEntity;
import com.peking.courseresourse.service.UserService;
import utils.Base64;
import utils.PageUtils;
import utils.R;
import utils.UserHolder;

import javax.imageio.ImageIO;


/**
 * 
 *
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-04-23 12:46:02
 */


@RestController
@RequestMapping("courseresourse/user")
@Api(value = "用户接口", tags = {"用户接口"})
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private Producer captchaProducerMath;
    @Autowired
    private StringRedisTemplate template;
    /**
     * 列表
     */
    @ApiOperation("管理员用户列表")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userService.queryPage(params);
        return R.ok().put("page", page);
    }
    @ApiOperation("超级管理员用户列表")
    @GetMapping("/super/list")
    public R superList(@RequestParam Map<String, Object> params){
        PageUtils page = userService.querySuperPage(params);
        return R.ok().put("page", page);
    }
    @SysLog("用户登录")
    @ApiOperation("用户登录")
    @PostMapping("/login")
    public R login(@RequestBody LoginVo loginVo){
        System.out.println("---"+loginVo);
        return userService.login(loginVo);
    }
    @SysLog("用户注册")
    @ApiOperation("用户注册")
    @PostMapping("/register")
    public R register(@RequestBody RegisterVo registerVo){
        System.out.println("***"+registerVo);
        return userService.register(registerVo);
    }
    //超级管理员审核用户列表
    @ApiOperation("超级管理员审核用户列表")
    @GetMapping("/check/list")
    public R checkList(@RequestParam Map<String, Object> params){
        PageUtils page = userService.queryCheckPage(params);
        return R.ok().put("page", page);
    }
    //管理员审核课程资源列表
    @ApiOperation("管理员审核案例库列表")
    @GetMapping("/check/case/list")
    public R checkCaseList(@RequestParam Map<String, Object> params){
        PageUtils page = userService.queryCasePage(params);
        return R.ok().put("page", page);
    }
    @ApiOperation("管理员审核电子期刊列表")
    @GetMapping("/check/electronic/list")
    public R checkElectronicList(@RequestParam Map<String, Object> params){
        PageUtils page = userService.queryElectronicPage(params);
        return R.ok().put("page", page);
    }
    @ApiOperation("管理员审核产品设计列表")
    @GetMapping("/check/design/list")
    public R checkDesignList(@RequestParam Map<String, Object> params){
        PageUtils page = userService.queryDesignPage(params);
        return R.ok().put("page", page);
    }
    @ApiOperation("管理员审核项目报告列表")
    @GetMapping("/check/report/list")
    public R checkReportList(@RequestParam Map<String, Object> params){
        PageUtils page = userService.queryReportPage(params);
        return R.ok().put("page", page);
    }
    @ApiOperation("管理员审核周报记录列表")
    @GetMapping("/check/weekly/list")
    public R checkWeeklyList(@RequestParam Map<String, Object> params){
        PageUtils page = userService.queryWeeklyPage(params);
        return R.ok().put("page", page);
    }
    //超级管理员工修改状态(审核)
    @ApiOperation("超级管理员工修改状态(审核)")
    @PostMapping("/updateStatus")
    public R updateStatus(@RequestParam("id")Integer id,@RequestParam("status") Integer status){
        return userService.updateStatus(status,id);
    }
    /**
     * 信息
     */
    @ApiOperation("根据id获取用户信息")
    @GetMapping("/info/{userId}")
    public R info(@PathVariable("userId") Integer userId){
		UserEntity user = userService.getById(userId);

        return R.ok().put("user", user);
    }
    //获取登录用户信息
    @ApiOperation("获取登录用户信息")
    @GetMapping("/me")
    public R me(){
        UserDTO user = UserHolder.getUser();
        return R.ok().put("user",user);
    }
    //获取验证码
    @ApiOperation("获取验证码")
    @GetMapping("/getCode")
    public R getCode(){
        String uuid = IdUtil.simpleUUID();
        String capText = captchaProducerMath.createText();
        System.out.println(capText);
        String capStr = capText.substring(0, capText.lastIndexOf("@"));
        String code = capText.substring(capText.lastIndexOf("@") + 1);
        BufferedImage bi = captchaProducerMath.createImage(capStr);
        template.opsForValue().set("login:code:"+uuid,code,2, TimeUnit.MINUTES);
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            ImageIO.write(bi, "jpg", os);
        } catch (IOException e) {
            R.error("获取失败");
        }
        return  R.ok().put("uuid", uuid).put("img", Base64.encode(os.toByteArray()));
    }
    /**
     * 保存
     */
    @ApiOperation("新增用户")
    @PostMapping("/save")
    public R save(@RequestBody UserEntity user){
		userService.save(user);
        return R.ok();
    }

    /**
     * 修改
     */
    @ApiOperation("修改用户")
    @PostMapping("/update")
    public R update(@RequestBody UserEntity user){
		userService.updateById(user);

        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除用户")
    @ApiOperation("根据id删除")
    @GetMapping("/delete")
    public R delete( Integer userId){
		userService.removeById(userId);
        return R.ok();
    }

}
