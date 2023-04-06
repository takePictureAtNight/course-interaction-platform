package com.peking.courseresourse.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peking.courseresourse.dao.NoticeDao;
import com.peking.courseresourse.entity.NoticeEntity;
import com.peking.courseresourse.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("notificationsService")
public class NoticeServiceImpl extends ServiceImpl<NoticeDao, NoticeEntity> implements NoticeService {
    @Autowired
    private NoticeDao noticeDao;

    @Override
    public List<NoticeEntity> selectAll() {
        return noticeDao.selectAll();
    }

    @Override
    public NoticeEntity selectById(int id) {
        return noticeDao.selectById(id);
    }

    @Override
    public int deleteById(int id) {
        return noticeDao.deleteById(id);
    }

    @Override
    public int insertNotice(NoticeEntity notice) {
        return noticeDao.insertNotice(notice);
    }

    @Override
    public int updateNotice(NoticeEntity notice) {
        return noticeDao.updateNotice(notice);
    }

}