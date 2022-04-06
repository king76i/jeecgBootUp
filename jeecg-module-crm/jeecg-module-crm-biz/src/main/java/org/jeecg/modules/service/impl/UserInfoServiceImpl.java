package org.jeecg.modules.service.impl;



import org.apache.catalina.User;
import org.jeecg.modules.entity.UserInfo;
import org.jeecg.modules.mapper.UserInfoMapper;
import org.jeecg.modules.service.IUserInfoService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 测试用户
 * @Author: jeecg-boot
 * @Date:   2022-03-30
 * @Version: V1.0
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Override
	public UserInfo selectOne(String id) {
		return userInfoMapper.selectOnes(id);
	}

	@Override
	@Transactional
	public void saveMain(UserInfo userInfo) {
		userInfoMapper.insert(userInfo);
	}

	@Override
	@Transactional
	public void updateMain(UserInfo userInfo) {
		userInfoMapper.updateById(userInfo);
		
		//1.先删除子表数据
		
		//2.子表数据重新插入
	}

	@Override
	@Transactional
	public void delMain(String id) {
		userInfoMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			userInfoMapper.deleteById(id);
		}
	}
	
}
