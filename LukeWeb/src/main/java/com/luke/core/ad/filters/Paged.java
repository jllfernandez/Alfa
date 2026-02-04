package com.luke.core.ad.filters;

import lombok.Getter;
import lombok.Setter;

public class Paged {

	@Getter
	@Setter
	private int page;

	@Getter
	@Setter
	private int pageSize;

	@Getter
	@Setter
	private int totalPages;

	@Getter
	@Setter
	private int aft;

	@Getter
	@Setter
	private int bef;

	@Getter
	@Setter
	private String orderField;

	@Getter
	@Setter
	private String orderType;

}
