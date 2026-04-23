package com.kh.delivery.controller;

import java.util.List;

import com.kh.delivery.dto.OrderDto;
import com.kh.delivery.service.OrderService;
import com.kh.delivery.vo.MenuVo;

public class OrderController {
	private OrderService orderService = new OrderService();
	
	public int insertOrder(OrderDto order) {
		return orderService.insertOrder(order);
	}
	
	public boolean checkSoldOut(int menuNo) {
	    return orderService.checkSoldOut(menuNo);  
	}
	
	public List<OrderDto> selectMyOrder(int userNo) {
		return orderService.selectMyOrder(userNo); 
	}
	
	public boolean selectSoldout(int restNo) {
		return orderService.selectSoldout(restNo);
	}
	
	public int orderCancel(int orderNo) {
		return orderService.orderCancel(orderNo);
		
	}

}
