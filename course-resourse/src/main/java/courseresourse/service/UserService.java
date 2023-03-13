package courseresourse.service;

import com.baomidou.mybatisplus.extension.service.IService;

import courseresourse.entity.UserEntity;
import utils.PageUtils;

import java.util.Map;

/**
 * 
 *
 * @author yy
 * @email sunlightcs@gmail.com
 * @date 2023-03-13 19:46:31
 */
public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

