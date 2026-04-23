package com.kh.delivery.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.delivery.common.Template;
import com.kh.delivery.dao.OrderDao;
import com.kh.delivery.dto.OrderDto;
import com.kh.delivery.vo.MenuVo;

public class OrderService {
	
	private OrderDao orderDao = new OrderDao();
	private MenuVo menu = new MenuVo();
	
	public int insertOrder(OrderDto order) {
		try (SqlSession session = Template.getSqlConnection()){
		int orderResult;
			orderResult = orderDao.insertOrder(session, order);
				if(orderResult>0) {
					session.commit();
					session.close();
				} else{
					session.rollback();
					session.close();
				}	
				return orderResult;	} catch
			(Exception e) {
					e.printStackTrace();
					return 0;
				}
	}
	
	public boolean checkSoldOut(int menuNo) {
		SqlSession session = Template.getSqlConnection();
		int result = orderDao.checkSoldOut(session, menuNo);
        return result == 1; 
	}
	
	public List<OrderDto> selectMyOrder(int userNo) {
		SqlSession session = Template.getSqlConnection();
		List<OrderDto> orderList = orderDao.selectMyOrder(session, userNo);
	    if (orderList == null) {
	    	System.out.println("주문내역이 없습니다.");
	    }
	    return orderList;
	}
	
	public boolean selectSoldout(int restNo) {
		SqlSession session = Template.getSqlConnection();
	    int menu = orderDao.selectSoldout(session, restNo);
	    if (menu>0) {
	        return menu == 1; 
	    }
	    return menu == 0;
	}
	
	public int orderCancel(int order) {
		SqlSession session = Template.getSqlConnection();
		int cancelAction = orderDao.orderCancel(session, order);
		
		if(cancelAction > 0) {
			session.commit();
			session.close();
		} else{
			session.rollback();
			session.close();}
		
		return cancelAction;
	}
}
