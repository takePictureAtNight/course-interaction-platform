package com.peking.courseresourse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.peking.courseresourse.entity.NoticeEntity;

import java.util.List;

/**
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-03-14 20:52:08
 */
public interface NoticeService extends IService<NoticeEntity> {

    /**
     * 查询所有通知
     *
     * @return
     */
    List<NoticeEntity> selectAll();

    /**
     * 基于id查询通知
     *
     * @param id
     * @return
     */
    NoticeEntity selectById(int id);

    /**
     * 基于id删除通知
     *
     * @param id
     * @return
     */
    int deleteById(int id);

    /**
     * 上传通知
     *
     * @param notice
     * @return
     */
    int insertNotice(NoticeEntity notice);

    /**
     * 更新通知
     *
     * @param notice
     * @return
     */
    int updateNotice(NoticeEntity notice);

}

