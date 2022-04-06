package org.jeecg.common.api;

import org.jeecg.common.api.vo.UserInfo;

/**
 * @Description: $
 * @Author: 曾柏青
 * @date ：Created in 2022/3/30 16:03
 */
public interface HelloUserApi {
    UserInfo getOnes(String username);
}
