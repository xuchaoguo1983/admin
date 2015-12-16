package cn.zmvision.ccm.factory.system.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import cn.zmvision.ccm.factory.base.bo.JsonResult;
import cn.zmvision.ccm.factory.base.bo.Message;
import cn.zmvision.ccm.factory.system.util.VerifyCodeUtil;

@Controller
@RequestMapping("system/auth")
public class AuthController {
	private final Logger logger = LoggerFactory
			.getLogger(AuthController.class);

	private final static String SESSION_ATTR_VERIFY_CODE = "verifyCode";

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String index() {
		return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/";
	}

	@RequestMapping(value = "/verifycode", method = RequestMethod.GET)
	public void verifyCode(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String verifyCode = VerifyCodeUtil.generateTextCode(
				VerifyCodeUtil.TYPE_NUM_ONLY, 4, null);
		// 将验证码放到HttpSession里面
		request.getSession().setAttribute(SESSION_ATTR_VERIFY_CODE, verifyCode);
		// 设置页面不缓存
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		// 设置输出的内容的类型为JPEG图像
		response.setContentType("image/jpeg");
		BufferedImage bufferedImage = VerifyCodeUtil.generateImageCode(
				verifyCode, 120, 40, 3, true, Color.WHITE, Color.BLACK, null);
		// 写给浏览器
		ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "verifycode", required = true) String verifycode,
			@RequestParam(value = "remember", defaultValue = "N") String remember,
			Model model, HttpSession session) {
		String realVerifyCode = (String) session
				.getAttribute(SESSION_ATTR_VERIFY_CODE);
		if (realVerifyCode == null
				|| !realVerifyCode.equalsIgnoreCase(verifycode)) {
			JsonResult result = new JsonResult(
					Message.LOGIN_VERIFY_CODE_INCORRECT);
			result.writeToRequest(model);
		} else {

			UsernamePasswordToken token = new UsernamePasswordToken(username,
					password);
			token.setRememberMe(remember.equals("Y"));

			Subject currentUser = SecurityUtils.getSubject();
			try {
				currentUser.login(token);

				if (currentUser.isAuthenticated()) {
					// 登陆成功
					return InternalResourceViewResolver.REDIRECT_URL_PREFIX
							+ "/main/";
				} else {
					token.clear();
				}
			} catch (UnknownAccountException uae) {
				// 无账户
				JsonResult result = new JsonResult(
						Message.LOGIN_ACCOUNT_INCORRECT);
				result.writeToRequest(model);
			} catch (IncorrectCredentialsException ice) {
				// 密码不正确
				JsonResult result = new JsonResult(
						Message.LOGIN_PASSOWRD_INCORRECT);
				result.writeToRequest(model);
			} catch (LockedAccountException lae) {
				// 用户已锁
				JsonResult result = new JsonResult(Message.LOGIN_ACCOUNT_LOCKED);
				result.writeToRequest(model);
			} catch (AuthenticationException ae) {
				JsonResult result = new JsonResult(Message.LOGIN_FAILED);
				result.writeToRequest(model);
				logger.error("", ae);
			}
		}

		return InternalResourceViewResolver.FORWARD_URL_PREFIX + "/";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		SecurityUtils.getSubject().logout();
		return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/";
	}
}
