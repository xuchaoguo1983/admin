package cn.zmvision.ccm.factory.system.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zmvision.ccm.factory.base.BaseController;
import cn.zmvision.ccm.factory.base.bo.JsonResult;
import cn.zmvision.ccm.factory.base.bo.PageResult;
import cn.zmvision.ccm.factory.system.dao.model.CodeMap;
import cn.zmvision.ccm.factory.system.domain.query.CodeMapQueryInput;
import cn.zmvision.ccm.factory.system.service.CodeMapService;

import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Controller
@RequestMapping("system/dict/map")
public class CodeMapController extends
		BaseController<CodeMapQueryInput, CodeMap> {
	@Resource
	CodeMapService codeMapService;

	@Override
	public String index() {
		return "system/dict";
	}

	@Override
	public PageResult queryByPage(CodeMapQueryInput input) {
		PageList<CodeMap> list = codeMapService.queryByPage(input.getExample(),
				input.getPageBounds());

		return new PageResult(input, list);
	}

	@Override
	public JsonResult query(Integer id) {
		throw new RuntimeException("not implemented.");
	}

	@Override
	public JsonResult save(CodeMap record) {
		return new JsonResult(codeMapService.save(record));
	}

	@RequestMapping(value = "/delete/dummy")
	@ResponseBody
	@Override
	public JsonResult delete(Integer id) {
		throw new RuntimeException("not implemented.");
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public JsonResult delete2(String id) {
		return new JsonResult(codeMapService.deleteById(id));
	}

}
