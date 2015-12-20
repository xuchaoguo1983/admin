package cn.zmvision.ccm.factory.system.domain.model;

import java.util.List;

import org.springframework.beans.BeanUtils;

import cn.zmvision.ccm.factory.system.dao.model.Role;

public class RoleModel extends Role {
	private static final long serialVersionUID = 1L;

	public RoleModel() {

	}

	public RoleModel(Role role) {
		BeanUtils.copyProperties(role, this);
	}

	private List<String> menuId;

	public List<String> getMenuId() {
		return menuId;
	}

	public void setMenuId(List<String> menuId) {
		this.menuId = menuId;
	}

}
