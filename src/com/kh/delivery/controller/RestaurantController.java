package com.kh.delivery.controller;

import java.util.List;

import com.kh.delivery.service.RestaurantService;
import com.kh.delivery.vo.MenuVo;
import com.kh.delivery.vo.RestaurantVo;

public class RestaurantController {
	RestaurantService restaurantService = new RestaurantService();
	
	public List<RestaurantVo> selectAllRestaurant(){
		return restaurantService.selectAllRestaurant();
	}
	
	public List<RestaurantVo> selectByCategory(String category) {
		return restaurantService.selectByCategory(category);
	}
	
	public List<RestaurantVo> selectAllRestaurantProxy(){
		return restaurantService.selectAllRestaurantProxy();
	}
	
	public List<MenuVo> selectDetail(int restNo){
		return restaurantService.selectDetail(restNo);
	}
	
	public boolean checkSoldOut(int restNo) {
		return restaurantService.checkSoldOut(restNo);
	}
}
