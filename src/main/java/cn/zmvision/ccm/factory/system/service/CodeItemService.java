package cn.zmvision.ccm.factory.system.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zmvision.ccm.factory.base.BaseService;
import cn.zmvision.ccm.factory.system.dao.mapping.CodeItemMapper;
import cn.zmvision.ccm.factory.system.dao.model.CodeItem;
import cn.zmvision.ccm.factory.system.dao.model.CodeItemExample;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * 字典项管理
 * 
 * @author xuchaoguo
 * 
 */
@Service
public class CodeItemService extends BaseService<CodeItem, CodeItemExample> {
	@Resource
	CodeItemMapper codeItemMapper;

	@Override
	public PageList<CodeItem> queryByPage(CodeItemExample example,
			PageBounds pageBounds) {
		return codeItemMapper.selectByExample(example, pageBounds);
	}

	@Override
	public List<CodeItem> queryAllByExample(CodeItemExample example) {
		return codeItemMapper.selectByExample(example);
	}

	@Override
	public CodeItem queryById(Integer id) {
		return codeItemMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean save(CodeItem model) {
		if (model.getId() == null)
			return codeItemMapper.insert(model) > 0;
		else
			return codeItemMapper.updateByPrimaryKey(model) > 0;
	}

	@Override
	public boolean deleteById(Integer id) {
		return codeItemMapper.deleteByPrimaryKey(id) > 0;
	}

}
