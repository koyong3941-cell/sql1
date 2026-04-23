package com.kh.delivery.vo;

public class MenuVo {
	private int menuNo;
	private int restNo;
	private String menuName;
	private int price;
	private boolean soldOut;// 쿼리 단에서 처리 예정
	
	
	@Override
	public String toString() {
		return "MenuVo [menuNo=" + menuNo + ", restNo=" + restNo + ", menuName=" + menuName + ", price=" + price
				+ ", soldOut=" + soldOut + "]";		
	}

	public int getMenuNo() {
		return menuNo;
	}

	public int getRestNo() {
		return restNo;
	}

	public String getMenuName() {
		return menuName;
	}

	public int getPrice() {
		return price;
	}

	public boolean soldOut() {
		return soldOut;
	}
	
	public void setSoldOut(boolean soldOut) {
		this.soldOut = soldOut;
	}
	
	public MenuVo() {}

	public MenuVo(int menuNo, int restNo, String menuName, int price, boolean soldOut) {
		super();
		this.menuNo = menuNo;
		this.restNo = restNo;
		this.menuName = menuName;
		this.price = price;
		this.soldOut = soldOut;
	}
	
	/*
	 *  SELECT 
    		REST_NO,
        	REST_NAME,
    	CASE 
    		WHEN SOLD_OUT = 'Y' THEN 1 ELSE 0 END AS SOLD_OUT
		FROM 
			RESTAURANT
	 * 
	 * 
	 * 
	 */

}
