package cn.zmvision.ccm.factory.system.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.zmvision.ccm.factory.base.BaseController;
import cn.zmvision.ccm.factory.base.bo.JsonResult;
import cn.zmvision.ccm.factory.base.bo.Message;
import cn.zmvision.ccm.factory.base.bo.PageResult;
import cn.zmvision.ccm.factory.system.dao.model.Role;
import cn.zmvision.ccm.factory.system.dao.model.RoleMenuKey;
import cn.zmvision.ccm.factory.system.domain.model.RoleModel;
import cn.zmvision.ccm.factory.system.domain.query.RoleQueryInput;
import cn.zmvision.ccm.factory.system.service.RoleService;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Controller
@RequestMapping("system/role")
public class RoleController extends BaseController<RoleQueryInput, RoleModel> {
	@Resource
	RoleService roleService;

	@Override
	public PageResult queryByPage(RoleQueryInput input) {
		PageBounds pageBounds = input.getPageBounds();
		PageList<Role> list = roleService.queryRoleListByPage(
				input.getExample(), pageBounds);

		return new PageResult(input, list);
	}

	@Override
	public JsonResult save(RoleModel model) {
		return new JsonResult(roleService.saveRole(model, model.getMenuId()));
	}

	public JsonResult delete(Integer id) {
		return new JsonResult(roleService.deleteRoleById(id));
	}

	@Override
	public JsonResult query(Integer id) {
		Role role = roleService.queryRoleById(id);
		if (role == null)
			return new JsonResult(Message.DATA_ERROR);

		RoleModel model = new RoleModel(role);

		List<RoleMenuKey> list = roleService.getMenuListByRoleId(id);
		List<String> menuList = new ArrayList<String>();
		if (list != null && list.size() > 0) {
			for (RoleMenuKey key : list)
				menuList.add(key.getMenuId());
		}

		model.setMenuId(menuList);

		return new JsonResult(model);
	}

}
