package com.meinil.resource.controller;

import com.meinil.common.core.domain.R;
import com.meinil.common.web.enums.FileStorageModelEnum;
import com.meinil.common.web.enums.FileStorageTypeEnum;
import com.meinil.common.web.exception.SparionException;
import com.meinil.resource.domain.vo.ResFileVO;
import com.meinil.resource.service.IResFileService;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ResFileControllerTest {

    @Test
    void listByIdsShouldTreatBlankIdsAsEmptyList() {
        RecordingResFileService resFileService = new RecordingResFileService();
        ResFileController controller = new ResFileController(resFileService);

        R<List<ResFileVO>> result = controller.listByIds("");

        assertTrue(result.getData().isEmpty());
        assertEquals(List.of(), resFileService.capturedIds);
    }

    @Test
    void listByIdsShouldPreserveCommaSeparatedOrder() {
        RecordingResFileService resFileService = new RecordingResFileService();
        ResFileController controller = new ResFileController(resFileService);

        controller.listByIds("3,1,2");

        assertEquals(List.of(3L, 1L, 2L), resFileService.capturedIds);
    }

    @Test
    void listByIdsShouldRejectInvalidIds() {
        RecordingResFileService resFileService = new RecordingResFileService();
        ResFileController controller = new ResFileController(resFileService);

        SparionException exception = assertThrows(SparionException.class, () -> controller.listByIds("1,foo"));

        assertEquals("文件id格式错误", exception.getMessage());
    }

    private static final class RecordingResFileService implements IResFileService {

        private List<Long> capturedIds = List.of();

        @Override
        public Long upload(MultipartFile file, FileStorageModelEnum storageModel, FileStorageTypeEnum storageType) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void download(Long fileId, HttpServletResponse response) {
            throw new UnsupportedOperationException();
        }

        @Override
        public List<ResFileVO> listByIds(List<Long> ids) {
            this.capturedIds = List.copyOf(ids);
            return List.of();
        }
    }
}
