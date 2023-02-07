package com.itwillbs.project.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.server.header.XFrameOptionsServerHttpHeadersWriter.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.project.service.BuyerService;
import com.itwillbs.project.service.StockService;
import com.itwillbs.project.vo.BuyerVo;
import com.itwillbs.project.vo.EmpVo;
import com.itwillbs.project.vo.StockVo;

@Controller
public class StockController {
	
	@Autowired
	private StockService service;
	
	//-----------재고 목록 이동 (기본페이지)------------
	@GetMapping(value = "StockList.st")
	public String stock(@ModelAttribute StockVo stock, Model model) {
		List<StockVo> stockList = service.getStockList();
		model.addAttribute("stockList",stockList);
		return "stock/stock_list";
	}//stock 끝
	
	//--------재고 조정 작업---------
	@PostMapping(value = "StockUpdate.st")
	public void stockUpdate(
			@RequestParam("updateStockNum") int update_qty,
			@RequestParam("stock_cd") int stock_cd,
			HttpServletResponse response
			) {
		
		int updateCount = service.stockUpdate(update_qty, stock_cd);
		if(updateCount > 0) {
			try {
				response.getWriter().print(updateCount); //emailcheck 값을 보내는 작업
			} catch (Exception e) {
				e.printStackTrace();
			}
		}//if 끝
	}//stockUpdate 끝
	
	
	//---------재고 이동----------
	@PostMapping(value = "stockMove.st")
	public void stockMove(
			@RequestParam("current_stock_cd") int current_stock_cd, //현재 재고번호
			@RequestParam("move_stock_cd") int move_stock_cd, // 이동할 재고번호
			@RequestParam("move_wh_loc_in_area") int move_wh_loc_in_area, // 이동할 구역
			@RequestParam("move_stock_num") int move_stock_num, //이동할 개수
			HttpServletResponse response
			) {
		
		int moveCount = service.stockMove(current_stock_cd, move_stock_cd, move_wh_loc_in_area, move_stock_num);
		if(moveCount > 0) {
			try {
				response.getWriter().print(moveCount); //emailcheck 값을 보내는 작업
			} catch (Exception e) {
				e.printStackTrace();
			}
		}//if 끝
		
	}//stockMove 끝
	
	//----------재고 키워드 조회------------
	@GetMapping(value = "StockListJson", produces = "application/json; charset=utf-8")
	public void listJson_stock(
			@RequestParam(defaultValue = "") String keyword,
			Model model,
			HttpServletResponse response){
	
		List<StockVo> stockList = service.getStockListSearch(keyword);
		// ---------------------------------------------------------------------------
		// 자바 데이터를 JSON 형식으로 변환하기
		// => org.json 패키지의 JSONObject 클래스를 활용하여 JSON 객체 1개를 생성하고
		//    JSONArray 클래스를 활용하여 JSONObject 객체 복수개에 대한 배열 생성
		// 0. JSONObject 객체 복수개를 저장할 JSONArray 클래스 인스턴스 생성
		JSONArray jsonArray = new JSONArray();
		
		// 1. List 객체 크기만큼 반복
		for(StockVo stock : stockList) {
			// 2. JSONObject 클래스 인스턴스 생성
			// => 파라미터 : VO(Bean) 객체(멤버변수 및 Getter/Setter, 기본생성자 포함)
			JSONObject jsonObject = new JSONObject(stock);
	//		System.out.println(jsonObject);
			
			// 3. JSONArray 객체의 put() 메서드를 호출하여 JSONObject 객체 추가
			jsonArray.put(jsonObject);
		}
		
		try {
			// 생성된 JSON 객체를 활용하여 응답 데이터를 직접 생성 후 웹페이지에 출력
			// response 객체의 setCharacterEncoding() 메서드로 출력 데이터 인코딩 지정 후
			// response 객체의 getWriter() 메서드로 PrintWriter 객체를 리턴받아
			// PrintWriter 객체의 print() 메서드를 호출하여 응답데이터 출력
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(jsonArray); // toString() 생략됨
			System.out.println(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}//listJson_stock 끝
	
		
}//BuyerController 끝
