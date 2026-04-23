package com.kh.delivery.vo;

public class RestaurantVo {
	private final int restNo;
	private final String restName;
	private final String category;
	private final int minPrice;
	private final int deliveryFee;
	
	@Override
	public String toString() {
		return "RestaurantVo [restNo=" + restNo + ", restName=" + restName + ", category=" + category + ", minPrice="
				+ minPrice + ", dliveryPrice=" + deliveryFee + "]";
	}
	
	public int getRestNo() {
		return restNo;
	}

	public String getRestName() {
		return restName;
	}


	public String getCategory() {
		return category;
	}


	public int getMinPrice() {
		return minPrice;
	}

	public int getDeliveryFee() {
		return deliveryFee;
	}
	
	
	public RestaurantVo(int restNo, String restName, String category, int minPrice, int deliveryFee) {
		super();
		this.restNo = restNo;
		this.restName = restName;
		this.category = category;
		this.minPrice = minPrice;
		this.deliveryFee = deliveryFee;
	}

}
