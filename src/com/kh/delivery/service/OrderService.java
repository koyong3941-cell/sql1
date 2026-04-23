package com.kh.delivery.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.delivery.common.Template;
import com.kh.delivery.dao.OrderDao;
import com.kh.delivery.dto.OrderDto;
import com.kh.delivery.vo.MenuVo;

public class OrderService {
	private SqlSession session = Template.getSqlConnection();
	private OrderDao orderDao = new OrderDao();
	private MenuVo menu = new MenuVo();
	
	public int insertOrder(OrderDto order) {
		int orderResult;
			orderResult = orderDao.insertOrder(session, order);
			if(orderResult>0) {
				session.commit();
				// session.close();
			} else{
				session.rollback();
				// session.close();
			}	
			return orderResult;	
	}
	
	public boolean checkSoldOut(int menuNo) {
	    int menu = orderDao.checkSoldOut(session, menuNo);
	    if (menu>0) {
	        return menu == 1; 
	    }
	    return menu == 0;
	}
	
	public List<OrderDto> selectMyOrder(int userNo) {
		List<OrderDto> orderList = orderDao.selectMyOrder(session, userNo);
	    if (orderList == null) {
	    	System.out.println("주문내역이 없습니다.");
	    }
	    return orderList;
	}
	
	public boolean selectSoldout(int restNo) {
	    int menu = orderDao.selectSoldout(session, restNo);
	    if (menu>0) {
	        return menu == 1; 
	    }
	    return menu == 0;
	}
}
