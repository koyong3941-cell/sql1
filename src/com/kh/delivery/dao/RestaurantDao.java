package com.kh.delivery.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.delivery.vo.MenuVo;
import com.kh.delivery.vo.RestaurantVo;

public class RestaurantDao {
	
	public List<RestaurantVo> selectAllRestaurant(SqlSession session){
		return session.selectList("restaurant-mapper.selectAllRestaurant");
	}
	
	public List<RestaurantVo> selectByCategory(SqlSession session, String category){
		return session.selectList("restaurant-mapper.selectByCategory", category);
	}
	
	public List<RestaurantVo> selectAllRestaurantProxy(SqlSession session){
		return session.selectList("restaurant-mapper.selectAllRestaurantProxy");
	}
	
	public List<MenuVo> selectDetail(SqlSession session, int restNo){
		return session.selectList("restaurant-mapper.selectDetail", restNo);
	}
	
	public int checkSoldOut(SqlSession session, int restNo){
		return session.selectOne("restaurant-mapper.checkSoldOut", restNo);
	}
	
}
