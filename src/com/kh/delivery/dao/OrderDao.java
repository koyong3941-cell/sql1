package com.kh.delivery.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.delivery.dto.OrderDto;

public class OrderDao {
	public int insertOrder(SqlSession session, OrderDto order) {
		return session.insert("order-mapper.insertOrder", order);
	}
	
	public int checkSoldOut(SqlSession session, int menuNo) {
	    return session.selectOne("order-mapper.checkSoldOut", menuNo);
	}
	
	public List<OrderDto> selectMyOrder(SqlSession session, int userNo){
		return session.selectList("order-mapper.selectMyOrder", userNo);
	}
	
	public int selectSoldout(SqlSession session, int restNo) {
	    return session.selectOne("order-mapper.selectSoldout", restNo);
	}
	
	public int orderCancel(SqlSession session, int order) {
		return session.update("order-mapper.orderCancel", order);
	}

}
