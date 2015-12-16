package cn.zmvision.ccm.factory.system.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import cn.zmvision.ccm.factory.system.dao.model.Menu;

/**
 * 菜单构建器
 * 
 * @author xuchaoguo
 * 
 */
public class MenuBuilder {
	static class MenuStrut {
		private Menu menu;
		private List<MenuStrut> subMenus;

		public MenuStrut(Menu menu) {
			this.menu = menu;
			this.subMenus = new ArrayList<MenuStrut>();
		}

		public Menu getMenu() {
			return menu;
		}

		public List<MenuStrut> getSubMenus() {
			return subMenus;
		}
	}

	public static String build(List<Menu> menuList, String basePath) {
		if (menuList == null || menuList.size() == 0)
			return null;

		StringBuilder sb = new StringBuilder();
		// 排序
		Collections.sort(menuList, new Comparator<Menu>() {

			@Override
			public int compare(Menu o1, Menu o2) {
				return o1.getId().compareTo(o2.getId());
			}
		});

		Map<String, MenuStrut> menuMap = new LinkedHashMap<>();
		for (Menu menu : menuList) {
			MenuStrut ms = menuMap.get(menu.getId());
			if (ms == null) {
				ms = new MenuStrut(menu);
			}

			if (!StringUtils.isEmpty(menu.getPid())) {
				MenuStrut pms = menuMap.get(menu.getPid());
				if (pms != null) {
					pms.getSubMenus().add(ms);
					continue;
				}
			}

			menuMap.put(menu.getId(), ms);
		}

		for (Iterator<MenuStrut> it = menuMap.values().iterator(); it.hasNext();) {
			MenuStrut ms = it.next();

			sb.append(build(ms, basePath));
		}

		return sb.toString();
	}

	private static String build(MenuStrut ms, String basePath) {
		StringBuilder sb = new StringBuilder();
		Menu menu = ms.getMenu();
		List<MenuStrut> subMenus = ms.getSubMenus();

		sb.append("<li>");
		if (subMenus.size() == 0) {
			sb.append(String.format("<a href=\"%s%s\">", basePath, menu.getUrl()));
			if (!StringUtils.isEmpty(menu.getIcon()))
				sb.append(String.format("<i class=\"%s\"></i>", menu.getIcon()));
			if (!StringUtils.isEmpty(menu.getPid()))
				sb.append(menu.getName());
			else
				sb.append(String.format("<span class=\"title\">%s</span>",
						menu.getName()));
			sb.append("</a>");
		} else {
			sb.append("<a href=\"javascript:;\">");
			if (!StringUtils.isEmpty(menu.getIcon()))
				sb.append(String.format("<i class=\"%s\"></i>", menu.getIcon()));
			sb.append(String.format("<span class=\"title\">%s</span>",
					menu.getName()));
			sb.append("<span class=\"arrow \"></span>");
			sb.append("</a>");

			sb.append("<ul class=\"sub-menu\">");

			for (MenuStrut sms : subMenus)
				sb.append(build(sms, basePath));

			sb.append("</ul>");
		}

		sb.append("</li>");

		return sb.toString();
	}
}
