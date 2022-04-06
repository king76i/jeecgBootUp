package org.jeecg.common.bsiUser.api.fallback;

import feign.hystrix.FallbackFactory;
import org.jeecg.common.bsiUser.api.JeecgHelloApi;
import org.springframework.stereotype.Component;

/**
 * @author zyf
 */
@Component
public class JeecgHelloFallback implements FallbackFactory<JeecgHelloApi> {

    @Override
    public JeecgHelloApi create(Throwable throwable) {
        return null;
    }
}
