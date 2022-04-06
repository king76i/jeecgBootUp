package org.jeecg.modules.api.fallback;

import feign.hystrix.FallbackFactory;
import org.jeecg.modules.api.JeecgHelloApi;
import org.springframework.stereotype.Component;

/**
 * @author zyf
 */
@Component
public class JeecgHelloUserFallback implements FallbackFactory<JeecgHelloApi> {

    @Override
    public JeecgHelloApi create(Throwable throwable) {
        return null;
    }
}
