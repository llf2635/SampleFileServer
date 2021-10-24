package com.file.server.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class FileVo implements Serializable {
    @ApiModelProperty(value = "uuid")
    private Long uuid;
    @ApiModelProperty(value = "文件名")
    private String name;
    @ApiModelProperty(value = "文件大小")
    private int size;
    @ApiModelProperty(value = "文件类型")
    private String type;
    @ApiModelProperty(value = "上传时间")
    private Data upload_time;
    @ApiModelProperty(value = "修改时间")
    private Data update_time;
}
