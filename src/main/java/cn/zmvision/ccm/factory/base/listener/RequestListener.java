package cn.zmvision.ccm.factory.base.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * 设置basePath
 * 
 * @author xuchaoguo
 * 
 */
public class RequestListener implements ServletRequestListener {
	public final static String BASE_PATH = "basePath";

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		HttpServletRequest request = (HttpServletRequest) sre
				.getServletRequest();
		String path = request.getContextPath();
		String ctx = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		request.setAttribute(BASE_PATH, ctx);
	}

}
