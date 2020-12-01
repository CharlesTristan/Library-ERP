package com.chentong.erp.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.chentong.erp.vo.resp.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/11/17 9:12
 */
@Controller
@RequestMapping("/file")
public class FileUploadController {
    @Value("${oss.endpoint}")
    private String endpoint;
    @Value("${oss.accessKeyId}")
    private String accessKeyId;
    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${oss.bucketName}")
    private String bucketName;
    @Value("${oss.baseUrl}")
    private String baseUrl;
    @RequestMapping("/upload")
    @ResponseBody
    public DataResult handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        DataResult dataResult = DataResult.success();
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String photoUrl = UUID.randomUUID().toString()+suffixName;
        // 上传文件到指定的存储空间（bucketName）并将其保存为指定的文件名称（objectName）。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, photoUrl, new ByteArrayInputStream(file.getBytes()));
        // 关闭OSSClient。
        ossClient.shutdown();
        dataResult.setData(baseUrl+photoUrl);
        return dataResult;
    }
}
