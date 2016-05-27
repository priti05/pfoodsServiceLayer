package com.pFoods.sl.restaurant.To;

import java.util.List;

import com.pFoods.sl.restaurant.vo.RestaurantImageVO;

public class RestaurantImageInsertionRequestTo {
	
	private String rid;
	
	private List<RestaurantImageVO> RestaurantImageVOList;
	
	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public List<RestaurantImageVO> getRestaurantImageVOList() {
		return RestaurantImageVOList;
	}

	public void setRestaurantImageVOList(List<RestaurantImageVO> restaurantImageVOList) {
		RestaurantImageVOList = restaurantImageVOList;
	}
	
	
	
	

}
