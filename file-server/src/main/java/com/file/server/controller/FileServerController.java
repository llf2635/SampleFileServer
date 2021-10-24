package com.file.server.controller;

import com.file.server.entity.File;
import com.file.server.entity.utils.ResultJson;
import com.file.server.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Api("文件服务端")
@RestController
@RequestMapping("/fileserver")
public class FileServerController {

    @Autowired
    private FileService fileService;

    @ApiOperation("文件上传")
    @PostMapping("/uploadfile")
    public ResultJson uploadFile(MultipartFile file) throws IOException {
        final String uuid = fileService.uploadFile(file);
        return ResultJson.ok().data("uuid",uuid);
    }

    @ApiOperation(("文件下载"))
    @GetMapping("/downloadfile/{uuid}")
    public void downloadFile(HttpServletResponse response, @PathVariable String uuid) throws IOException, ServletException {
        fileService.downloadFile(response,uuid);
    }

    @ApiOperation("根据uuid查询")
    @GetMapping("/selectFileByUuid/{uuid}")
    public ResultJson selectFile(@PathVariable String uuid){
        File file = fileService.selectFile(uuid);
        return ResultJson.ok().data("record",file);
    }
}
