package cn.zmvision.ccm.factory.system.security;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.zmvision.ccm.factory.base.listener.RequestListener;
import cn.zmvision.ccm.factory.system.dao.model.Menu;
import cn.zmvision.ccm.factory.system.dao.model.User;
import cn.zmvision.ccm.factory.system.service.MenuService;
import cn.zmvision.ccm.factory.system.service.UserService;
import cn.zmvision.ccm.factory.system.util.MenuBuilder;

public class RememberAuthenticationFilter extends FormAuthenticationFilter {
	private final Logger logger = LoggerFactory
			.getLogger(RememberAuthenticationFilter.class);
	@Resource
	private UserService userService;
	@Resource
	MenuService menuService;

	/**
	 * 这个方法决定了是否能让用户登录
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) {
		Subject subject = getSubject(request, response);

		boolean bAllowed = subject.isAuthenticated() || subject.isRemembered();
		if (bAllowed) {
			User user = SessionUtil.getUser();
			if (user == null) {
				String userName = subject.getPrincipal().toString();

				user = userService.getByUserName(userName);
				if (user == null) {
					logger.warn("user doesn't exist: {}", userName);
					return false;
				}

				SessionUtil.setUser(user);
			}
			// 初始化菜单
			if (SessionUtil.getMenu() == null) {
				List<Menu> menuList = menuService.getMenuListByUserId(user
						.getId());

				HttpServletRequest httpRequest = (HttpServletRequest) request;
				String basePath = (String) httpRequest
						.getAttribute(RequestListener.BASE_PATH);

				SessionUtil.setMenu(MenuBuilder.build(menuList, basePath));
			}
		}

		return bAllowed;
	}
}
