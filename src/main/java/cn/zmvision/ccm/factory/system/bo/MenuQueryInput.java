package cn.zmvision.ccm.factory.system.bo;

import org.springframework.util.StringUtils;

import cn.zmvision.ccm.factory.base.bo.PageMeta;
import cn.zmvision.ccm.factory.system.dao.model.MenuExample;
import cn.zmvision.ccm.factory.system.dao.model.MenuExample.Criteria;

public class MenuQueryInput extends PageMeta {
	private String id;
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MenuExample getExample() {
		MenuExample example = new MenuExample();

		Criteria c = example.createCriteria();
		if (!StringUtils.isEmpty(this.getId())) {
			c.andIdLike("%" + this.getId() + "%");
		}

		if (!StringUtils.isEmpty(this.getName())) {
			c.andNameLike("%" + this.getName() + "%");
		}

		return example;
	}
}
