package com.spring.javawspring.pagenation;

import lombok.Data;

@Data
public class PageVO {
		private int pag;
		private int pageSize;
		private int totRecCnt;
		private int totPage;
		private int startIndexNo;
		private int curScrStartNo;
		private int blockSize;
		private int curBlock;
		private int lastBlock;
		
		// 필요한 것들 나머지 추가
		private String part;
	
}
