package com.meinil.system.init;

import com.meinil.common.cache.constants.CacheConstants;
import com.meinil.common.cache.utils.CacheUtil;
import com.meinil.system.domain.vo.SysMenuVO;
import com.meinil.system.service.ISysMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Meinil
 * @date 2025/3/18
 * @description 将接口权限添加到缓存
 */
@Component
public class PermissionInitializer implements CommandLineRunner {

    private final static Logger log = LoggerFactory.getLogger(PermissionInitializer.class);

    private final ISysMenuService menuService;

    public PermissionInitializer(ISysMenuService menuService) {
        this.menuService = menuService;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("========开始缓存接口权限========");
        CacheUtil.expire(CacheConstants.INTERFACE_PERMISSION, 0L);
        List<SysMenuVO> sysMenuVOS = menuService.selectInterfaces();
        Map<String, String> permissionMap = sysMenuVOS.stream().collect(Collectors.toMap(SysMenuVO::getPath, SysMenuVO::getPerms));
        CacheUtil.setCacheMap(CacheConstants.INTERFACE_PERMISSION, permissionMap);
        log.info("========接口权限缓存结束========");
    }
}
