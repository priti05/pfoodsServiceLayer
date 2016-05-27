package com.pFoods.sl.restaurant.vo;

import com.pFoods.dbo.restaurant.imageModel.Images.Profile;

public class RestaurantImageVO {
	
	private byte[] image;
	
	private Profile profileImage;

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Profile getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(Profile profileImage) {
		this.profileImage = profileImage;
	}
	
	

}
