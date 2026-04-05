package com.meinil.resource.controller;

import com.meinil.common.core.domain.R;
import com.meinil.common.web.constants.FileConstant;
import com.meinil.common.web.enums.FileStorageModelEnum;
import com.meinil.common.web.enums.FileStorageTypeEnum;
import com.meinil.common.web.exception.SparionException;
import com.meinil.resource.domain.vo.ResFileVO;
import com.meinil.resource.service.IResFileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Meinil
 * @date 2025/3/24
 * @description
 */
@RestController
@RequestMapping("/resource/file")
public class ResFileController {

    private final IResFileService resFileService;

    public ResFileController(IResFileService resFileService) {
        this.resFileService = resFileService;
    }

    /**
     * 文件上传
     */
    @PostMapping("/upload")
    public R<String> uploadFile(@RequestParam("file") MultipartFile file,
                                @RequestParam(name = "storageModel", required = false, defaultValue = FileConstant.FILE_STORAGE_MODEL_MINIO) String storageModel,
                                @RequestParam(name = "storageType", required = false, defaultValue = FileConstant.FILE_STORAGE_TYPE_TEMP) String storageType) {
        Long id = resFileService.upload(file, FileStorageModelEnum.value(storageModel), FileStorageTypeEnum.value(storageType));
        return R.data(String.valueOf(id));
    }

    /**
     * 下载文件
     */
    @GetMapping("/download/{fileId}")
    public void download(@PathVariable(name = "fileId") Long fileId, HttpServletResponse response) {
        resFileService.download(fileId, response);
    }

    /**
     * 根据id集合查询文件元数据
     */
    @GetMapping("/listByIds")
    public R<List<ResFileVO>> listByIds(@RequestParam(name = "ids", required = false) String ids) {
        return R.data(resFileService.listByIds(parseIds(ids)));
    }

    private List<Long> parseIds(String ids) {
        if (ids == null || ids.isBlank()) {
            return Collections.emptyList();
        }
        try {
            return Arrays.stream(ids.split(","))
                    .map(String::trim)
                    .filter(id -> !id.isEmpty())
                    .map(Long::valueOf)
                    .toList();
        } catch (NumberFormatException e) {
            throw new SparionException("文件id格式错误");
        }
    }
}
