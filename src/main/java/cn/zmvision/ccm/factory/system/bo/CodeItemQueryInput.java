package cn.zmvision.ccm.factory.system.bo;

import org.springframework.util.StringUtils;

import cn.zmvision.ccm.factory.base.bo.PageMeta;
import cn.zmvision.ccm.factory.system.dao.model.CodeItemExample;

public class CodeItemQueryInput extends PageMeta {
	private String codemap;

	public String getCodemap() {
		return codemap;
	}

	public void setCodemap(String codemap) {
		this.codemap = codemap;
	}

	public CodeItemExample getExample() {
		CodeItemExample example = new CodeItemExample();
		if (!StringUtils.isEmpty(codemap))
			example.createCriteria().andCodemapEqualTo(codemap);
		return example;
	}
}
