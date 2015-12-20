package cn.zmvision.ccm.factory.system.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zmvision.ccm.factory.base.BaseController;
import cn.zmvision.ccm.factory.base.bo.JsTreeElement;
import cn.zmvision.ccm.factory.base.bo.JsonResult;
import cn.zmvision.ccm.factory.base.bo.Message;
import cn.zmvision.ccm.factory.base.bo.PageResult;
import cn.zmvision.ccm.factory.system.dao.model.Menu;
import cn.zmvision.ccm.factory.system.domain.query.MenuQueryInput;
import cn.zmvision.ccm.factory.system.service.MenuService;

import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Controller
@RequestMapping("system/menu")
public class MenuController extends BaseController<MenuQueryInput, Menu> {
	@Resource
	MenuService menuService;

	@Override
	public PageResult queryByPage(MenuQueryInput input) {
		PageList<Menu> list = menuService.queryByPage(
				input.getExample(), input.getPageBounds());

		return new PageResult(input, list);
	}

	@RequestMapping(value = "/query/dummy")
	@ResponseBody
	@Override
	public JsonResult query(Integer id) {
		throw new RuntimeException("not implemented.");
	}

	@RequestMapping(value = "/query")
	@ResponseBody
	public JsonResult query2(String id) {
		return new JsonResult(menuService.queryById(id));
	}

	@Override
	public JsonResult save(Menu menu) {
		if (StringUtils.isEmpty(menu.getPid())) {
			// 根菜单
			menu.setPid("#");
		}

		if (!menu.getPid().equals("#")
				&& menuService.queryById(menu.getPid()) == null) {
			return new JsonResult(Message.MENU_PARENT_NOT_EXIST);
		}

		return new JsonResult(menuService.save(menu));
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
		// id类型不同，因此单独写了一个方法
		return new JsonResult(menuService.deleteById(id));
	}

	@RequestMapping(value = "/tree")
	@ResponseBody
	public List<JsTreeElement> tree(
			@RequestParam(value = "id", defaultValue = "#") String id) {
		List<JsTreeElement> tree = new ArrayList<JsTreeElement>();
		List<Menu> menuList = menuService.queryAllByExample(null);
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
