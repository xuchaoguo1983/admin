package cn.zmvision.ccm.factory.system.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zmvision.ccm.factory.base.bo.JsonResult;
import cn.zmvision.ccm.factory.base.bo.Message;
import cn.zmvision.ccm.factory.base.bo.PageResult;
import cn.zmvision.ccm.factory.system.bo.CodeItemQueryInput;
import cn.zmvision.ccm.factory.system.bo.CodeQueryInput;
import cn.zmvision.ccm.factory.system.dao.model.CodeItem;
import cn.zmvision.ccm.factory.system.dao.model.CodeItemExample;
import cn.zmvision.ccm.factory.system.dao.model.CodeItemExample.Criteria;
import cn.zmvision.ccm.factory.system.dao.model.CodeMap;
import cn.zmvision.ccm.factory.system.service.CodeDictService;

import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Controller
@RequestMapping("system/dict")
public class CodeDictController {
	@Resource
	CodeDictService codeDictService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		return "system/dict_list";
	}

	@RequestMapping(value = "/map/page", method = RequestMethod.POST)
	@ResponseBody
	public PageResult queryCodeMapByPage(CodeQueryInput input) {
		PageList<CodeMap> list = codeDictService.queryCodeMapListByPage(
				input.getExample(), input.getPageBounds());

		return new PageResult(input, list);
	}

	@RequestMapping(value = "/map/save")
	@ResponseBody
	public JsonResult saveCodeMap(CodeMap code) {
		return new JsonResult(codeDictService.saveCodeMap(code));
	}

	@RequestMapping(value = "/map/delete/{id}")
	@ResponseBody
	public JsonResult deleteCodeMap(@PathVariable String id) {
		return new JsonResult(codeDictService.deleteCodeMapById(id));
	}

	@RequestMapping(value = "/code/page", method = RequestMethod.POST)
	@ResponseBody
	public PageResult queryCodeItemByPage(CodeItemQueryInput input) {
		PageList<CodeItem> list = codeDictService.queryCodeItemListByMap(
				input.getExample(), input.getPageBounds());

		return new PageResult(input, list);
	}

	@RequestMapping(value = "/code/save")
	@ResponseBody
	public JsonResult saveCode(CodeItem code) {
		CodeItemExample example = new CodeItemExample();
		Criteria c = example.createCriteria();
		c.andCodeEqualTo(code.getCode());
		c.andCodemapEqualTo(code.getCodemap());
		if (code.getId() != null)
			c.andIdNotEqualTo(code.getId());
		List<CodeItem> list = codeDictService.queryCodeItem(example);
		if (list != null && list.size() > 0) {
			return new JsonResult(Message.DICT_CODE_DUPLICATE);
		}

		return new JsonResult(codeDictService.saveCodeItem(code));
	}

	@RequestMapping(value = "/code/delete/{id}")
	@ResponseBody
	public JsonResult deleteCodeItem(@PathVariable Integer id) {
		return new JsonResult(codeDictService.deleteCodeItemById(id));
	}
}
