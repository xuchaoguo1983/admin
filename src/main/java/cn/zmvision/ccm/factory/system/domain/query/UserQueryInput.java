package cn.zmvision.ccm.factory.system.domain.query;

import org.springframework.util.StringUtils;

import cn.zmvision.ccm.factory.base.bo.PageMeta;
import cn.zmvision.ccm.factory.system.dao.model.UserExample;
import cn.zmvision.ccm.factory.system.dao.model.UserExample.Criteria;

public class UserQueryInput extends PageMeta {
	private String name;
	private String username;
	private String status;
	private String type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public UserExample getExample() {
		UserExample example = new UserExample();
		Criteria c = example.createCriteria();
		if (!StringUtils.isEmpty(name))
			c.andNameLike("%" + name + "%");

		if (!StringUtils.isEmpty(username))
			c.andUsernameLike("%" + username + "%");

		if (!StringUtils.isEmpty(status)) {
			c.andStatusEqualTo(status);
		}

		if (!StringUtils.isEmpty(type)) {
			c.andTypeEqualTo(type);
		}

		return example;
	}
}
