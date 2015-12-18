package cn.zmvision.ccm.factory.test;

import java.io.IOException;
import java.net.SocketException;
import java.util.List;

import org.junit.Assert;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cn.zmvision.ccm.factory.base.net.ftp.FtpUtil;
import cn.zmvision.ccm.factory.base.util.TestSupport;

public class FtpTest extends TestSupport {
	private Logger logger = Logger.getLogger(getClass());

	@Before
	public void testBefore() {
	}

	@After
	public void testAfter() {
	}

	@Test
	public void test() {
		logger.info("test ftp connection.");

		FtpUtil ftpUtil = new FtpUtil();
		try {
			ftpUtil.connectServer("114.215.240.244", 21, "wlline01", "wlline01", null);
			List<String> list = ftpUtil.getFileList("/");
			Assert.assertTrue(list.size() > 0);
		} catch (SocketException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}
}
