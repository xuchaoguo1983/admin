package cn.zmvision.ccm.factory.system.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zmvision.ccm.factory.Constants;
import cn.zmvision.ccm.factory.base.BaseService;
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
public class RoleService extends BaseService<Role, RoleExample> {
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
	public List<Role> queryAllByUserId(Integer userId) {
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
				.andStatusEqualTo(Constants.CODE_ENTITY_ACTIVE)
				.andIdIn(roleIds);

		return roleMapper.selectByExample(example2);
	}

	@Override
	public PageList<Role> queryByPage(RoleExample example, PageBounds pageBounds) {
		return roleMapper.selectByExample(example, pageBounds);
	}

	@Override
	public List<Role> queryAllByExample(RoleExample example) {
		return roleMapper.selectByExample(example);
	}

	/**
	 * 查询关联角色的菜单
	 * 
	 * @param id
	 * @return
	 */
	public List<RoleMenuKey> queryAllMenuKeyById(Integer id) {
		RoleMenuExample example = new RoleMenuExample();
		example.createCriteria().andRoleIdEqualTo(id);
		return roleMenuMapper.selectByExample(example);
	}

	@Override
	public Role queryById(Integer id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean save(Role model) {
		if (model.getId() == null)
			return roleMapper.insert(model) > 0;
		else
			return roleMapper.updateByPrimaryKey(model) > 0;
	}

	/**
	 * 保存角色和相关联的菜单
	 * 
	 * @param model
	 * @param menuIds
	 * @return
	 */
	public boolean save(Role model, List<String> menuIds) {
		if (this.save(model) && menuIds != null) {
			// 先删除再重建
			RoleMenuExample example = new RoleMenuExample();
			example.createCriteria().andRoleIdEqualTo(model.getId());
			roleMenuMapper.deleteByExample(example);

			if (menuIds.size() > 0) {
				for (String menuId : menuIds) {
					RoleMenuKey key = new RoleMenuKey();
					key.setMenuId(menuId);
					key.setRoleId(model.getId());
					roleMenuMapper.insert(key);
				}
			}
		}

		return true;
	}

	@Override
	public boolean deleteById(Integer id) {
		if (roleMapper.deleteByPrimaryKey(id) > 0) {
			RoleMenuExample example = new RoleMenuExample();
			example.createCriteria().andRoleIdEqualTo(id);
			roleMenuMapper.deleteByExample(example);
			return true;
		}

		return false;
	}

}
