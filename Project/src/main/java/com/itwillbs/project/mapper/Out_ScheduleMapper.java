package com.itwillbs.project.mapper;

import java.util.List;

import com.itwillbs.project.vo.ProductVO;

public interface Out_ScheduleMapper {

	List<ProductVO> selectProList(String keyword);
	
}