package com.peking.courseresourse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import dto.UserDTO;
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
import utils.UserHolder;


@Service("productDesignService")
public class ProductDesignServiceImpl extends ServiceImpl<ProductDesignDao, ProductDesignEntity> implements ProductDesignService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        UserDTO user = UserHolder.getUser();
        //产品设计搜索条件
        LambdaQueryWrapper<ProductDesignEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper//.eq(ProductDesignEntity::getCreateBy,user.getId())
                .eq(params.get("designName") != null, ProductDesignEntity::getDesignName, params.get("designName"))
                .eq(params.get("keywords") != null, ProductDesignEntity::getKeywords, params.get("keywords"))
                .like(params.get("serviceTarget") != null, ProductDesignEntity::getServiceTarget, params.get("serviceTarget"))
                .eq(params.get("productCategory") != null, ProductDesignEntity::getProductCategory, params.get("productCategory"));
        IPage<ProductDesignEntity> page = this.page(
                new Query<ProductDesignEntity>().getPage(params),
                lambdaQueryWrapper
        );

        return new PageUtils(page);
    }

}