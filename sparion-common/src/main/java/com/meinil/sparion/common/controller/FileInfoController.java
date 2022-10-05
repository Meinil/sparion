package com.meinil.sparion.common.controller;

import com.meinil.sparion.common.entity.FileInfo;
import com.meinil.sparion.common.utils.file.FileUtils;
import com.meinil.sparion.common.utils.file.Files;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Meinil
 * @date 2022/10/5
 * @description
 */
@Api("文件管理")
@RestController
@RequestMapping("/file")
public class FileInfoController {

    @Autowired
    private FileUtils fileUtils;

    @PostMapping("/upload")
    public List<FileInfo> upload(@RequestParam("files") MultipartFile[] files) {
        Files localFiles = fileUtils.getLocalFiles();
        return localFiles.upload(files);
    }
}
