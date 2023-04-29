package com.peking.courseresourse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.peking.courseresourse.entity.CaseTableEntity;
import com.peking.courseresourse.entity.ElectronicJournalEntity;
import com.peking.courseresourse.vo.UpdateStatusVo;
import dto.UserDTO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import utils.PageUtils;
import utils.Query;

import com.peking.courseresourse.dao.ProductDesignDao;
import com.peking.courseresourse.entity.ProductDesignEntity;
import com.peking.courseresourse.service.ProductDesignService;
import utils.R;
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
                .eq(params.get("productCategory") != null, ProductDesignEntity::getProductCategory, params.get("productCategory"))
                .eq(ProductDesignEntity::getStatus,1);
        IPage<ProductDesignEntity> page = this.page(
                new Query<ProductDesignEntity>().getPage(params),
                lambdaQueryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public R updateStatus(UpdateStatusVo updateStatusVo) {
        UpdateWrapper<ProductDesignEntity> wrapper = new UpdateWrapper<ProductDesignEntity>().eq("id", updateStatusVo.getId());
        Integer status = updateStatusVo.getStatus();
        String returnReason = updateStatusVo.getReturnReason();
        wrapper.set(status != null,"status",status);
        wrapper.set(StringUtils.isNotBlank(returnReason),"return_reason",returnReason);
        this.update(wrapper);
        return R.ok();
    }

    @Override
    public R saveAll(ProductDesignEntity productDesign) {
        Integer id = UserHolder.getUser().getId();
        //保存后状态为为审核0
        productDesign.setStatus(0);
        //保存用户id 创建人
        productDesign.setCreateBy(id);
        this.save(productDesign);
        return R.ok();
    }

}