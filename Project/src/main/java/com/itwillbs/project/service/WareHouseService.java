package com.itwillbs.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.project.mapper.WareHouseMapper;
import com.itwillbs.project.vo.WareHouseVO;

@Service
public class WareHouseService {
@Autowired
private WareHouseMapper mapper;

//------------창고 등록 작업-----------------
public boolean insertWh(WareHouseVO vo) {
	return mapper.insertWh(vo);
}//insertWh()끝
//------------창고 목록 작업-----------------
public List<WareHouseVO> getwhList() {
	return mapper.getwhList();
}//getwhList()끝
//------------창고 상세내용 작업-----------------
public WareHouseVO getWarehouse(String wh_cd) {
	return mapper.getWarehouse(wh_cd);
}//getWarehouse()끝
//------------창고 상세내용(이룸) 작업-----------------
public WareHouseVO getWarehouseName(String wh_cd, String wh_name) {
	return mapper.getWarehouseName(wh_cd,wh_name);
}//getWarehouseName()끝
//------------창고 수정 작업-----------------
public void whModify(WareHouseVO vo,String wh_cd) {
	mapper.whModify(vo,wh_cd);
}
public int getDB_wh_cd(String wh_cd) {
	return mapper.getDB_wh_cd(wh_cd);
}
public void WhAreaInsert(WareHouseVO vo) {
	mapper.WhAreaInsert(vo);
}
public List<WareHouseVO> getwhAreaList(String wh_cd) {
	return mapper.getwhAreaList(wh_cd);
}
public void WhAreaDelte(String wh_area_cd) {
	mapper.WhAreaDelte(wh_area_cd);
}
public List<WareHouseVO> getwhAreaLocInList(String wh_area_cd) {
	return mapper.getwhAreaLocInList(wh_area_cd);
}
public void WhLocAreaInsert(WareHouseVO vo) {
	mapper.WhLocAreaInsert(vo);
}
public void WhLocAreaDelte(int wh_loc_in_area_cd) {
	mapper.WhLocAreaDelte(wh_loc_in_area_cd);
}
public void WhAreaupdate(WareHouseVO vo) {
	mapper.WhAreaupdate(vo);
}
public void WhAreaLocupdate(WareHouseVO vo) {
	mapper.WhAreaLocupdate(vo);
}

public List<WareHouseVO> getStockList(WareHouseVO vo,int startRow,int listLimit) {
	// TODO Auto-generated method stub
	return mapper.getStockList(vo,startRow,listLimit);
}
public int getStockListCount(WareHouseVO vo) {
	return mapper.getStockListCount(vo);
}
public int getWh_loc_in_area_cd() {
	return mapper.getWh_loc_in_area_cd();
}
public int getArea_cd() {
	return mapper.getArea_cd();
}
public List<WareHouseVO> getStockKeywordList(String keyword, int startRow, int listLimit) {
	return mapper.getStockKeywordList(keyword,startRow,listLimit);
}
public int getStockListCount(String keyword) {
	return mapper.getStockKeywordCount(keyword);
}
	
}
