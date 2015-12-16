package cn.zmvision.ccm.factory.system.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zmvision.ccm.factory.system.dao.mapping.UserMapper;
import cn.zmvision.ccm.factory.system.dao.mapping.UserRoleMapper;
import cn.zmvision.ccm.factory.system.dao.model.User;
import cn.zmvision.ccm.factory.system.dao.model.UserExample;

/**
 * 用户管理
 * 
 * @author xuchaoguo
 * 
 */
@Service
public class UserService {
	@Resource
	UserMapper userMapper;
	@Resource
	UserRoleMapper userRoleMapper;

	/**
	 * 根据登陆用户名查找用户
	 * 
	 * @param userName
	 * @return
	 */
	public User getByUserName(String userName) {
		UserExample example = new UserExample();
		example.createCriteria().andUsernameEqualTo(userName);

		List<User> list = userMapper.selectByExample(example);
		if (list != null && list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	/**
	 * 更新用户信息
	 * 
	 * @param user
	 * @return
	 */
	public boolean saveUser(User user) {
		if (user.getId() != null) {
			return userMapper.updateByPrimaryKey(user) > 0;
		} else {
			return userMapper.insert(user) > 0;
		}
	}
}
