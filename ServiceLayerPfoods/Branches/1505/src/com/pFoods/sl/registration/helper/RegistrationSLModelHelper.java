package com.pFoods.sl.registration.helper;

import org.springframework.beans.factory.annotation.Autowired;

import com.pFoods.dbo.userProfile.loginModal.LoginInfo;
import com.pFoods.dbo.userProfile.profileModal.Address;
import com.pFoods.dbo.userProfile.profileModal.UserProfile;

public class RegistrationSLModelHelper {
	
	@Autowired
	private LoginInfo loginInfoModel;
	@Autowired
	private UserProfile userProfileModel;
	@Autowired
	private Address addressModel;
	
	public LoginInfo getLoginInfoModel() {
		return loginInfoModel;
	}
	public void setLoginInfoModel(LoginInfo loginInfoModel) {
		this.loginInfoModel = loginInfoModel;
	}
	public UserProfile getUserProfileModel() {
		return userProfileModel;
	}
	public void setUserProfileModel(UserProfile userProfileModel) {
		this.userProfileModel = userProfileModel;
	}
	public Address getAddressModel() {
		return addressModel;
	}
	public void setAddressModel(Address addressModel) {
		this.addressModel = addressModel;
	}
	
	
	
	
	
}
