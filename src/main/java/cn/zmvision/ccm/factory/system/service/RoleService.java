package cn.zmvision.ccm.factory.system.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zmvision.ccm.factory.Constants;
import cn.zmvision.ccm.factory.system.dao.mapping.RoleMapper;
import cn.zmvision.ccm.factory.system.dao.mapping.RoleMenuMapper;
import cn.zmvision.ccm.factory.system.dao.mapping.UserRoleMapper;
import cn.zmvision.ccm.factory.system.dao.model.Role;
import cn.zmvision.ccm.factory.system.dao.model.RoleExample;
import cn.zmvision.ccm.factory.system.dao.model.RoleMenuExample;
import cn.zmvision.ccm.factory.system.dao.model.RoleMenuKey;
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
	RoleMenuMapper roleMenuMapper;
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
		example2.createCriteria().andStatusEqualTo(Constants.CODE_ENTITY_ACTIVE)
				.andIdIn(roleIds);

		return roleMapper.selectByExample(example2);
	}

	public PageList<Role> queryRoleListByPage(RoleExample example,
			PageBounds pageBounds) {
		return roleMapper.selectByExample(example, pageBounds);
	}

	public List<RoleMenuKey> getMenuListByRoleId(Integer id) {
		RoleMenuExample example = new RoleMenuExample();
		example.createCriteria().andRoleIdEqualTo(id);
		return roleMenuMapper.selectByExample(example);
	}

	public Role queryRoleById(Integer id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	public boolean saveRole(Role record, List<String> menuIds) {
		boolean result = false;
		if (record.getId() == null)
			result = roleMapper.insert(record) > 0;
		else
			result = roleMapper.updateByPrimaryKey(record) > 0;
		if (result && menuIds != null) {
			// 先删除再重建
			RoleMenuExample example = new RoleMenuExample();
			example.createCriteria().andRoleIdEqualTo(record.getId());
			roleMenuMapper.deleteByExample(example);

			if (menuIds.size() > 0) {
				for (String menuId : menuIds) {
					RoleMenuKey key = new RoleMenuKey();
					key.setMenuId(menuId);
					key.setRoleId(record.getId());
					roleMenuMapper.insert(key);
				}
			}
		}

		return result;
	}

	public boolean deleteRoleById(Integer id) {
		if (roleMapper.deleteByPrimaryKey(id) > 0) {
			RoleMenuExample example = new RoleMenuExample();
			example.createCriteria().andRoleIdEqualTo(id);
			roleMenuMapper.deleteByExample(example);
			return true;
		}

		return false;
	}
}
