package com.meinil.resource.service.impl;

import com.meinil.resource.convert.ResFileConvert;
import com.meinil.resource.domain.entity.ResFile;
import com.meinil.resource.domain.vo.ResFileVO;
import com.meinil.resource.mapper.ResFileMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ResFileServiceImplTest {

    @Test
    void listByIdsShouldPreserveInputOrderAndOmitMissingFiles() {
        ResFile first = new ResFile();
        first.setId(1L);
        first.setFileName("avatar.png");
        first.setFileSuffix("png");
        first.setFileSize(1024L);

        ResFile third = new ResFile();
        third.setId(3L);
        third.setFileName("report.pdf");
        third.setFileSuffix("pdf");
        third.setFileSize(2048L);

        AtomicReference<List<Long>> capturedIds = new AtomicReference<>();
        ResFileMapper baseMapper = proxy((methodName, args) -> {
            if ("selectBatchIds".equals(methodName)) {
                List<Long> actualIds = ((List<?>) args[0]).stream().map(Long.class::cast).toList();
                capturedIds.set(actualIds);
                assertEquals(List.of(3L, 1L, 2L), actualIds);
                return List.of(first, third);
            }
            throw new UnsupportedOperationException(methodName);
        });
        ResFileServiceImpl resFileService =
                new ResFileServiceImpl(baseMapper, Mappers.getMapper(ResFileConvert.class), Map.of());

        List<ResFileVO> result = resFileService.listByIds(List.of(3L, 1L, 2L));

        assertEquals(List.of(3L, 1L), result.stream().map(ResFileVO::getId).toList());
        assertEquals("report.pdf", result.get(0).getFileName());
        assertEquals("avatar.png", result.get(1).getFileName());
        assertEquals(List.of(3L, 1L, 2L), capturedIds.get());
    }

    @Test
    void listByIdsShouldReturnEmptyListWhenIdsEmpty() {
        AtomicBoolean selectBatchIdsInvoked = new AtomicBoolean(false);
        ResFileMapper baseMapper = proxy((methodName, args) -> {
            if ("selectBatchIds".equals(methodName)) {
                selectBatchIdsInvoked.set(true);
                return List.of();
            }
            throw new UnsupportedOperationException(methodName);
        });
        ResFileServiceImpl resFileService =
                new ResFileServiceImpl(baseMapper, Mappers.getMapper(ResFileConvert.class), Map.of());

        List<ResFileVO> result = resFileService.listByIds(List.of());

        assertTrue(result.isEmpty());
        assertFalse(selectBatchIdsInvoked.get());
    }

    private static ResFileMapper proxy(StubHandler handler) {
        return (ResFileMapper) Proxy.newProxyInstance(
                ResFileMapper.class.getClassLoader(),
                new Class<?>[]{ResFileMapper.class},
                (proxy, method, args) -> handler.invoke(method.getName(), args)
        );
    }

    @FunctionalInterface
    private interface StubHandler {
        Object invoke(String methodName, Object[] args);
    }
}
