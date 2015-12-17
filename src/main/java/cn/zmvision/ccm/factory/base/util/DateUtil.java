package cn.zmvision.ccm.factory.base.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private final static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd hh:mm:ss");

	public static String getNow() {
		return getDateString(new Date());
	}

	public static String getDateString(Date dt) {
		return sdf.format(dt);
	}
}
