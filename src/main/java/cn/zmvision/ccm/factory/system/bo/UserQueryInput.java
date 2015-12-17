package cn.zmvision.ccm.factory.system.bo;

import org.springframework.util.StringUtils;

import cn.zmvision.ccm.factory.base.bo.PageMeta;
import cn.zmvision.ccm.factory.system.dao.model.UserExample;
import cn.zmvision.ccm.factory.system.dao.model.UserExample.Criteria;

public class UserQueryInput extends PageMeta {
	private String name;
	private String username;
	private Integer status;

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public UserExample getExample() {
		UserExample example = new UserExample();
		Criteria c = example.createCriteria();
		if (!StringUtils.isEmpty(name))
			c.andNameLike("%" + name + "%");

		if (!StringUtils.isEmpty(username))
			c.andUsernameLike("%" + username + "%");

		if (status != null) {
			c.andStatusEqualTo(status);
		}

		return example;
	}
}
