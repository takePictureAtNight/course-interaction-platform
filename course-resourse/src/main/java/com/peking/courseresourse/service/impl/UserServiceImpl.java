package com.peking.courseresourse.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.peking.courseresourse.entity.*;
import com.peking.courseresourse.service.*;
import com.peking.courseresourse.vo.CheckVo;
import com.peking.courseresourse.vo.LoginVo;
import com.peking.courseresourse.vo.RegisterVo;
import dto.UserDTO;
import exception.RException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import utils.PageUtils;
import utils.Query;

import com.peking.courseresourse.dao.UserDao;
import utils.R;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private CaseTableService caseTableService;
    @Autowired
    private ElectronicJournalService electronicJournalService;
    @Autowired
    private ProjectReportService projectReportService;
    @Autowired
    private ProductDesignService productDesignService;
    @Autowired
    private WeeklyReportreCordsService weeklyReportreCordsService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                new QueryWrapper<UserEntity>().in("role_id",3,4).eq("status",1)
        );

        return new PageUtils(page);
    }

    @Override
    public R login(LoginVo loginVo) {
        System.out.println(loginVo);
        String cap = loginVo.getCode();
        if(cap == null){
            throw new RException("验证码不存在");
        }
        String code = redisTemplate.opsForValue().get("login:code:"+loginVo.getUuid());
        if(code == null){
            throw new RException("验证码不存在");
        }
        if(!code.equals(cap)) {
            throw new RException("验证码错误");
        }
        //UserEntity user = this.getOne(new QueryWrapper<UserEntity>().eq("username", loginVo.getUsername()).eq("password", loginVo.getPassword()));
       UserEntity user = this.baseMapper.loginByRoleId(loginVo.getRoleId(),loginVo.getUsername(),loginVo.getPassword());
        if(user == null){
            throw new RException("用户名或密码错误");
        }
        if(user.getStatus() == 0){
            throw new RException("超级管理员未审核,请联系超级管理员");
        }
        if(user.getStatus() == 2){
            throw new RException("审核未通过,请联系超级管理员");
        }
        String token = IdUtil.simpleUUID();
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        userDTO.setId(user.getUserId());
        redisTemplate.opsForValue().set("login:token:"+token, JSON.toJSONString(userDTO));
        //1天有效期
        redisTemplate.expire("login:token:"+token,24*60, TimeUnit.MINUTES);
        return R.ok("登录成功").put("token",token);

    }
    // 0 为未审核 1 审核成功 2 审核失败
    @Override
    public PageUtils queryCheckPage(Map<String, Object> params) {
        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                new QueryWrapper<UserEntity>().eq("status",0)
        );
        List<CheckVo> checkList = page.getRecords().stream().map(user->BeanUtil.copyProperties(user, CheckVo.class)).collect(Collectors.toList());
        PageUtils pageUtils = new PageUtils(page);
        pageUtils.setList(checkList);
        return pageUtils;
    }
    private String getRoleNameById(Integer id)
    {
       return this.baseMapper.getRoleNameById(id);
    }

    @Override
    public R updateStatus(Integer status,Integer id) {
        this.update(new UpdateWrapper<UserEntity>().set("status",status).eq("user_id",id));
        return R.ok("修改成功");
    }
    @Transactional
    @Override
    public R register(RegisterVo registerVo) {
        String username = registerVo.getUsername();
        UserEntity user = this.getOne(new QueryWrapper<UserEntity>().eq("username", username));
        if(user != null){
            return R.error("名字重复换一个试试");
        }
        UserEntity registerUser = new UserEntity();
        BeanUtils.copyProperties(registerVo,registerUser);
        registerUser.setStatus(0);//代表未审核
        registerUser.setUserType("01");//注册用户 而非系统用户
        registerUser.setRoleId(registerVo.getRoleId());//设置角色id
        this.save(registerUser);
        return R.ok("注册成功,等待超级管理员审核后才能登录");
    }

//    @Autowired
//    private ProjectReportService projectReportService;
//    @Autowired
//    private ProductDesignService productDesignService;
//    @Autowired
//    private WeeklyReportreCordsService weeklyReportreCordsService;
    @Override
    public PageUtils queryCasePage(Map<String, Object> params) {
        IPage<CaseTableEntity> page = caseTableService.page(
                new Query<CaseTableEntity>().getPage(params),
                new QueryWrapper<CaseTableEntity>().eq("status", 0)
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryElectronicPage(Map<String, Object> params) {
        IPage<ElectronicJournalEntity> page = electronicJournalService.page(
                new Query<ElectronicJournalEntity>().getPage(params),
                new QueryWrapper<ElectronicJournalEntity>().eq("status", 0)
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryDesignPage(Map<String, Object> params) {
        IPage<ProductDesignEntity> page = productDesignService.page(
                new Query<ProductDesignEntity>().getPage(params),
                new QueryWrapper<ProductDesignEntity>().eq("status", 0)
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryReportPage(Map<String, Object> params) {
        IPage<ProjectReportEntity> page = projectReportService.page(
                new Query<ProjectReportEntity>().getPage(params),
                new QueryWrapper<ProjectReportEntity>().eq("status", 0)
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryWeeklyPage(Map<String, Object> params) {
        IPage<WeeklyReportreCordsEntity> page = weeklyReportreCordsService.page(
                new Query<WeeklyReportreCordsEntity>().getPage(params),
                new QueryWrapper<WeeklyReportreCordsEntity>().eq("status", 0)
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils querySuperPage(Map<String, Object> params) {
        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                new QueryWrapper<UserEntity>().in("role_id",2,3,4).eq("status",1)
        );

        return new PageUtils(page);
    }

}