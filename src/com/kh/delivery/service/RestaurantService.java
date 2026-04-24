package com.kh.delivery.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.delivery.common.Template;
import com.kh.delivery.dao.RestaurantDao;
import com.kh.delivery.vo.MenuVo;
import com.kh.delivery.vo.RestaurantVo;

public class RestaurantService {
	RestaurantDao restaurantDao = new RestaurantDao();
		
	public List<RestaurantVo> selectAllRestaurant(){
		SqlSession session = Template.getSqlConnection();
		List<RestaurantVo> restaurantList = restaurantDao.selectAllRestaurant(session);
			if(restaurantList == null) {
				System.out.println("비정상적인 접근입니다.");
				session.close();
					}	
			return restaurantList;
	}
	
	public List<RestaurantVo> selectByCategory(String category) {
		SqlSession session = Template.getSqlConnection();
		List<RestaurantVo> restaurantList = restaurantDao.selectByCategory(session, category);
			if(restaurantList == null) {
				System.out.println("비정상적인 접근입니다.");
				session.close();
					}
				return restaurantList;
	}
	
	public List<RestaurantVo> selectAllRestaurantProxy() {
		SqlSession session = Template.getSqlConnection();
		List<RestaurantVo> restaurantList = restaurantDao.selectAllRestaurantProxy(session);
			if(restaurantList == null) {
				System.out.println("비정상적인 접근입니다.");
				session.close();
					}
				return restaurantList;
	}
	
	public List<MenuVo> selectDetail(int restNo) {
		SqlSession session = Template.getSqlConnection();
		List<MenuVo> menuList = restaurantDao.selectDetail(session, restNo);
			if(menuList == null) {
				System.out.println("비정상적인 접근입니다.");
				session.close();
					}
				return menuList;
	}
	
	public boolean checkSoldOut(int restNo) {
		SqlSession session = Template.getSqlConnection();
	    int menu = restaurantDao.checkSoldOut(session, restNo);
	    if (menu>0) {
	    	session.close();
	        return menu == 1; 
	    }
	    session.close();
	    return menu == 0;
	}
	
	
			
}


