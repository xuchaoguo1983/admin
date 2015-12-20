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
import cn.zmvision.ccm.factory.system.dao.model.User;
import cn.zmvision.ccm.factory.system.dao.model.UserRoleKey;
import cn.zmvision.ccm.factory.system.domain.model.UserModel;
import cn.zmvision.ccm.factory.system.domain.query.UserQueryInput;
import cn.zmvision.ccm.factory.system.service.UserService;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Controller
@RequestMapping("system/user")
public class UserController extends BaseController<UserQueryInput, UserModel> {
	@Resource
	UserService userService;

	@Override
	public PageResult queryByPage(UserQueryInput input) {
		PageBounds pageBounds = input.getPageBounds();
		PageList<User> list = userService.queryUserListByPage(
				input.getExample(), pageBounds);

		return new PageResult(input, list);
	}

	@Override
	public JsonResult query(Integer id) {
		User user = userService.queryUserById(id);
		if (user == null)
			return new JsonResult(Message.DATA_ERROR);

		UserModel model = new UserModel(user);

		List<UserRoleKey> list = userService.queryUserRoleListById(id);
		List<Integer> roleList = new ArrayList<Integer>();
		if (list != null && list.size() > 0) {
			for (UserRoleKey key : list)
				roleList.add(key.getRoleId());
		}

		model.setRoleId(roleList);

		return new JsonResult(model);
	}

	@Override
	public JsonResult save(UserModel record) {
		return new JsonResult(userService.saveUser(record, record.getRoleId()));
	}

	@Override
	public JsonResult delete(Integer id) {
		return new JsonResult(userService.deleteUserById(id));
	}

}
