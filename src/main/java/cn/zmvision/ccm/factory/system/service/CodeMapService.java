package cn.zmvision.ccm.factory.system.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zmvision.ccm.factory.base.BaseService;
import cn.zmvision.ccm.factory.system.dao.mapping.CodeItemMapper;
import cn.zmvision.ccm.factory.system.dao.mapping.CodeMapMapper;
import cn.zmvision.ccm.factory.system.dao.model.CodeItemExample;
import cn.zmvision.ccm.factory.system.dao.model.CodeMap;
import cn.zmvision.ccm.factory.system.dao.model.CodeMapExample;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * 字典管理
 * 
 * @author xuchaoguo
 * 
 */
@Service
public class CodeMapService extends BaseService<CodeMap, CodeMapExample> {
	@Resource
	CodeMapMapper codeMapMapper;
	@Resource
	CodeItemMapper codeItemMapper;

	@Override
	public PageList<CodeMap> queryByPage(CodeMapExample example,
			PageBounds pageBounds) {
		return codeMapMapper.selectByExample(example, pageBounds);
	}

	@Override
	public List<CodeMap> queryAllByExample(CodeMapExample example) {
		return codeMapMapper.selectByExample(example);
	}

	@Override
	public CodeMap queryById(Integer id) {
		throw new RuntimeException("not supported.");
	}

	public CodeMap queryById(String id) {
		return codeMapMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean save(CodeMap model) {
		if (queryById(model.getId()) == null)
			return codeMapMapper.insert(model) > 0;
		return codeMapMapper.updateByPrimaryKey(model) > 0;
	}

	@Override
	public boolean deleteById(Integer id) {
		throw new RuntimeException("not supported.");
	}

	public boolean deleteById(String id) {
		if (codeMapMapper.deleteByPrimaryKey(id) > 0) {
			CodeItemExample example = new CodeItemExample();
			example.createCriteria().andCodemapEqualTo(id);
			return codeItemMapper.deleteByExample(example) > 0;
		}
		return false;
	}
}
