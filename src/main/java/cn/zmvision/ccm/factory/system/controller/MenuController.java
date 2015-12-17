package cn.zmvision.ccm.factory.system.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zmvision.ccm.factory.base.bo.JsTreeElement;
import cn.zmvision.ccm.factory.base.bo.JsonResult;
import cn.zmvision.ccm.factory.base.bo.Message;
import cn.zmvision.ccm.factory.base.bo.PageResult;
import cn.zmvision.ccm.factory.system.bo.MenuQueryInput;
import cn.zmvision.ccm.factory.system.dao.model.Menu;
import cn.zmvision.ccm.factory.system.service.MenuService;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Controller
@RequestMapping("system/menu")
public class MenuController {
	@Resource
	MenuService menuService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		return "system/menu_list";
	}

	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public PageResult queryByPage(MenuQueryInput input) {
		PageBounds pageBounds = input.getPageBounds();
		PageList<Menu> list = menuService.queryMenuListByPage(
				input.getExample(), pageBounds);

		return new PageResult(input, list);
	}

	@RequestMapping(value = "/save")
	@ResponseBody
	public JsonResult save(Menu menu) {
		String pid = menu.getPid();
		if (StringUtils.isEmpty(pid)) {
			menu.setPid("#");
		}
		if (!pid.equals("#") && menuService.queryMenuById(pid) == null) {
			return new JsonResult(Message.MENU_PARENT_NOT_EXIST);
		}

		return new JsonResult(menuService.saveMenu(menu));
	}

	@RequestMapping(value = "/delete/{id}")
	@ResponseBody
	public JsonResult delete(@PathVariable String id) {
		return new JsonResult(menuService.deleteMenuById(id));
	}

	@RequestMapping(value = "/tree")
	@ResponseBody
	public List<JsTreeElement> tree(
			@RequestParam(value = "id", defaultValue = "#") String id) {
		List<JsTreeElement> tree = new ArrayList<JsTreeElement>();
		List<Menu> menuList = menuService.queryMenuListByExample(null);
		if (menuList != null && menuList.size() > 0) {
			for (Menu menu : menuList) {
				JsTreeElement node = new JsTreeElement();
				node.setId(menu.getId());
				node.setParent(menu.getPid());
				node.setText(menu.getName());
				node.setIcon(menu.getIcon());

				tree.add(node);
			}
		}

		return tree;
	}
}
