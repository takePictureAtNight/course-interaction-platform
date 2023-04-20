package com.peking.courseresourse.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.peking.courseresourse.entity.ElectronicJournalEntity;
import com.peking.courseresourse.service.ElectronicJournalService;
import utils.PageUtils;
import utils.R;


/**
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-03-14 20:49:11
 */
@RestController
@RequestMapping("courseresourse/electronicjournal")
public class ElectronicJournalController {
    @Autowired
    private ElectronicJournalService electronicJournalService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = electronicJournalService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        ElectronicJournalEntity electronicJournal = electronicJournalService.getById(id);

        return R.ok().put("electronicJournal", electronicJournal);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody ElectronicJournalEntity electronicJournal) {
        electronicJournalService.save(electronicJournal);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/updateStatus")
    public R update(@RequestParam Integer id, @RequestParam String status, @RequestParam String returnReason) {
        //修改电子期刊的审核状态
        return electronicJournalService.updateStatus(id, status, returnReason);

    }

    /**
     * 删除
     */
    @GetMapping("/delete")
    public R delete(Integer id) {
        electronicJournalService.removeById(id);
        return R.ok();
    }

}
