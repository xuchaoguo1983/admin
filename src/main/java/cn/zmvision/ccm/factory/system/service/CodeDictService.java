package cn.zmvision.ccm.factory.system.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zmvision.ccm.factory.system.dao.mapping.CodeItemMapper;
import cn.zmvision.ccm.factory.system.dao.mapping.CodeMapMapper;
import cn.zmvision.ccm.factory.system.dao.model.CodeItem;
import cn.zmvision.ccm.factory.system.dao.model.CodeItemExample;
import cn.zmvision.ccm.factory.system.dao.model.CodeMap;
import cn.zmvision.ccm.factory.system.dao.model.CodeMapExample;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Service
public class CodeDictService {
	@Resource
	CodeMapMapper codeMapMapper;
	@Resource
	CodeItemMapper codeItemMapper;

	public PageList<CodeMap> queryCodeMapListByPage(CodeMapExample example,
			PageBounds pageBounds) {
		return codeMapMapper.selectByExample(example, pageBounds);
	}

	public CodeMap queryCodeMapById(String id) {
		return codeMapMapper.selectByPrimaryKey(id);
	}

	public boolean saveCodeMap(CodeMap record) {
		if (queryCodeMapById(record.getId()) == null)
			return codeMapMapper.insert(record) > 0;
		return codeMapMapper.updateByPrimaryKey(record) > 0;
	}

	public boolean deleteCodeMapById(String id) {
		CodeItemExample example = new CodeItemExample();
		example.createCriteria().andCodemapEqualTo(id);
		codeItemMapper.deleteByExample(example);

		return codeMapMapper.deleteByPrimaryKey(id) > 0;
	}

	public PageList<CodeItem> queryCodeItemListByPage(CodeItemExample example,
			PageBounds pageBounds) {
		return codeItemMapper.selectByExample(example, pageBounds);
	}
	
	public List<CodeItem> queryCodeItem(CodeItemExample example) {
		return codeItemMapper.selectByExample(example);
	}

	public boolean saveCodeItem(CodeItem record) {
		if (record.getId() == null)
			return codeItemMapper.insert(record) > 0;
		else
			return codeItemMapper.updateByPrimaryKey(record) > 0;
	}

	public boolean deleteCodeItemById(Integer id) {
		return codeItemMapper.deleteByPrimaryKey(id) > 0;
	}
}
