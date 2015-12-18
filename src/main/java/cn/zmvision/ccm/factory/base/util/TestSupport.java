package cn.zmvision.ccm.factory.base.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * For Junit Test
 * @author xuchaoguo
 *
 */
@ContextConfiguration(locations = { "classpath*:spring.xml" })
public class TestSupport extends AbstractJUnit4SpringContextTests {
	protected long startTime;
	protected long endTime;

	public final static SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss.SSSS");

	/**
	 * 记录 开始运行时间
	 *
	 * @return
	 */
	protected long start() {
		this.startTime = System.currentTimeMillis();
		return startTime;
	}

	/**
	 * 记录 结束运行时间
	 *
	 * @return
	 */
	protected long end() {
		this.endTime = System.currentTimeMillis();
		this.log();
		return endTime;
	}

	/**
	 * 输出记录
	 */
	protected void log() {
		String text = df.format(new Date(this.startTime)) + " - " + df.format(new Date(this.endTime)) + "\t"
				+ (this.endTime - this.startTime) / 1000.0 + "s";
		logger.info(text);
	}
}