package com.peking.courseresourse.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.peking.courseresourse.entity.NoticeEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-03-14 20:52:08
 */
@Mapper
public interface NoticeDao extends BaseMapper<NoticeEntity> {
    //查询所有
    @Select("select * from notice")
    List<NoticeEntity> selectAll();

    //基于id查询
    @Select("select * from notice where id=#{id}")
    NoticeEntity selectById(int id);

    //基于id删除
    @Delete("delete from notice where id=#{id}")
    int deleteById(int id);

    //创建通知
    int insertNotice(NoticeEntity notice);

    //更新通知
    int updateNotice(NoticeEntity notice);
}
