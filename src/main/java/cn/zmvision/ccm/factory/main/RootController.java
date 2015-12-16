package cn.zmvision.ccm.factory.main;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Controller
public class RootController {

	@RequestMapping(value = "/")
	public String index() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated() || subject.isRemembered())
			return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/main/";

		return "login";
	}
}
