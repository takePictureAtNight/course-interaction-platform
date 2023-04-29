package com.peking.courseresourse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.peking.courseresourse.vo.LoginVo;
import com.peking.courseresourse.vo.RegisterVo;
import utils.PageUtils;
import com.peking.courseresourse.entity.UserEntity;
import utils.R;

import java.util.Map;

/**
 * 
 *
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-04-23 12:46:02
 */
public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    R login(LoginVo loginVo);

    PageUtils queryCheckPage(Map<String, Object> params);

    R updateStatus(Integer status,Integer id);

    R register(RegisterVo registerVo);

    PageUtils queryCasePage(Map<String, Object> params);

    PageUtils queryElectronicPage(Map<String, Object> params);

    PageUtils queryDesignPage(Map<String, Object> params);

    PageUtils queryReportPage(Map<String, Object> params);

    PageUtils queryWeeklyPage(Map<String, Object> params);

    PageUtils querySuperPage(Map<String, Object> params);
}

