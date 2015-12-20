package cn.zmvision.ccm.factory.system.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zmvision.ccm.factory.base.util.DateUtil;
import cn.zmvision.ccm.factory.system.dao.mapping.UserMapper;
import cn.zmvision.ccm.factory.system.dao.mapping.UserRoleMapper;
import cn.zmvision.ccm.factory.system.dao.model.User;
import cn.zmvision.ccm.factory.system.dao.model.UserExample;
import cn.zmvision.ccm.factory.system.dao.model.UserRoleExample;
import cn.zmvision.ccm.factory.system.dao.model.UserRoleKey;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

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
	public User queryUserByName(String userName) {
		UserExample example = new UserExample();
		example.createCriteria().andUsernameEqualTo(userName);

		List<User> list = userMapper.selectByExample(example);
		if (list != null && list.size() > 0)
			return list.get(0);
		else
			return null;
	}
	
	/**
	 * 根据ID查询用户信息
	 * @param id
	 * @return
	 */
	public User queryUserById(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	/**
	 * 分页查询用户列表
	 * 
	 * @param example
	 * @param pageBounds
	 * @return
	 */
	public PageList<User> queryUserListByPage(UserExample example,
			PageBounds pageBounds) {
		return userMapper.selectByExample(example, pageBounds);
	}

	/**
	 * 保存用户和关联角色信息
	 * 
	 * @param user
	 * @param roleIds
	 * @return
	 */
	public boolean saveUser(User user, List<Integer> roleIds) {
		boolean result = false;
		if (user.getId() != null) {
			result = userMapper.updateByPrimaryKeySelective(user) > 0;
		} else {
			user.setCreatetime(DateUtil.getNow());
			// default password is 111111, save as MD5
			user.setPassword("96e79218965eb72c92a549dd5a330112");
			result = userMapper.insert(user) > 0;
		}

		if (result && roleIds != null) {
			// 删除后重建
			UserRoleExample example = new UserRoleExample();
			example.createCriteria().andUserIdEqualTo(user.getId());
			userRoleMapper.deleteByExample(example);

			if (roleIds.size() > 0) {
				for (Integer roleId : roleIds) {
					UserRoleKey key = new UserRoleKey();
					key.setRoleId(roleId);
					key.setUserId(user.getId());

					userRoleMapper.insert(key);
				}
			}
		}

		return result;
	}

	/**
	 * 删除用户和关联的角色信息
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteUserById(Integer id) {
		if (userMapper.deleteByPrimaryKey(id) > 0) {
			UserRoleExample example = new UserRoleExample();
			example.createCriteria().andUserIdEqualTo(id);
			userRoleMapper.deleteByExample(example);

			return true;
		}

		return false;
	}

	/**
	 * 查询关联用户的角色列表
	 * 
	 * @param id
	 * @return
	 */
	public List<UserRoleKey> queryUserRoleListById(Integer id) {
		UserRoleExample example = new UserRoleExample();
		example.createCriteria().andUserIdEqualTo(id);

		return userRoleMapper.selectByExample(example);
	}
}
