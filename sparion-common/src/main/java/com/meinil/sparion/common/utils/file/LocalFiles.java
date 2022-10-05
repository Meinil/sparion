package com.meinil.sparion.common.utils.file;

import com.meinil.sparion.common.entity.FileInfo;
import com.meinil.sparion.common.mapper.FileInfoMapper;
import com.meinil.sparion.common.result.SparionException;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author Meinil
 * @date 2022/10/5
 * @description 本地文件上传
 */
public class LocalFiles implements Files{

    private FileInfoMapper fileInfoMapper;

    public LocalFiles(FileInfoMapper fileInfoMapper) {
        this.fileInfoMapper = fileInfoMapper;
    }

    @Override
    public FileInfo upload(MultipartFile multipartFile) {
        return upload(Arrays.asList(multipartFile).get(0));
    }

    @Override
    public List<FileInfo> upload(MultipartFile[] files) {
        ArrayList<FileInfo> fileInfos = new ArrayList<>(files.length);
        for (MultipartFile multipartFile : files) {
            // 记录到数据库的实体
            FileInfo fileInfo = new FileInfo();
            fileInfos.add(fileInfo);

            // 获取文件的名称
            String originalFilename = multipartFile.getOriginalFilename();
            fileInfo.setOriginName(originalFilename);

            // 文件后缀 例如：.png
            String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            // uuid 生成文件名
            String uuid = String.valueOf(UUID.randomUUID());
            fileInfo.setUuid(uuid);
            // 根路径，在 resources/static/file
            String basePath = null;
            try {
                LocalDate now = LocalDate.now();
                basePath = String.format("%sfile/%s/%s/%s/", ResourceUtils.getURL("classpath:").getPath(), now.getYear(), now.getMonth(), now.getDayOfMonth());
                // 新的文件名，使用uuid生成文件名
                String fileName = uuid + fileSuffix;
                fileInfo.setPath(fileName);

                if (fileInfoMapper.insert(fileInfo) <= 0) {
                    throw new SparionException("文件插入数据库失败");
                }
                // 创建新的文件
                File fileExist = new File(basePath);
                // 文件夹不存在，则新建
                if (!fileExist.exists()) {
                    fileExist.mkdirs();
                }
                // 获取文件对象
                File file = new File(basePath, fileName);
                // 完成文件的上传
                multipartFile.transferTo(file);
            } catch (Exception e) {
                throw new SparionException("文件上传错误");
            }
        }

        return fileInfos;
    }

    @Override
    public Boolean delete(Long fileId) {
        return null;
    }
}
