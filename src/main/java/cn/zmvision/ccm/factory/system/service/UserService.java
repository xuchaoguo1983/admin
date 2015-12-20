package cn.zmvision.ccm.factory.system.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zmvision.ccm.factory.base.BaseService;
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
public class UserService extends BaseService<User, UserExample> {
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
	public User queryByName(String userName) {
		UserExample example = new UserExample();
		example.createCriteria().andUsernameEqualTo(userName);

		List<User> list = userMapper.selectByExample(example);
		if (list != null && list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	/**
	 * 查询关联用户的角色列表
	 * 
	 * @param id
	 * @return
	 */
	public List<UserRoleKey> queryRoleKeyById(Integer id) {
		UserRoleExample example = new UserRoleExample();
		example.createCriteria().andUserIdEqualTo(id);

		return userRoleMapper.selectByExample(example);
	}

	@Override
	public PageList<User> queryByPage(UserExample example, PageBounds pageBounds) {
		return userMapper.selectByExample(example, pageBounds);
	}

	@Override
	public List<User> queryAllByExample(UserExample example) {
		return userMapper.selectByExample(example);
	}

	@Override
	public User queryById(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean save(User model) {
		if (model.getId() != null) {
			return userMapper.updateByPrimaryKeySelective(model) > 0;
		} else {
			model.setCreatetime(DateUtil.getNow());
			// default password is 111111, save as MD5
			model.setPassword("96e79218965eb72c92a549dd5a330112");
			return userMapper.insert(model) > 0;
		}
	}

	/**
	 * 保存用户和关联角色信息
	 * 
	 * @param model
	 * @param roleIds
	 * @return
	 */
	public boolean save(User model, List<Integer> roleIds) {
		if (this.save(model) && roleIds != null) {
			// 删除后重建
			UserRoleExample example = new UserRoleExample();
			example.createCriteria().andUserIdEqualTo(model.getId());
			userRoleMapper.deleteByExample(example);

			if (roleIds.size() > 0) {
				for (Integer roleId : roleIds) {
					UserRoleKey key = new UserRoleKey();
					key.setRoleId(roleId);
					key.setUserId(model.getId());

					userRoleMapper.insert(key);
				}
			}
		}

		return true;
	}

	@Override
	public boolean deleteById(Integer id) {
		if (userMapper.deleteByPrimaryKey(id) > 0) {
			UserRoleExample example = new UserRoleExample();
			example.createCriteria().andUserIdEqualTo(id);
			userRoleMapper.deleteByExample(example);

			return true;
		}

		return false;
	}

}
