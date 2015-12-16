package cn.zmvision.ccm.factory.base.bo;

import org.springframework.util.StringUtils;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public class PageMeta {
	private int start = 0;
	private int length = 10;
	private int draw;
	private String sortingOrder;// "age.asc,gender.desc"

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public String getSortingOrder() {
		return sortingOrder;
	}

	public void setSortingOrder(String sortingOrder) {
		this.sortingOrder = sortingOrder;
	}

	public PageBounds getPageBounds() {
		PageBounds bounds = new PageBounds(1 + start / length, length);
		bounds.setContainsTotalCount(true);
		bounds.setAsyncTotalCount(false);

		if (!StringUtils.isEmpty(sortingOrder)) {
			bounds.setOrders(Order.formString(sortingOrder));
		}

		return bounds;
	}
}
