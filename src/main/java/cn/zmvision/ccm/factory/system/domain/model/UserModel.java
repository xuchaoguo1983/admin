package cn.zmvision.ccm.factory.system.domain.model;

import java.util.List;

import org.springframework.beans.BeanUtils;

import cn.zmvision.ccm.factory.system.dao.model.User;

public class UserModel extends User {
	private static final long serialVersionUID = 1L;

	private List<Integer> roleId;

	public UserModel() {
		super();
	}

	public UserModel(User user) {
		BeanUtils.copyProperties(user, this);
	}

	public List<Integer> getRoleId() {
		return roleId;
	}

	public void setRoleId(List<Integer> roleId) {
		this.roleId = roleId;
	}

}
