package com.meinil.sparion.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meinil.sparion.common.entity.FileInfo;
import com.meinil.sparion.common.mapper.FileInfoMapper;
import com.meinil.sparion.common.service.IFileInfoService;
import org.springframework.stereotype.Service;

/**
 * @author Meinil
 * @date 2022/10/5
 * @description
 */
@Service
public class FileInfoServiceImpl extends ServiceImpl<FileInfoMapper, FileInfo> implements IFileInfoService {
}
