package com.file.server.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.file.server.entity.File;
import com.file.server.mapper.FileMapper;
import com.file.server.service.FileService;
import com.file.server.utils.OssPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

@Service
public class FileServiceImpl extends ServiceImpl<FileMapper,File> implements FileService {

    @Override
    public String uploadFile(MultipartFile file) {
        // 工具类获取值
        String endpoint = OssPropertiesUtils.END_POIND;
        String accessKeyId = OssPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = OssPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = OssPropertiesUtils.BUCKET_NAME;

        try {
            // 创建OSS实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            //获取上传文件输入流
            InputStream inputStream = file.getInputStream();
            //获取文件名称
            String fileName = file.getOriginalFilename();
            //获取文件大小
            long fileSize = file.getSize();
            //获取文件类型
            String contentType = file.getContentType();
            //创建File对象
            File file1 = new File();

            //1 在文件名称里面添加随机唯一的值
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            // yuy76t5rew01.jpg
            fileName = uuid + fileName;

            //2 把文件按照日期进行分类
            //获取当前日期
            //yyyy/MM/dd
            String datePath = new DateTime().toString("yyyy/MM/dd");
            //拼接
            // yyyy/MM/dd/xxxx.jpg
            fileName = datePath + "/" + fileName;

            //调用oss方法实现上传
            //第一个参数  Bucket名称
            //第二个参数  上传到oss文件路径和文件名称   aa/bb/1.jpg
            //第三个参数  上传文件输入流
            ossClient.putObject(bucketName, fileName, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            //文件的实际地址
            String fileUrl = "https://" + bucketName + "." + endpoint + "/" + fileName;

            //向数据库添加File相关信息
            file1.setUuid(uuid);
            file1.setName(fileName);
            file1.setSize(fileSize);
            file1.setType(contentType);
            file1.setFileAddress(fileUrl);
            baseMapper.insert(file1);
            //需要把上传到阿里云oss路径手动拼接出来
            //  https://edu-guli-1010.oss-cn-beijing.aliyuncs.com/01.jpg

            //把上传之后文件uuid返回
            return uuid;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    //下载文件
    @Override
    public void downloadFile(HttpServletResponse response, String uuid) throws IOException{

        //通过传过来的uuid查询到对应文件
        File file = selectFile(uuid);
        //获取文件按地址
        String fileAddress = file.getFileAddress();
        //获取文件名称
        String fileName = file.getName();
        //获取文件类型
        String type = file.getType();

        //设置响应以下载的方式打开
        response.setCharacterEncoding("UTF-8");
//        response.setContentType(type);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename="+ fileName);

        //生成下载地址
        URL url = new URL(fileAddress);
        System.out.println("要访问的地址:"+url);
        //获取链接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // 模拟浏览器，设置通用的请求属性
        connection.setRequestProperty("accept", "*/*");
        connection.setRequestProperty("connection", "Keep-Alive");
        connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:93.0) Gecko/20100101 Firefox/93.0");
        //设置连接方法、连接超时时间，打开输入流
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(6000);
        connection.setDoInput(true);
//        connection.setDoOutput(true);
        //建立连接
        connection.connect();
        System.out.println("连接响应状态码:"+connection.getResponseCode());
        if (connection.getResponseCode()==200){
            //获取connection的字节输入缓冲流
            BufferedInputStream bufferedInputStream = new BufferedInputStream(connection.getInputStream());
            //获取response的字节缓冲输出流
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            //设置缓冲区大小
            byte[] bytes = new byte[1024];
            int read;
            //向response中写入数据
            while ((read = bufferedInputStream.read(bytes)) !=-1){
                bufferedOutputStream.write(bytes,0,read);
                bufferedOutputStream.flush();
            }
            //关闭流
            bufferedInputStream.close();
            bufferedOutputStream.close();
        }
        //httpclient的方式
        //创建一个浏览器的实例
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        //创建一个指定地址的Get请求
//        HttpGet httpGet = new HttpGet(fileAddress);
//        //发送Get请求，并响应
//        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
//        System.out.println("响应的内容"+httpResponse);
//        if (httpResponse.getStatusLine().getStatusCode()==200){
//            HttpEntity httpEntity = httpResponse.getEntity();
//        }
//        httpResponse.close();
//        httpClient.close();
    }

    @Override
    public File selectFile(String uuid) {
        return baseMapper.selectById(uuid);
    }


}
