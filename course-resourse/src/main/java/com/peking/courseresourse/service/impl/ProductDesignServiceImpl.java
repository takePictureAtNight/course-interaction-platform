package com.peking.courseresourse.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import utils.PageUtils;
import utils.Query;

import com.peking.courseresourse.dao.ProductDesignDao;
import com.peking.courseresourse.entity.ProductDesignEntity;
import com.peking.courseresourse.service.ProductDesignService;


@Service("productDesignService")
public class ProductDesignServiceImpl extends ServiceImpl<ProductDesignDao, ProductDesignEntity> implements ProductDesignService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProductDesignEntity> page = this.page(
                new Query<ProductDesignEntity>().getPage(params),
                new QueryWrapper<ProductDesignEntity>()
        );

        return new PageUtils(page);
    }

}