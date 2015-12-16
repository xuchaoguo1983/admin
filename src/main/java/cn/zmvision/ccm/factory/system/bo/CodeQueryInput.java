package cn.zmvision.ccm.factory.system.bo;

import org.springframework.util.StringUtils;

import cn.zmvision.ccm.factory.base.bo.PageMeta;
import cn.zmvision.ccm.factory.system.dao.model.CodeMapExample;

public class CodeQueryInput extends PageMeta {
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public CodeMapExample getExample() {
		CodeMapExample example = new CodeMapExample();
		if (!StringUtils.isEmpty(code))
			example.createCriteria().andCodeLike("%" + code + "%");
		return example;
	}
}
