package com.meinil.resource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.meinil.resource.domain.entity.ResFile;

/**
 * @author Meinil
 * @date 2025/3/24
 * @description
 */
public interface ResFileMapper extends BaseMapper<ResFile> {

    /**
     * 根据hash值查询文件是否存在
     * @param hash 文件hash值
     * @return 文件对象
     */
    ResFile selectByHash(String hash);
}
