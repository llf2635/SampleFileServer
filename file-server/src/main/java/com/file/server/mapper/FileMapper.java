package com.file.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.file.server.entity.File;
import org.mapstruct.Mapper;

@Mapper
public interface FileMapper extends BaseMapper<File> {
}
