package cn.zmvision.ccm.factory.system.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zmvision.ccm.factory.base.bo.JsonResult;
import cn.zmvision.ccm.factory.base.bo.PageResult;
import cn.zmvision.ccm.factory.system.bo.RoleQueryInput;
import cn.zmvision.ccm.factory.system.dao.model.Role;
import cn.zmvision.ccm.factory.system.dao.model.RoleMenuKey;
import cn.zmvision.ccm.factory.system.service.RoleService;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Controller
@RequestMapping("system/role")
public class RoleController {
	@Resource
	RoleService roleService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		return "system/role_list";
	}

	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public PageResult queryByPage(RoleQueryInput input) {
		PageBounds pageBounds = input.getPageBounds();
		PageList<Role> list = roleService.queryRoleListByPage(
				input.getExample(), pageBounds);

		return new PageResult(input, list);
	}

	@RequestMapping(value = "/save")
	@ResponseBody
	public JsonResult save(Role role,
			@RequestParam(value = "menuIds") List<String> menuIds) {
		return new JsonResult(roleService.saveRole(role, menuIds));
	}

	@RequestMapping(value = "/delete/{id}")
	@ResponseBody
	public JsonResult delete(@PathVariable Integer id) {
		return new JsonResult(roleService.deleteRoleById(id));
	}

	@RequestMapping(value = "/menu")
	@ResponseBody
	public JsonResult menu(@RequestParam(value = "id") Integer id) {
		List<RoleMenuKey> list = roleService.getMenuListByRoleId(id);
		List<String> menuList = new ArrayList<String>();
		if (list != null && list.size() > 0) {
			for (RoleMenuKey key : list)
				menuList.add(key.getMenuId());
		}

		return new JsonResult(menuList);
	}
}
