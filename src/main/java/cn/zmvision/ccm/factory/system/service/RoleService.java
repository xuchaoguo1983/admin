package cn.zmvision.ccm.factory.system.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zmvision.ccm.factory.Constants;
import cn.zmvision.ccm.factory.system.dao.mapping.RoleMapper;
import cn.zmvision.ccm.factory.system.dao.mapping.UserRoleMapper;
import cn.zmvision.ccm.factory.system.dao.model.Role;
import cn.zmvision.ccm.factory.system.dao.model.RoleExample;
import cn.zmvision.ccm.factory.system.dao.model.UserRoleExample;
import cn.zmvision.ccm.factory.system.dao.model.UserRoleKey;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * 角色管理
 * 
 * @author xuchaoguo
 * 
 */
@Service
public class RoleService {
	@Resource
	RoleMapper roleMapper;
	@Resource
	UserRoleMapper userRoleMapper;

	/**
	 * 查找用户关联的启用角色列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<Role> getRoleListByUserId(Integer userId) {
		UserRoleExample example = new UserRoleExample();
		example.createCriteria().andUserIdEqualTo(userId);

		List<UserRoleKey> list = userRoleMapper.selectByExample(example);
		if (list == null || list.size() == 0)
			return null;
		List<Integer> roleIds = new ArrayList<Integer>();
		for (UserRoleKey key : list)
			roleIds.add(key.getRoleId());

		RoleExample example2 = new RoleExample();
		example2.createCriteria()
				.andStatusEqualTo(Constants.SYS_USER_STATE_ACTIVE)
				.andIdIn(roleIds);

		return roleMapper.selectByExample(example2);
	}

	public PageList<Role> queryRoleListByPage(RoleExample example,
			PageBounds pageBounds) {
		return roleMapper.selectByExample(example, pageBounds);
	}

	public Role queryRoleById(Integer id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	public boolean saveRole(Role record) {
		if (record.getId() == null)
			return roleMapper.insert(record) > 0;
		return roleMapper.updateByPrimaryKey(record) > 0;
	}

	public boolean deleteRoleById(Integer id) {
		return roleMapper.deleteByPrimaryKey(id) > 0;
	}
}
