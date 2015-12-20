package cn.zmvision.ccm.factory.system.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zmvision.ccm.factory.base.BaseController;
import cn.zmvision.ccm.factory.base.bo.JsonResult;
import cn.zmvision.ccm.factory.base.bo.Message;
import cn.zmvision.ccm.factory.base.bo.PageResult;
import cn.zmvision.ccm.factory.system.dao.model.CodeItem;
import cn.zmvision.ccm.factory.system.dao.model.CodeItemExample;
import cn.zmvision.ccm.factory.system.dao.model.CodeItemExample.Criteria;
import cn.zmvision.ccm.factory.system.domain.query.CodeItemQueryInput;
import cn.zmvision.ccm.factory.system.service.CodeItemService;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Controller
@RequestMapping("system/dict/code")
public class CodeItemController extends
		BaseController<CodeItemQueryInput, CodeItem> {

	@Resource
	CodeItemService codeItemService;

	@Override
	public PageResult queryByPage(CodeItemQueryInput input) {
		PageList<CodeItem> list = codeItemService.queryByPage(
				input.getExample(), input.getPageBounds());

		return new PageResult(input, list);
	}

	@Override
	public JsonResult query(Integer id) {
		throw new RuntimeException("not implemented.");
	}

	@Override
	public JsonResult save(CodeItem record) {
		CodeItemExample example = new CodeItemExample();
		Criteria c = example.createCriteria();
		c.andCodeEqualTo(record.getCode());
		c.andCodemapEqualTo(record.getCodemap());
		if (record.getId() != null)
			c.andIdNotEqualTo(record.getId());
		List<CodeItem> list = codeItemService.queryAllByExample(example);
		if (list != null && list.size() > 0) {
			return new JsonResult(Message.DICT_CODE_DUPLICATE);
		}

		return new JsonResult(codeItemService.save(record));
	}

	@Override
	public JsonResult delete(Integer id) {
		return new JsonResult(codeItemService.deleteById(id));
	}

	@RequestMapping(value = "/map")
	@ResponseBody
	public JsonResult queryCodeItemByMap(
			@RequestParam(value = "list") List<String> list) {
		CodeItemExample example = new CodeItemExample();
		example.createCriteria().andCodemapIn(list);

		PageBounds pageBounds = new PageBounds();
		pageBounds.setOrders(Order.formString("codemap.asc, sort.asc"));

		return new JsonResult(codeItemService.queryByPage(example, pageBounds));
	}
}
