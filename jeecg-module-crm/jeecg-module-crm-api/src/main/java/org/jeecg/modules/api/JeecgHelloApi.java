package org.jeecg.modules.api;

import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.api.fallback.JeecgHelloUserFallback;
import org.jeecg.modules.entity.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


public interface JeecgHelloApi {

    /**
     * 根据service_path获取api配置信息
     * @param
     * @return
     */
    @GetMapping(value = "/test/jeecg/helloTest",produces = "application/json;charset=utf-8")
    Result<String> getMessage(@RequestParam(value = "name") String name);



    /**
     * 根据service_path获取api配置信息
     * @param
     * @return
     */
    @GetMapping(value = "/bsitest/userInfo/getOnes",produces = "application/json;charset=utf-8")
    Result<UserInfo> getOnes(@RequestParam(value = "id") String id);
}
