package com.peking.courseresourse.controller;

import com.peking.courseresourse.entity.NoticeEntity;
import com.peking.courseresourse.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.R;

import java.util.List;


/**
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-03-14 20:52:08
 */
@RestController
@RequestMapping("/notice/")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    /**
     * 查询所有
     */
    @GetMapping("/list")
    public R list() {
        List<NoticeEntity> noticeList = noticeService.selectAll();
        return R.ok().put("noticeList", noticeList);
    }

    /**
     * 基于id查询通知
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        NoticeEntity notice = noticeService.selectById(id);
        return R.ok().put("notice", notice);
    }

    /**
     * 上传通知
     */
    @PostMapping("/save")
    public R save(@RequestBody NoticeEntity notice) {
        noticeService.insertNotice(notice);
        return R.ok();
    }

    /**
     * 更新通知
     */
    @PostMapping("/update")
    public R update(@RequestBody NoticeEntity notice) {
        noticeService.updateNotice(notice);
        return R.ok();
    }

    /**
     * 删除通知
     */
    @GetMapping("/delete")
    public R delete(Integer id) {
        noticeService.deleteById(id);
        return R.ok();
    }

}
