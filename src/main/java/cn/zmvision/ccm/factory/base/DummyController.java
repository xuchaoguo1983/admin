package cn.zmvision.ccm.factory.base;

import cn.zmvision.ccm.factory.base.bo.JsonResult;
import cn.zmvision.ccm.factory.base.bo.PageMeta;
import cn.zmvision.ccm.factory.base.bo.PageResult;

public class DummyController extends BaseController<PageMeta, Object> {
	@Override
	public PageResult queryByPage(PageMeta input) {
		throw new RuntimeException("method is not supported.");
	}

	@Override
	public JsonResult save(Object record) {
		throw new RuntimeException("method is not supported.");
	}

	@Override
	public JsonResult delete(Integer id) {
		throw new RuntimeException("method is not supported.");
	}

	@Override
	public JsonResult query(Integer id) {
		throw new RuntimeException("method is not supported.");
	}

}
