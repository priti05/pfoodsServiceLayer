package com.pFoods.sl.registration;

import java.util.List;

import com.pFoods.dbo.userProfile.TO.NewSecurityCodeRequestTO;
import com.pFoods.dbo.userProfile.TO.RetrieveUserAddressListRequestTO;
import com.pFoods.dbo.userProfile.TO.VerifySecurityCodeTO;
import com.pFoods.sl.registration.vo.AddressVO;
import com.pFoods.sl.registration.vo.UserProfileVO;

public interface IRegistrationSL {
	
	public String doRegister(UserProfileVO userProfileVO);
	public long updateAddress(String userID, AddressVO adrVO);
	public boolean userIdAvailibilty(String userID);
	public List<com.pFoods.dbo.userProfile.vo.AddressVO> retriveUserAddressesSL(RetrieveUserAddressListRequestTO retrieveUserAddressListRequestTO);
	public boolean phoneNumberAvailibilty(String phoneNumber);
	public boolean verifySecurityCodeSL(VerifySecurityCodeTO verifySecurityCodeTO);	
	public String newSecurityCodeSL(NewSecurityCodeRequestTO wewSecurityCodeRequestTO);
}
