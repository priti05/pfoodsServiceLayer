package com.pFoods.sl.restaurant.To;

import org.springframework.beans.factory.annotation.Autowired;

import com.pFoods.sl.restaurant.vo.RestaurantAddressVO;
import com.pFoods.sl.restaurant.vo.RestaurantLoginVO;
import com.pFoods.sl.restaurant.vo.RestaurantProfileVO;

public class RestaurantRegistrationSLRequestTo {
	
	@Autowired
	private RestaurantLoginVO restaurantLoginVO; //not null
	
	@Autowired
	private RestaurantProfileVO restaurantProfileVO; //not null
	
	@Autowired
	private RestaurantAddressVO restaurantAddressVO; //not null
	
	private int tasteId; 
	
	public RestaurantLoginVO getRestaurantLoginVO() {
		return restaurantLoginVO;
	}
	public void setRestaurantLoginVO(RestaurantLoginVO restaurantLoginVO) {
		this.restaurantLoginVO = restaurantLoginVO;
	}
	public RestaurantProfileVO getRestaurantProfileVO() {
		return restaurantProfileVO;
	}
	public void setRestaurantProfileVO(RestaurantProfileVO restaurantProfileVO) {
		this.restaurantProfileVO = restaurantProfileVO;
	}
	public RestaurantAddressVO getRestaurantAddressVO() {
		return restaurantAddressVO;
	}
	public void setRestaurantAddressVO(RestaurantAddressVO restaurantAddressVO) {
		this.restaurantAddressVO = restaurantAddressVO;
	}
	public int getTasteId() {
		return tasteId;
	}
	public void setTasteId(int tasteId) {
		this.tasteId = tasteId;
	}
	
	
}
