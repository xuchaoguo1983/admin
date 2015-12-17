package cn.zmvision.ccm.factory.system.bo;

import org.springframework.util.StringUtils;

import cn.zmvision.ccm.factory.base.bo.PageMeta;
import cn.zmvision.ccm.factory.system.dao.model.RoleExample;
import cn.zmvision.ccm.factory.system.dao.model.RoleExample.Criteria;

public class RoleQueryInput extends PageMeta {
	private String name;
	private String status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public RoleExample getExample() {
		RoleExample example = new RoleExample();
		Criteria c = example.createCriteria();
		if (!StringUtils.isEmpty(name))
			c.andNameLike("%" + name + "%");

		if (!StringUtils.isEmpty(status)) {
			c.andStatusEqualTo(status);
		}

		return example;
	}
}
