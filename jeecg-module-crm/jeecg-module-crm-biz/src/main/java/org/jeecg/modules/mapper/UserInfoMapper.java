package org.jeecg.modules.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.entity.UserInfo;

/**
 * @Description: 测试用户
 * @Author: jeecg-boot
 * @Date:   2022-03-30
 * @Version: V1.0
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    UserInfo selectOnes(String id);
}
