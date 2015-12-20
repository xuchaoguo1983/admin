package cn.zmvision.ccm.factory.system.domain.query;

import org.springframework.util.StringUtils;

import cn.zmvision.ccm.factory.base.bo.PageMeta;
import cn.zmvision.ccm.factory.system.dao.model.CodeMapExample;

public class CodeMapQueryInput extends PageMeta {
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CodeMapExample getExample() {
		CodeMapExample example = new CodeMapExample();
		if (!StringUtils.isEmpty(id))
			example.createCriteria().andIdLike("%" + id + "%");
		return example;
	}
}
