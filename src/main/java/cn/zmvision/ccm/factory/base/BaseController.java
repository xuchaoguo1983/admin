package cn.zmvision.ccm.factory.base;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zmvision.ccm.factory.base.bo.JsonResult;
import cn.zmvision.ccm.factory.base.bo.PageMeta;
import cn.zmvision.ccm.factory.base.bo.PageResult;

public abstract class BaseController<INPUT extends PageMeta, MODEL extends Object> {

	/**
	 * 进入模块界面，JSP文件名路径必须和API调用URL完全吻合，否则需要自行实现
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		RequestMapping[] mapping = this.getClass().getAnnotationsByType(
				RequestMapping.class);
		if (mapping.length != 1) {
			throw new RuntimeException(
					"Controller must be only one RequestMapping annotation.");
		}

		return mapping[0].value()[0];
	}

	/**
	 * 分页查询数据列表
	 * 
	 * @param input
	 *            查询条件对象
	 * @return
	 */
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public abstract PageResult queryByPage(INPUT input);

	/**
	 * 查询数据
	 * 
	 * @param id
	 *            主键ID
	 * @return
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public abstract JsonResult query(Integer id);

	/**
	 * 新增或更新数据
	 * 
	 * @param record
	 *            数据对象
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public abstract JsonResult save(MODEL record);

	/**
	 * 删除数据
	 * 
	 * @param id
	 *            主键ID
	 * @return
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public abstract JsonResult delete(Integer id);
}
