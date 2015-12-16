package cn.zmvision.ccm.factory.base.bo;

import com.github.miemiedev.mybatis.paginator.domain.PageList;

@SuppressWarnings("serial")
/**
 * Datatable返回结果
 * @author xuchaoguo
 *
 */
public class PageResult extends JsonResult {
	private int recordsTotal;
	private int recordsFiltered;
	private int draw;

	public <T> PageResult(PageMeta pageMeta, PageList<T> list) {
		super(list);
		recordsTotal = list.getPaginator().getTotalCount();
		recordsFiltered = recordsTotal;
		draw = pageMeta.getDraw();
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

}
