package org.jeecg.modules.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.entity.JeecgHelloEntity;

/**
 * 测试接口
 */
public interface IJeecgHelloService extends IService<JeecgHelloEntity> {

    Result<String> hello();

}
