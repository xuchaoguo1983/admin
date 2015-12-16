package cn.zmvision.ccm.factory.system.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import cn.zmvision.ccm.factory.system.dao.model.User;

public class SessionUtil {
	public final static String SESSION_ATTR_USER = "_user";
	public final static String SESSION_ATTR_MENU = "_menu";

	public static boolean setSession(Object key, Object value) {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession(true);
			session.setAttribute(key, value);
			return true;
		}

		return false;
	}

	public static Object getSession(Object key) {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession();
			if (null != session) {
				return session.getAttribute(key);
			}
		}

		return null;
	}

	public static String getMenu() {
		Object o = getSession(SESSION_ATTR_MENU);
		return (o != null ? (String) o : null);
	}

	public static void setMenu(String menuHtml) {
		setSession(SESSION_ATTR_MENU, menuHtml);
	}

	public static User getUser() {
		Object o = getSession(SESSION_ATTR_USER);
		return (o != null ? (User) o : null);
	}

	public static void setUser(User user) {
		setSession(SESSION_ATTR_USER, user);
	}
}
