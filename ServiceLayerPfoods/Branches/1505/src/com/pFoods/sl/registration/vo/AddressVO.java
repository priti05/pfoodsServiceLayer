package com.pFoods.sl.registration.vo;

import com.pFoods.dbo.userProfile.profileModal.Address.AddressType;

public class AddressVO {
 
	private String street_number;
	private String apt_number;
	private String street;
	private String town;
	private String State;
	private String zip;
	private AddressType adrType;
	
	public AddressType getAdrType() {
		return adrType;
	}
	public void setAdrType(AddressType adrType) {
		this.adrType = adrType;
	}
	public String getStreet_number() {
		return street_number;
	}
	public void setStreet_number(String street_number) {
		this.street_number = street_number;
	}
	public String getApt_number() {
		return apt_number;
	}
	public void setApt_number(String apt_number) {
		this.apt_number = apt_number;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
		
}
