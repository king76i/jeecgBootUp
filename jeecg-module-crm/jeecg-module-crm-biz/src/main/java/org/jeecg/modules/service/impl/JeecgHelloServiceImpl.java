package org.jeecg.modules.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.entity.JeecgHelloEntity;
import org.jeecg.modules.mapper.JeecgHelloMapper;
import org.jeecg.modules.service.IJeecgHelloService;
import org.springframework.stereotype.Service;

/**
 * 测试Service
 */
@Service
public class JeecgHelloServiceImpl extends ServiceImpl<JeecgHelloMapper, JeecgHelloEntity> implements IJeecgHelloService {

    @Override
    public Result<String> hello() {
        return Result.OK("hello jeecg tttttttttttttttttttttttttttttt!");
    }
}
