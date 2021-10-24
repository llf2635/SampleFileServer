package com.file.server.entity.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author 龙茶清欢
 * @Date 2021/10/21
 **/
@Data
public class ResultJson {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    //HshMap存放键值对，最符合json格式
    private Map<String, Object> data = new HashMap<String, Object>();

    //把构造方法私有
    private ResultJson() {
    }

    //成功静态方法
    public static ResultJson ok() {
        ResultJson r = new ResultJson();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }

    //失败静态方法
    public static ResultJson error() {
        ResultJson r = new ResultJson();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }

    public ResultJson success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public ResultJson message(String message) {
        this.setMessage(message);
        return this;
    }

    public ResultJson code(Integer code) {
        this.setCode(code);
        return this;
    }

    public ResultJson data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public ResultJson data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }
}
