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
import cn.zmvision.ccm.factory.system.bo.UserQueryInput;
import cn.zmvision.ccm.factory.system.dao.model.User;
import cn.zmvision.ccm.factory.system.dao.model.UserRoleKey;
import cn.zmvision.ccm.factory.system.service.UserService;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Controller
@RequestMapping("system/user")
public class UserController {
	@Resource
	UserService userService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		return "system/user_list";
	}

	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public PageResult queryByPage(UserQueryInput input) {
		PageBounds pageBounds = input.getPageBounds();
		PageList<User> list = userService.queryUserListByPage(
				input.getExample(), pageBounds);

		return new PageResult(input, list);
	}

	@RequestMapping(value = "/save")
	@ResponseBody
	public JsonResult save(User user,
			@RequestParam(value = "roleId") List<Integer> roleId) {
		return new JsonResult(userService.saveUser(user, roleId));
	}

	@RequestMapping(value = "/delete/{id}")
	@ResponseBody
	public JsonResult delete(@PathVariable Integer id) {
		return new JsonResult(userService.deleteUserById(id));
	}

	@RequestMapping(value = "/role")
	@ResponseBody
	public JsonResult role(@RequestParam(value = "id") Integer id) {
		List<UserRoleKey> list = userService.queryUserRoleListById(id);
		List<Integer> roleList = new ArrayList<Integer>();
		if (list != null && list.size() > 0) {
			for (UserRoleKey key : list)
				roleList.add(key.getRoleId());
		}

		return new JsonResult(roleList);
	}
}
