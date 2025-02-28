package com.meinil.system.api.feign;

import com.meinil.system.api.service.IUserFeignApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Meinil
 * @date 2025/2/23
 * @description
 */
@FeignClient(name = "${sparion.applications.sparion-module-system}")
public interface UserFeignClient extends IUserFeignApi {

}
