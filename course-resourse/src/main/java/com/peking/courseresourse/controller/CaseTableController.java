package com.peking.courseresourse.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import annotation.SysLog;
import cn.hutool.core.io.FileUtil;
import dto.CaseTableDTO;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.peking.courseresourse.entity.CaseTableEntity;
import com.peking.courseresourse.service.CaseTableService;
import org.springframework.web.multipart.MultipartFile;
import utils.PageUtils;
import utils.R;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;


/**
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-03-14 20:49:11
 */
@RestController
@RequestMapping("courseresourse/casetable")
public class CaseTableController {
    @Autowired
    private CaseTableService caseTableService;

    /**
     * 列表(用户所有)
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = caseTableService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        CaseTableEntity caseTable = caseTableService.getById(id);

        return R.ok().put("caseTable", caseTable);
    }


    /**
     *
     * @param id   上传文件的id
     * @param response
     * @return
     * @throws IOException
     */
    @GetMapping("/download")
    public R download(Integer id, HttpServletResponse response) throws IOException {
        CaseTableEntity caseTable = caseTableService.getById(id);
        String resourceUrl = caseTable.getResourceUrl();
        response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode(caseTable.getFileName(),"UTF-8"));
        byte[] bytes = FileUtil.readBytes(resourceUrl);
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
        return R.ok("下载成功");
    }

    /**
     *
     * @param caseTableDTO 前端传过来的dto
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public R upload(CaseTableDTO caseTableDTO) throws IOException {
        MultipartFile[] files = caseTableDTO.getFiles();
        if(files == null || files.length == 0){
            return R.error("上传文件不能为空");
        }
        for (MultipartFile file : files) {
            String originalFilename = file.getOriginalFilename();
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            System.out.println(extension);
            if(!".doc".equals("."+extension) && !".docx".equals("."+extension)){
                return R.error("文件格式错误无法上传");
            }
            String newFilename = new SimpleDateFormat("yyyMMddHHmmss").format(new Date()) + UUID.randomUUID() +"."+extension;
            String path = ResourceUtils.getURL("classpath:").getPath() + "static/files";
            System.out.println(path);
            String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String dateDirPath = path + "/" + format;
            File datePath = new File(dateDirPath);
            if (!datePath.exists()) {
                datePath.mkdirs();
            }
            try {
                file.transferTo(new File(datePath, newFilename));
                CaseTableEntity caseTableEntity = new CaseTableEntity();
                BeanUtils.copyProperties(caseTableDTO,caseTableEntity);
                caseTableEntity.setResourceUrl(path+"/"+format+"/"+newFilename);
                caseTableEntity.setFileName(originalFilename);
                caseTableService.save(caseTableEntity);
            } catch (Exception e) {
                R.error("上传失败");
            }
        }
        return R.ok("上传成功");
    }

        /**
         * 保存
         */
        @PostMapping("/save")
        public R save (@RequestBody CaseTableEntity caseTable){
            caseTableService.save(caseTable);

            return R.ok();
        }

        /**
         * 修改
         */
        @PostMapping("/update")
        public R update (@RequestBody CaseTableEntity caseTable){
            caseTableService.updateById(caseTable);

            return R.ok();
        }

        /**
         * 删除
         */
        @SysLog("删除")
        @GetMapping("/delete")
        public R delete (Integer id){
            caseTableService.removeById(id);
            return R.ok();
        }

    }
