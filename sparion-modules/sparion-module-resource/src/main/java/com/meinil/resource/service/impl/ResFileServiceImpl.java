package com.meinil.resource.service.impl;

import com.meinil.common.core.utlis.DateUtil;
import com.meinil.common.core.utlis.StringUtil;
import com.meinil.common.web.enums.FileStorageModelEnum;
import com.meinil.common.web.enums.FileStorageTypeEnum;
import com.meinil.common.web.exception.SparionException;
import com.meinil.resource.domain.entity.ResFile;
import com.meinil.resource.mapper.ResFileMapper;
import com.meinil.resource.service.IFileStorageService;
import com.meinil.resource.service.IResFileService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

/**
 * @author Meinil
 * @date 2025/3/24
 * @description
 */
@Service
public class ResFileServiceImpl implements IResFileService {

    private final static Logger log = LoggerFactory.getLogger(ResFileServiceImpl.class);

    private final ResFileMapper baseMapper;

    private final Map<String, IFileStorageService> storageServices;

    public ResFileServiceImpl(ResFileMapper baseMapper, Map<String, IFileStorageService> storageServices) {
        this.baseMapper = baseMapper;
        this.storageServices = storageServices;
    }

    @Override
    public Long upload(MultipartFile file, FileStorageModelEnum storageModel, FileStorageTypeEnum storageType) {
        ResFile resFile = getResFile(file, storageModel.getCode());
        ResFile temp = baseMapper.selectByHash(resFile.getHash());
        if (Objects.nonNull(temp)) {
            return temp.getId();
        }
        try (InputStream inputStream = file.getInputStream()) {
            resFile.setStorageType(storageType.getCode());
            getStorageService(storageModel).upload(inputStream, file.getSize(), resFile.getPath(), storageType);
        } catch (Exception e) {
            throw new SparionException(e);
        }
        baseMapper.insert(resFile);
        return resFile.getId();
    }

    @Override
    public void download(Long fileId, HttpServletResponse response) {
        ResFile file = baseMapper.selectById(fileId);
        if (Objects.isNull(file)) {
            throw new SparionException("文件不存在");
        }

        try (InputStream inputStream = getStorageService(FileStorageModelEnum.value(file.getStorageModel())).download(file.getPath(), FileStorageTypeEnum.value(file.getStorageType()));){
            byte[] buffer = new byte[1024];
            int len;
            try (FastByteArrayOutputStream os = new FastByteArrayOutputStream()){
                while ((len=inputStream.read(buffer))!=-1){
                    os.write(buffer,0,len);
                }
                os.flush();
                byte[] bytes = os.toByteArray();
                response.setContentType("application/octet-stream");
                response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getFileName(), StandardCharsets.UTF_8));
                response.addHeader("Content-Length", String.valueOf(file.getFileSize()));
                try (ServletOutputStream stream = response.getOutputStream()){
                    stream.write(bytes);
                    stream.flush();
                }
            }
        } catch (Exception e) {
            throw new SparionException(e.getMessage());
        }
    }

    /**
     * 获取文件对象
     * @param file 文件流
     */
    private ResFile getResFile(MultipartFile file, String storageModel) {
        return getResFile(file, storageModel, "");
    }

    /**
     * 获取文件名称
     * @param file 文件流
     * @param fileName 文件名称
     */
    private ResFile getResFile(MultipartFile file, String storageModel, String fileName) {
        ResFile resFile = new ResFile();
        resFile.setFileName(StringUtil.isBlank(fileName) ? file.getOriginalFilename() : fileName);
        resFile.setOriginalName(file.getOriginalFilename());
        resFile.setFileSuffix(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.') + 1));
        resFile.setPath(DateUtil.format(LocalDateTime.now(), "yyyy/MM/dd/") + file.getOriginalFilename());
        resFile.setStorageModel(storageModel);

        // 设置文件hash值
        try (InputStream is = file.getInputStream()) {
            byte[] byteArray = new byte[1024];
            int bytesCount;
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            while ((bytesCount = is.read(byteArray)) != -1) {
                digest.update(byteArray, 0, bytesCount);
            }
            byte[] bytes = digest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            resFile.setHash(sb.toString());
        } catch (Exception e) {
            throw new SparionException(e);
        }
        resFile.setFileSize(file.getSize());
        return resFile;
    }

    private IFileStorageService getStorageService(FileStorageModelEnum storageModel) {
        return storageServices.get(storageModel.getBeanName());
    }
}
