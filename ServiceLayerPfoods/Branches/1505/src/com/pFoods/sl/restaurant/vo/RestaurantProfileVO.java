package com.pFoods.sl.restaurant.vo;

import com.pFoods.dbo.restaurant.menuModel.Taste;
import com.pFoods.dbo.restaurant.profileModal.RestaurantProfile.Deliever;
import com.pFoods.dbo.restaurant.profileModal.RestaurantProfile.PickUp;
import com.pFoods.dbo.restaurant.profileModal.RestaurantProfile.ReserveTable;

public class RestaurantProfileVO {
	
	private String name; //Not Null
	private Taste taste; //Not Null
	private String phoneNumber; //Not Null
	private Deliever delieverOption; //Not Null 
	private PickUp pickUpOption; //Not Null 
	private ReserveTable reserveTableOption; //Not Null 
	private String delieveryMinimum; //Not Null 
	private String delieveryCharge; //Not Null
	private int rating; //this will be updated later when user rate it
	private String description; //Not Null
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Taste getTaste() {
		return taste;
	}
	public void setTaste(Taste taste) {
		this.taste = taste;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Deliever getDelieverOption() {
		return delieverOption;
	}
	public void setDelieverOption(Deliever delieverOption) {
		this.delieverOption = delieverOption;
	}
	public PickUp getPickUpOption() {
		return pickUpOption;
	}
	public void setPickUpOption(PickUp pickUpOption) {
		this.pickUpOption = pickUpOption;
	}
	public ReserveTable getReserveTableOption() {
		return reserveTableOption;
	}
	public void setReserveTableOption(ReserveTable reserveTableOption) {
		this.reserveTableOption = reserveTableOption;
	}
	public String getDelieveryMinimum() {
		return delieveryMinimum;
	}
	public void setDelieveryMinimum(String delieveryMinimum) {
		this.delieveryMinimum = delieveryMinimum;
	}
	public String getDelieveryCharge() {
		return delieveryCharge;
	}
	public void setDelieveryCharge(String delieveryCharge) {
		this.delieveryCharge = delieveryCharge;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
