package com.peking.courseresourse.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.peking.courseresourse.entity.NoticeEntity;
import com.peking.courseresourse.service.NoticeService;
import utils.PageUtils;
import utils.R;



/**
 * 通知公告表
 *
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-04-11 14:19:01
 */
@RestController
@RequestMapping("courseresourse/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = noticeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		NoticeEntity notice = noticeService.getById(id);

        return R.ok().put("notice", notice);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody NoticeEntity notice){
		noticeService.save(notice);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public R update(@RequestBody NoticeEntity notice){
		noticeService.updateById(notice);

        return R.ok();
    }

    /**
     * 删除
     */
    @GetMapping("/delete")
    public R delete( Integer id){
		noticeService.removeById(id);
        return R.ok();
    }

}
