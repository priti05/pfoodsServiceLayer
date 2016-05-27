package com.pFoods.sl.registration;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pFoods.dbo.userProfile.TO.NewSecurityCodeRequestTO;
import com.pFoods.dbo.userProfile.TO.RetrieveUserAddressListRequestTO;
import com.pFoods.dbo.userProfile.TO.VerifySecurityCodeTO;
import com.pFoods.sl.registration.constant.RegistrationSLConstants;
import com.pFoods.sl.registration.helper.RegistrationSLHelper;
import com.pFoods.sl.registration.vo.AddressVO;
import com.pFoods.sl.registration.vo.UserProfileVO;
import com.pFoods.sl.result.ResultSLHelper;

public class RegistrationSL implements IRegistrationSL{

	public AbstractApplicationContext getAppContext(){
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(RegistrationSLConstants.SpringXML);
		context.registerShutdownHook();
		return context;		
	}
	
	@Override
	public String doRegister(UserProfileVO userProfileVO){
		String secCode;
		try{
			AbstractApplicationContext context = getAppContext();
			RegistrationSLHelper registrationSLHelperBean  = (RegistrationSLHelper)context.getBean("registrationSLHelperBean");
			secCode = registrationSLHelperBean.createRegisterModel(userProfileVO, context);
		}catch(Exception e){
			secCode = null;
			ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RegistrationSLConstants.FAILURE; ResultSLHelper.ERROR=RegistrationSLConstants.REG_SLGEN_111;
		}
		return secCode;
	}

	@Override
	public long updateAddress(String userID, AddressVO adrVO) {
		long addressID = 0;
		try{
			AbstractApplicationContext context = getAppContext();
			RegistrationSLHelper registrationSLHelperBean  = (RegistrationSLHelper)context.getBean("registrationSLHelperBean");
			addressID = registrationSLHelperBean.updateAddressHelper(userID, adrVO, context);
		}catch(Exception e){
			ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RegistrationSLConstants.FAILURE; ResultSLHelper.ERROR=RegistrationSLConstants.REG_SLGEN_111;
		}
		return addressID;
	}

	@Override
	public boolean userIdAvailibilty(String userID) {
		boolean flag = false;
		try{
			AbstractApplicationContext context = getAppContext();
			RegistrationSLHelper registrationSLHelperBean  = (RegistrationSLHelper)context.getBean("registrationSLHelperBean");
			flag = registrationSLHelperBean.checkUserIDAvailable(userID, context);
		}catch(Exception e){
			ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RegistrationSLConstants.FAILURE; ResultSLHelper.ERROR=RegistrationSLConstants.REG_SLGEN_111;
		}
		return flag;
	}

	@Override
	public boolean phoneNumberAvailibilty(String phoneNumber) {
		boolean flag = false;
		try{
			AbstractApplicationContext context = getAppContext();
			RegistrationSLHelper registrationSLHelperBean  = (RegistrationSLHelper)context.getBean("registrationSLHelperBean");
			flag = registrationSLHelperBean.checkPhoneNumberAvailable(phoneNumber, context);
		}catch(Exception e){
			ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RegistrationSLConstants.FAILURE; ResultSLHelper.ERROR=RegistrationSLConstants.REG_SLGEN_111;
		}
		return flag;
	}

	@Override
	public boolean verifySecurityCodeSL(VerifySecurityCodeTO verifySecurityCodeTO) {
		boolean flag = false;
		try{
			ResultSLHelper.clearResult();
			AbstractApplicationContext context = getAppContext();
			RegistrationSLHelper registrationSLHelperBean  = (RegistrationSLHelper)context.getBean("registrationSLHelperBean");
			flag = registrationSLHelperBean.verifySecurityCodeSLHelper(verifySecurityCodeTO, context);
		}catch(Exception e){
			ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RegistrationSLConstants.FAILURE; ResultSLHelper.ERROR=RegistrationSLConstants.REG_SLGEN_111;
		}
		return flag;
	}

	@Override
	public String newSecurityCodeSL(NewSecurityCodeRequestTO wewSecurityCodeRequestTO) {
		String secCode = null;
		try{
			ResultSLHelper.clearResult();
			AbstractApplicationContext context = getAppContext();
			RegistrationSLHelper registrationSLHelperBean  = (RegistrationSLHelper)context.getBean("registrationSLHelperBean");
			secCode = registrationSLHelperBean.newSecurityCodeSLHelper(wewSecurityCodeRequestTO, context);
		}catch(Exception e){
			ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RegistrationSLConstants.FAILURE; ResultSLHelper.ERROR=RegistrationSLConstants.REG_SLGEN_111;
		}
		return secCode;
	}

	@Override
	public List<com.pFoods.dbo.userProfile.vo.AddressVO> retriveUserAddressesSL(RetrieveUserAddressListRequestTO retrieveUserAddressListRequestTO) {
		List<com.pFoods.dbo.userProfile.vo.AddressVO> addressList = null;
		try{
			ResultSLHelper.clearResult();
			AbstractApplicationContext context = getAppContext();
			RegistrationSLHelper registrationSLHelperBean  = (RegistrationSLHelper)context.getBean("registrationSLHelperBean");
			addressList = registrationSLHelperBean.retriveUserAddressesSLHelper(retrieveUserAddressListRequestTO, context);
		}catch(Exception e){
			ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RegistrationSLConstants.FAILURE; ResultSLHelper.ERROR=RegistrationSLConstants.REG_SLGEN_111;
		}
		return addressList;
	}
	
	
	
}
