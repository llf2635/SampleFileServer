package com.file.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.file.server.entity.File;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface FileService extends IService<File> {
    String uploadFile(MultipartFile file) throws IOException;

    void downloadFile(HttpServletResponse response,String uuid) throws IOException, ServletException;

    File selectFile(String uuid);
}
