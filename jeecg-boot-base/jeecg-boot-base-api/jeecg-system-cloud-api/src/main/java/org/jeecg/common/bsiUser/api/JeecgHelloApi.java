package org.jeecg.common.bsiUser.api;
import org.jeecg.common.api.HelloUserApi;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.api.vo.UserInfo;
import org.jeecg.common.bsiUser.api.fallback.JeecgHelloFallback;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "jeecg-crm",fallbackFactory = JeecgHelloFallback.class)
public interface JeecgHelloApi extends HelloUserApi {
    /**
     * 根据service_path获取api配置信息
     * @param
     * @return
     */
    @GetMapping(value = "/bsitest/userInfo/getOnes",produces = "application/json;charset=utf-8")
    UserInfo getOnes(@RequestParam(value = "id") String id);
}
