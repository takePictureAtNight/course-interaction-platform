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
import com.peking.courseresourse.entity.ProductDesignEntity;
import com.peking.courseresourse.service.ProductDesignService;
import utils.PageUtils;
import utils.R;



/**
 * 
 *
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-03-14 20:49:11
 */
@RestController
@RequestMapping("courseresourse/productdesign")
public class ProductDesignController {
    @Autowired
    private ProductDesignService productDesignService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = productDesignService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		ProductDesignEntity productDesign = productDesignService.getById(id);

        return R.ok().put("productDesign", productDesign);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody ProductDesignEntity productDesign){
		productDesignService.save(productDesign);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public R update(@RequestBody ProductDesignEntity productDesign){
		productDesignService.updateById(productDesign);

        return R.ok();
    }

    /**
     * 删除
     */
    @GetMapping("/delete")
    public R delete( Integer id){
		productDesignService.removeById(id);
        return R.ok();
    }

}
