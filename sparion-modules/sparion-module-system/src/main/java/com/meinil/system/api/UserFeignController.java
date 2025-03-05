package com.meinil.system.api;

import com.meinil.common.core.domain.R;
import com.meinil.common.core.domain.UserInfo;
import com.meinil.common.web.exception.SparionException;
import com.meinil.system.api.dto.RegisterDTO;
import com.meinil.system.service.ISysPermissionService;
import com.meinil.system.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Meinil
 * @date 2025/2/23
 * @description Feign controller
 */
@RestController
@RequestMapping("/feign/system")
public class UserFeignController {

    private final static Logger log = LoggerFactory.getLogger(UserFeignController.class);

    private final ISysUserService userService;

    private final ISysPermissionService permissionService;

    public UserFeignController(ISysUserService userService, ISysPermissionService permissionService) {
        this.userService = userService;
        this.permissionService = permissionService;
    }

    @GetMapping("/user")
    public R<UserInfo> getUserInfo(@RequestParam("username") String username) {
        UserInfo userInfo = userService.getLoginUser(username);

        try (ExecutorService virtualThread = Executors.newVirtualThreadPerTaskExecutor()) {
            // 查询角色权限
            CompletableFuture<Void> task1 = CompletableFuture.runAsync(() -> {
                userInfo.setRoles(permissionService.getRolePermission(userInfo.getUserId()));
            }, virtualThread);
            // 查询菜单权限
            CompletableFuture<Void> task2 = CompletableFuture.runAsync(() -> {
                userInfo.setMenus(permissionService.getMenuPermission(userInfo.getUserId()));
            }, virtualThread);

            CompletableFuture.allOf(task1, task2).get();
        } catch (Exception e) {
            throw new SparionException(e);
        }

        return R.ok(userInfo);
    }

    @PostMapping("/register")
    public R<Boolean> registerUserInfo(@RequestBody RegisterDTO registerDTO) {
        return R.ok(userService.register(registerDTO));
    }
}
