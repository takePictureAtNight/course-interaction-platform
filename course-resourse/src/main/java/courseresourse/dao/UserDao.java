package courseresourse.dao;

import courseresourse.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author yy
 * @email sunlightcs@gmail.com
 * @date 2023-03-13 19:46:31
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
	
}
