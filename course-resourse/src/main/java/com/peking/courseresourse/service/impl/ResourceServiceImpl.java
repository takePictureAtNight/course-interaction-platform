package com.peking.courseresourse.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peking.courseresourse.dao.CaseTableDao;
import com.peking.courseresourse.entity.*;
import com.peking.courseresourse.service.*;
import com.peking.courseresourse.vo.Echarts;
import com.peking.courseresourse.vo.ReturnResource;
import exception.RException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import utils.R;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private CaseTableService caseTableService;
    @Autowired
    private ElectronicJournalService electronicJournalService;
    @Autowired
    private ProjectReportService projectReportService;
    @Autowired
    private ProductDesignService productDesignService;
    @Autowired
    private WeeklyReportreCordsService weeklyReportreCordsService;
    @Autowired
    private FileService fileService;

    @Override
    public R me(Integer id) {
        List<ReturnResource> list = new ArrayList<>();
        List<CaseTableEntity> caseList = caseTableService.list(new QueryWrapper<CaseTableEntity>().eq("create_by", id));
        List<ElectronicJournalEntity> electronicJournalEntityList = electronicJournalService.list(new QueryWrapper<ElectronicJournalEntity>().eq("create_by", id));
        List<ProjectReportEntity> projectReportEntityList = projectReportService.list(new QueryWrapper<ProjectReportEntity>().eq("create_by", id));
        List<ProductDesignEntity> productDesignEntityList = productDesignService.list(new QueryWrapper<ProductDesignEntity>().eq("create_by", id));
        List<WeeklyReportreCordsEntity> reportreCordsEntityList = weeklyReportreCordsService.list(new QueryWrapper<WeeklyReportreCordsEntity>().eq("create_by", id));
        for (CaseTableEntity caseTable : caseList) {
            list.add(BeanUtil.copyProperties(caseTable, ReturnResource.class));
        }
        for (ElectronicJournalEntity journalEntity : electronicJournalEntityList) {
            list.add(BeanUtil.copyProperties(journalEntity, ReturnResource.class));
        }
        for (ProductDesignEntity productDesignEntity : productDesignEntityList) {
            list.add(BeanUtil.copyProperties(productDesignEntity, ReturnResource.class));
        }
        for (ProjectReportEntity projectReportEntity : projectReportEntityList) {
            list.add(BeanUtil.copyProperties(projectReportEntity, ReturnResource.class));
        }
        for (WeeklyReportreCordsEntity reportreCordsEntity : reportreCordsEntityList) {
            list.add(BeanUtil.copyProperties(reportreCordsEntity, ReturnResource.class));
        }
        return R.ok().put("list", list);
    }

    @Override
    public R getPie() {
        long caseCount = caseTableService.count(new QueryWrapper<CaseTableEntity>().eq("status", 1));
        long eleCount = electronicJournalService.count(new QueryWrapper<ElectronicJournalEntity>().eq("status", 1));
        long productCount = productDesignService.count(new QueryWrapper<ProductDesignEntity>().eq("status", 1));
        long reportCount = projectReportService.count(new QueryWrapper<ProjectReportEntity>().eq("status", 1));
        long weeklyCount = weeklyReportreCordsService.count(new QueryWrapper<WeeklyReportreCordsEntity>().eq("status", 1));
        List<Echarts> list = new ArrayList<>();
        list.add(new Echarts("案例库", caseCount));
        list.add(new Echarts("电子期刊", eleCount));
        list.add(new Echarts("产品设计", productCount));
        list.add(new Echarts("项目报告", reportCount));
        list.add(new Echarts("周报记录", weeklyCount));
        return R.ok().put("list", list);
    }

    @Override
    public R getComPie() {
        //承泽园，畅春园，中关园，燕北园，燕园街道，朗润园，蔚秀园，燕东园
        long c1 = caseTableService.count(new QueryWrapper<CaseTableEntity>().eq("internship_community", "承泽园").eq("status", 1));
        long c2 = caseTableService.count(new QueryWrapper<CaseTableEntity>().eq("internship_community", "畅春园").eq("status", 1));
        long c3 = caseTableService.count(new QueryWrapper<CaseTableEntity>().eq("internship_community", "中关园").eq("status", 1));
        long c4 = caseTableService.count(new QueryWrapper<CaseTableEntity>().eq("internship_community", "燕北园").eq("status", 1));
        long c5 = caseTableService.count(new QueryWrapper<CaseTableEntity>().eq("internship_community", "燕园街道").eq("status", 1));
        long c6 = caseTableService.count(new QueryWrapper<CaseTableEntity>().eq("internship_community", "朗润园").eq("status", 1));
        long c7 = caseTableService.count(new QueryWrapper<CaseTableEntity>().eq("internship_community", "蔚秀园").eq("status", 1));
        long c8 = caseTableService.count(new QueryWrapper<CaseTableEntity>().eq("internship_community", "燕东园").eq("status", 1));
        long e1 = electronicJournalService.count(new QueryWrapper<ElectronicJournalEntity>().eq("internship_community", "承泽园").eq("status", 1));
        long e2 = electronicJournalService.count(new QueryWrapper<ElectronicJournalEntity>().eq("internship_community", "畅春园").eq("status", 1));
        long e3 = electronicJournalService.count(new QueryWrapper<ElectronicJournalEntity>().eq("internship_community", "中关园").eq("status", 1));
        long e4 = electronicJournalService.count(new QueryWrapper<ElectronicJournalEntity>().eq("internship_community", "燕北园").eq("status", 1));
        long e5 = electronicJournalService.count(new QueryWrapper<ElectronicJournalEntity>().eq("internship_community", "燕园街道").eq("status", 1));
        long e6 = electronicJournalService.count(new QueryWrapper<ElectronicJournalEntity>().eq("internship_community", "朗润园").eq("status", 1));
        long e7 = electronicJournalService.count(new QueryWrapper<ElectronicJournalEntity>().eq("internship_community", "蔚秀园").eq("status", 1));
        long e8 = electronicJournalService.count(new QueryWrapper<ElectronicJournalEntity>().eq("internship_community", "燕东园").eq("status", 1));
        long n1 = weeklyReportreCordsService.count(new QueryWrapper<WeeklyReportreCordsEntity>().eq("internship_community", "承泽园").eq("status", 1));
        long n2 = weeklyReportreCordsService.count(new QueryWrapper<WeeklyReportreCordsEntity>().eq("internship_community", "畅春园").eq("status", 1));
        long n3 = weeklyReportreCordsService.count(new QueryWrapper<WeeklyReportreCordsEntity>().eq("internship_community", "中关园").eq("status", 1));
        long n4 = weeklyReportreCordsService.count(new QueryWrapper<WeeklyReportreCordsEntity>().eq("internship_community", "燕北园").eq("status", 1));
        long n5 = weeklyReportreCordsService.count(new QueryWrapper<WeeklyReportreCordsEntity>().eq("internship_community", "燕园街道").eq("status", 1));
        long n6 = weeklyReportreCordsService.count(new QueryWrapper<WeeklyReportreCordsEntity>().eq("internship_community", "朗润园").eq("status", 1));
        long n7 = weeklyReportreCordsService.count(new QueryWrapper<WeeklyReportreCordsEntity>().eq("internship_community", "蔚秀园").eq("status", 1));
        long n8 = weeklyReportreCordsService.count(new QueryWrapper<WeeklyReportreCordsEntity>().eq("internship_community", "燕东园").eq("status", 1));
        List<Echarts> list = new ArrayList<>();
        list.add(new Echarts("承泽园", c1 + e1 + n1));
        list.add(new Echarts("畅春园", c2 + e2 + n2));
        list.add(new Echarts("中关园", c3 + e3 + n3));
        list.add(new Echarts("燕北园", c4 + e4 + n4));
        list.add(new Echarts("燕园街道", c5 + e5 + n5));
        list.add(new Echarts("朗润园", c6 + e6 + n6));
        list.add(new Echarts("蔚秀园", c7 + e7 + n7));
        list.add(new Echarts("燕东园", c8 + e8 + n8));
        return R.ok().put("list", list);
    }

    @Override
    public void preview(Integer id, Integer type, HttpServletResponse response) {
        String resourceUrl = "";
        String fileName = "";
        switch (type) {
            case 1:
                CaseTableEntity caseTable = caseTableService.getById(id);
                resourceUrl = caseTable.getResourceUrl();
                fileName = caseTable.getFileName();
                break;
            case 2:
                ElectronicJournalEntity electronicJournal = electronicJournalService.getById(id);
                resourceUrl = electronicJournal.getResourceUrl();
                fileName = electronicJournal.getFileName();
                break;
            case 3:
                ProductDesignEntity productDesign = productDesignService.getById(id);
                resourceUrl = productDesign.getResourceUrl();
                fileName = productDesign.getFileName();
                break;
            case 4:
                ProjectReportEntity projectReport = projectReportService.getById(id);
                resourceUrl = projectReport.getResourceUrl();
                fileName = projectReport.getFileName();
                break;
            case 5:
                WeeklyReportreCordsEntity weeklyReportreCords = weeklyReportreCordsService.getById(id);
                resourceUrl = weeklyReportreCords.getResourceUrl();
                fileName = weeklyReportreCords.getFileName();
                break;
            default:
                throw new RException("类型不存在");
        }
        String path;
        try {
            path = ResourceUtils.getURL("classpath:").getPath() + "static" + resourceUrl;
        } catch (FileNotFoundException e) {
            throw new RException("文件找不到");
        }
        try {
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RException("编码失败");
        }
        byte[] bytes = FileUtil.readBytes(path);
        OutputStream outputStream;
        try {
            outputStream = response.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            throw new RException("失败");
        }


    }
}
