package cn.zmvision.ccm.factory.base;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public abstract class BaseService<MODEL, EXAMPLE> {
	/**
	 * 按条件分页查询
	 * 
	 * @param example
	 * @param pageBounds
	 * @return
	 */
	public abstract PageList<MODEL> queryByPage(EXAMPLE example,
			PageBounds pageBounds);

	/**
	 * 按条件查询所有记录
	 * 
	 * @param example
	 * @return
	 */
	public abstract List<MODEL> queryAllByExample(EXAMPLE example);

	/**
	 * 按主键查询数据
	 * 
	 * @param id
	 * @return
	 */
	public abstract MODEL queryById(Integer id);

	/**
	 * 保存数据：更新或新增
	 * 
	 * @param model
	 * @return
	 */
	public abstract boolean save(MODEL model);

	/**
	 * 删除数据
	 * 
	 * @param id
	 * @return
	 */
	public abstract boolean deleteById(Integer id);
}
