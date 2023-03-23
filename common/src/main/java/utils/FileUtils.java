package utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import dto.UploadDTO;
import exception.RException;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class FileUtils {
    //上传工具类
    public static List<UploadDTO> upload(MultipartFile[] files){
        String url = "http://localhost:8082/file/upload";
        String json = FileUtils.doPostFormData(url, "files", files);
        JSONObject jsonObject = JSON.parseObject(json);
        System.out.println("---"+jsonObject);
        if( jsonObject.getInteger("code")== 500){
            throw new RException(jsonObject.getString("msg"));
        }
        List<UploadDTO> myList = JSON.parseArray(jsonObject.getString("data"), UploadDTO.class);
        return myList;
    }
    /**
     * 以post方式调用第三方接口,以form-data 形式  发送 MultipartFile 文件数据
     *
     * @param url  post请求url
     * @param fileParamName 文件参数名称
     * @param multipartFile  文件
     * @param
     * @return
     */
    public static String doPostFormData(String url, String fileParamName, MultipartFile[] multipartFile) {
        // 创建Http实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建HttpPost实例
        HttpPost httpPost = new HttpPost(url);

        // 请求参数配置
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000)
                .setConnectionRequestTimeout(10000).build();
        httpPost.setConfig(requestConfig);

        try {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setCharset(java.nio.charset.Charset.forName("UTF-8"));
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            for (MultipartFile file : multipartFile) {

                String fileName = file.getOriginalFilename();
                // 文件流
                builder.addBinaryBody(fileParamName, file.getInputStream(), ContentType.MULTIPART_FORM_DATA, fileName);
            }

           // String fileName = multipartFile.getOriginalFilename();
            // 文件流
           // builder.addBinaryBody(fileParamName, multipartFile.getInputStream(), ContentType.MULTIPART_FORM_DATA, fileName);
           // 表单中其他参数

            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);// 执行提交

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 返回
                String res = EntityUtils.toString(response.getEntity(), java.nio.charset.Charset.forName("UTF-8"));
                return res;
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                }
            }
        }
        return null;
    }

    /**
     * @Author 魏一鹤
     * @Description 将MultipartFile转换为File
     * @param
     * @return
     */
//    public static File[] multipartFileToFileBatch(MultipartFile[] files){
//        File[] array = new File[files.length];
//        for (int i = 0; i < files.length; i++) {
//            array[i] = multipartFileToFile(files[i]);
//        }
//    }
    private  static File multipartFileToFile(MultipartFile multiFile) {
        // 获取文件名
        String fileName = multiFile.getOriginalFilename();
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        // 若须要防止生成的临时文件重复,能够在文件名后添加随机码
        try {
            File file = File.createTempFile(fileName, prefix);
            multiFile.transferTo(file);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
