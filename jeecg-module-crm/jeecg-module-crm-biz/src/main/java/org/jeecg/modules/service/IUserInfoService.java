package org.jeecg.modules.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.catalina.User;
import org.jeecg.modules.entity.UserInfo;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 测试用户
 * @Author: jeecg-boot
 * @Date:   2022-03-30
 * @Version: V1.0
 */
public interface IUserInfoService extends IService<UserInfo> {
	public UserInfo selectOne(String id);
	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(UserInfo userInfo) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(UserInfo userInfo);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
