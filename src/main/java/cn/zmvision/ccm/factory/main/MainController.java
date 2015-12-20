package cn.zmvision.ccm.factory.main;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zmvision.ccm.factory.base.DummyController;
import cn.zmvision.ccm.factory.base.bo.JsonResult;
import cn.zmvision.ccm.factory.base.bo.Message;
import cn.zmvision.ccm.factory.system.dao.model.User;
import cn.zmvision.ccm.factory.system.security.SessionUtil;
import cn.zmvision.ccm.factory.system.service.UserService;

@Controller
@RequestMapping("main")
public class MainController extends DummyController {
	@Resource
	UserService userService;

	@RequestMapping(value = "/setpassord", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult setPassword(
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "newpassword", required = true) String newpassword,
			@RequestParam(value = "confirmpassword", required = true) String confirmpassword) {
		User user = SessionUtil.getUser();
		if (!user.getPassword().equals(password))
			return new JsonResult(Message.LOGIN_PASSOWRD_INCORRECT);
		if (!newpassword.equals(confirmpassword))
			return new JsonResult(Message.DATA_ERROR);

		user.setPassword(newpassword);
		userService.save(user, null);

		return new JsonResult();
	}

}
