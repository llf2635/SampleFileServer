package com.file.server.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class File implements Serializable {
    @ApiModelProperty(value = "uuid")
    @TableId(type = IdType.UUID)
    private String uuid;

    @ApiModelProperty(value = "文件名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "文件大小")
    @TableField("size")
    private Long size;

    @ApiModelProperty(value = "文件类型")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "文件地址")
    @TableField("file_address")
    private String fileAddress;

    @ApiModelProperty(value = "上传时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "upload_time",fill = FieldFill.INSERT)
    private Date uploadTime;

    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
