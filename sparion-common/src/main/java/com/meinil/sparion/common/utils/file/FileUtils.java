package com.meinil.sparion.common.utils.file;

import com.meinil.sparion.common.mapper.FileInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Meinil
 * @date 2022/10/5
 * @description
 */
@Component
public class FileUtils {
    @Autowired
    private FileInfoMapper fileInfoMapper;

    /**
     * 获取本地文件上传类
     * @return
     */
    public Files getLocalFiles() {
        return new LocalFiles(fileInfoMapper);
    }
}
