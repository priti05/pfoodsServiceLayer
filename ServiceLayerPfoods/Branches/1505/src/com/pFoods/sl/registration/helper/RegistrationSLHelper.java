package com.pFoods.sl.registration.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.support.AbstractApplicationContext;

import com.pFoods.dbo.result.ResultDBOHelper;
import com.pFoods.sl.registration.To.RegistrationSLRequestTO;
import com.pFoods.sl.registration.constant.RegistrationSLConstants;
import com.pFoods.sl.registration.vo.AddressVO;
import com.pFoods.sl.registration.vo.UserProfileVO;
import com.pFoods.sl.result.ResultSLHelper;
import com.pFoods.dbo.userProfile.IUserProfileDBORegistration;
import com.pFoods.dbo.userProfile.UserProfileDBORegistration;
import com.pFoods.dbo.userProfile.TO.NewSecurityCodeRequestTO;
import com.pFoods.dbo.userProfile.TO.RegistrationDBORequestTO;
import com.pFoods.dbo.userProfile.TO.RetrieveUserAddressListRequestTO;
import com.pFoods.dbo.userProfile.TO.UpdateAddressDBOTO;
import com.pFoods.dbo.userProfile.TO.VerifySecurityCodeTO;
import com.pFoods.dbo.userProfile.constants.RegistrationDBOConstant;
import com.pFoods.dbo.userProfile.loginModal.LoginInfo;
import com.pFoods.dbo.userProfile.profileModal.Address;
import com.pFoods.dbo.userProfile.profileModal.Address.AddressType;
import com.pFoods.dbo.userProfile.profileModal.UserProfile;
import com.pFoods.dbo.userProfile.profileModal.UserProfile.VerificationType;

public class RegistrationSLHelper {
	
	public String createRegisterModel(UserProfileVO userProfileVO, AbstractApplicationContext context){
		/*
		 * This need to be overridden upon success call to DBO layer,
		 * By default we "SYSTEM DOWN error code"
		 */
		ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RegistrationSLConstants.FAILURE; ResultSLHelper.ERROR=RegistrationSLConstants.REG_SLGEN_111;
		String secCode = null;
		try{
			if(userProfileVO!=null){
				String uid = userProfileVO.getUid();
				String password = userProfileVO.getPassword();
				String fName = userProfileVO.getfName();
				String lName = userProfileVO.getlName();
				String email = userProfileVO.getEmail();
				String phoneNumber = userProfileVO.getPhoneNumber();
				AddressVO adrVO = userProfileVO.getAddressVO();
				/*
				 * uid, password, fname , lname, phonenumbers are must field
				 * otherwise return "Invalid input" error
				 */
				if(StringUtils.isNotBlank(uid) && StringUtils.isNotBlank(password) && StringUtils.isNotBlank(fName)
						&& StringUtils.isNotBlank(lName) && StringUtils.isNotBlank(phoneNumber)){
					/*
					 * Make sure Registration-Spring context exist
					 */
					if(context!=null){
						RegistrationSLModelHelper regSLModelHlp = (RegistrationSLModelHelper)context.getBean("registrationSLModelHelperBean");
						if(regSLModelHlp!=null){
							LoginInfo loginInfo = regSLModelHlp.getLoginInfoModel();
							UserProfile userProfile = regSLModelHlp.getUserProfileModel();
							Address adr = null; // we don't want to assign Address yet
							loginInfo.setUserId(uid);
							loginInfo.setPassword(password);
							userProfile.setLoginInfo(loginInfo);
							userProfile.setFirstname(fName);
							userProfile.setLastName(lName);
							userProfile.setPhone_number(phoneNumber);
							userProfile.setEmail(email);
							userProfile.setVerifiedStatus(VerificationType.no);
							userProfile.setProfileCreationDate();
							/*
							 * if user hasn't skipped address window
							 */
							if(adrVO!=null){
								adr = regSLModelHlp.getAddressModel();
								Collection<Address> adrList = new ArrayList<>();
								adrList.add(adr);
								loginInfo.setAddress(adrList);
								adr.setLoginInfo(loginInfo);
								adr.setStreet_Numer(adrVO.getStreet_number());
								adr.setApt_Numer(adrVO.getApt_number());
								adr.setStreet(adrVO.getStreet());
								adr.setTown(adrVO.getTown());
								adr.setState(adrVO.getState());
								adr.setZip(adrVO.getZip());
								adr.setAddressType(AddressType.p); //To update address User will need to select as primary or secondary 
							}
							IUserProfileDBORegistration userProfRegDBO = (UserProfileDBORegistration)context.getBean("userProfileRegDBOBean");
							if(userProfRegDBO!=null){
								try{
									RegistrationSLRequestTO registrationSLRequestTO = (RegistrationSLRequestTO)context.getBean("registrationSLRequestTO");
									if(registrationSLRequestTO!=null){
										RegistrationDBORequestTO registrationDBORequestTO = registrationSLRequestTO.getRegistrationDBORequestTO();
										if(registrationDBORequestTO!=null){
											registrationDBORequestTO.setLogininfo(loginInfo);
											registrationDBORequestTO.setUserProfile(userProfile);
											if(adr!=null){
												registrationDBORequestTO.setAddress(adr);
											}
											secCode = userProfRegDBO.submitUserProfile(registrationDBORequestTO);
											if(ResultDBOHelper.SUCCESS){
												//SUCCESS CALL
												ResultSLHelper.SUCCESS = true; ResultSLHelper.RESULT=RegistrationSLConstants.SUCCESS; ResultSLHelper.ERROR=RegistrationSLConstants.SL_000;
											}else if(ResultDBOHelper.ERROR==RegistrationDBOConstant.REG_DBO_101){
												//INVALID INPUT
												ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RegistrationSLConstants.FAILURE; ResultSLHelper.ERROR=RegistrationSLConstants.REG_SL_101;
											}else if(ResultDBOHelper.ERROR==RegistrationDBOConstant.REG_GEN_111){
												//SYSTEM DOWN
												ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RegistrationSLConstants.FAILURE; ResultSLHelper.ERROR=RegistrationSLConstants.REG_SLGEN_111;
											}else if(ResultDBOHelper.ERROR==RegistrationDBOConstant.REG_DBO_102){
												//UID NOT AVAILABE to REGISTER
												ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RegistrationSLConstants.FAILURE; ResultSLHelper.ERROR=RegistrationSLConstants.REG_SL_102;
											}else if(ResultDBOHelper.ERROR==RegistrationDBOConstant.REG_DBO_103){
												//UID NOT AVAILABE to REGISTER
												ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RegistrationSLConstants.FAILURE; ResultSLHelper.ERROR=RegistrationSLConstants.REG_SL_103;
											}
										}
									}
								}catch(Exception e){
									//SYSTEM DOWN
									ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RegistrationSLConstants.FAILURE; ResultSLHelper.ERROR=RegistrationSLConstants.REG_SLGEN_111;
								}
							}
						}
					}
				}else{
					//INVALID INPUT
					ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RegistrationSLConstants.FAILURE; ResultSLHelper.ERROR=RegistrationSLConstants.REG_SL_101;
				}	
			}else{
				//INVALID INPUT
				ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RegistrationSLConstants.FAILURE; ResultSLHelper.ERROR=RegistrationSLConstants.REG_SL_101;
			}
		}catch(Exception e){
			secCode=null;
		}
		return secCode;		
	}

	public long updateAddressHelper(String userID, AddressVO adrVO, AbstractApplicationContext context) {
		/*
		 * This need to be overridden upon success call to DBO layer,
		 * By default we "SYSTEM DOWN error code"
		 */
		long addressID= 0 ;
		ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RegistrationSLConstants.FAILURE; ResultSLHelper.ERROR=RegistrationSLConstants.REG_SLGEN_111;
		try{
			if(StringUtils.isNotBlank(userID) && adrVO!=null && context!=null){
				Address address = (Address)context.getBean("addressModel");
				if(address!=null){
					String stNum = adrVO.getStreet_number();
					String st = adrVO.getStreet();
					String town = adrVO.getTown();
					String state = adrVO.getState();
					String zip = adrVO.getZip();
					AddressType adrType = adrVO.getAdrType();
					if(StringUtils.isNotBlank(stNum) && StringUtils.isNotBlank(st) && StringUtils.isNotBlank(town) && 
							StringUtils.isNotBlank(state) && StringUtils.isNotBlank(zip) && adrType != null){
						address.setStreet_Numer(stNum);
						address.setApt_Numer(adrVO.getApt_number());
						address.setStreet(st);
						address.setTown(town);
						address.setState(state);
						address.setZip(zip);
						address.setAddressType(adrType);
						RegistrationSLRequestTO registrationSLRequestTO = (RegistrationSLRequestTO)context.getBean("registrationSLRequestTO");
						if(registrationSLRequestTO!=null){
							UpdateAddressDBOTO updateAddressDBOTO = registrationSLRequestTO.getUpdateAddressDBOTo();
							if(updateAddressDBOTO!=null){
								updateAddressDBOTO.setAddress(address);
								updateAddressDBOTO.setUserID(userID);
								IUserProfileDBORegistration userProfRegDBO = (UserProfileDBORegistration)context.getBean("userProfileRegDBOBean");
								if(userProfRegDBO!=null){
									/*
									 * DBO layer call
									 */
									addressID = userProfRegDBO.updateAddress(updateAddressDBOTO);
									if(ResultDBOHelper.SUCCESS){
										ResultSLHelper.SUCCESS = true; ResultSLHelper.RESULT=RegistrationSLConstants.SUCCESS; ResultSLHelper.ERROR=RegistrationSLConstants.SL_000;
									}else if(ResultDBOHelper.ERROR==RegistrationDBOConstant.REG_DBO_101){
										ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RegistrationSLConstants.FAILURE; ResultSLHelper.ERROR=RegistrationSLConstants.REG_SL_101;
									}else if(ResultDBOHelper.ERROR==RegistrationDBOConstant.REG_GEN_111){
										ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RegistrationSLConstants.FAILURE; ResultSLHelper.ERROR=RegistrationSLConstants.REG_SLGEN_111;
									}
								}	
							}
						}	
					}else{
						//invalid input
						ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RegistrationSLConstants.FAILURE; ResultSLHelper.ERROR=RegistrationSLConstants.REG_SL_101;
					}
				}
			}else{
				//invalid input
				ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RegistrationSLConstants.FAILURE; ResultSLHelper.ERROR=RegistrationSLConstants.REG_SL_101;
			}
		}catch(Exception e){
			//default value for error has been set already
		}
		
		return addressID;
	}

	public boolean checkUserIDAvailable(String userID, AbstractApplicationContext context) {
		boolean flag = false;
		ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RegistrationSLConstants.FAILURE; ResultSLHelper.ERROR=RegistrationSLConstants.REG_SLGEN_111;
		try{
			if(StringUtils.isNotBlank(userID) && context!=null){
				IUserProfileDBORegistration userProfRegDBO = (UserProfileDBORegistration)context.getBean("userProfileRegDBOBean");
				if(userProfRegDBO!=null){
					/*
					* DBO layer call
					*/
					flag = userProfRegDBO.userIdAvaibility(userID);
				}
			}else{
				//invalid input
				ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RegistrationSLConstants.FAILURE; ResultSLHelper.ERROR=RegistrationSLConstants.REG_SL_101;
			}
		}catch(Exception e){
			//default value for error has been set already
		}
		return flag;
	}

	public boolean checkPhoneNumberAvailable(String phoneNumber, AbstractApplicationContext context) {
		boolean flag = false;
		ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RegistrationSLConstants.FAILURE; ResultSLHelper.ERROR=RegistrationSLConstants.REG_SLGEN_111;
		try{
			if(StringUtils.isNotBlank(phoneNumber) && context!=null){
				IUserProfileDBORegistration userProfRegDBO = (UserProfileDBORegistration)context.getBean("userProfileRegDBOBean");
				if(userProfRegDBO!=null){
					/*
					* DBO layer call
					*/
					flag = userProfRegDBO.phoneNumberAvailibility(phoneNumber);
				}
			}else{
				//invalid input
				ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RegistrationSLConstants.FAILURE; ResultSLHelper.ERROR=RegistrationSLConstants.REG_SL_101;
			}
		}catch(Exception e){
			//default value for error has been set already
		}
		return flag;
	}

	public boolean verifySecurityCodeSLHelper(VerifySecurityCodeTO verifySecurityCodeTO,
			AbstractApplicationContext context) {
		boolean flag = false;
		ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RegistrationSLConstants.FAILURE; ResultSLHelper.ERROR=RegistrationSLConstants.REG_SLGEN_111;
		try{
			if(verifySecurityCodeTO!=null && context!=null){
				IUserProfileDBORegistration userProfRegDBO = (UserProfileDBORegistration)context.getBean("userProfileRegDBOBean");
				if(userProfRegDBO!=null){
					/*
					* DBO layer call
					*/
					flag = userProfRegDBO.verifySecurityCode(verifySecurityCodeTO);
					if(ResultDBOHelper.SUCCESS && flag){
						ResultSLHelper.SUCCESS = true; ResultSLHelper.RESULT=RegistrationSLConstants.SUCCESS; ResultSLHelper.ERROR=RegistrationSLConstants.SL_000;
					}else if(ResultDBOHelper.ERROR==RegistrationDBOConstant.REG_DBO_101){
						ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RegistrationSLConstants.FAILURE; ResultSLHelper.ERROR=RegistrationSLConstants.REG_SL_101;
					}else if(ResultDBOHelper.ERROR==RegistrationDBOConstant.REG_GEN_111){
						ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RegistrationSLConstants.FAILURE; ResultSLHelper.ERROR=RegistrationSLConstants.REG_SLGEN_111;
					}
				}
			}else{
				//invalid input
				ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RegistrationSLConstants.FAILURE; ResultSLHelper.ERROR=RegistrationSLConstants.REG_SL_101;
			}
		}catch(Exception e){
			//default value for error has been set already
		}
		return flag;
	}

	public String newSecurityCodeSLHelper(NewSecurityCodeRequestTO wewSecurityCodeRequestTO,
			AbstractApplicationContext context) {
		String secCode = null;
		ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RegistrationSLConstants.FAILURE; ResultSLHelper.ERROR=RegistrationSLConstants.REG_SLGEN_111;
		try{
			if(wewSecurityCodeRequestTO!=null && context!=null){
				IUserProfileDBORegistration userProfRegDBO = (UserProfileDBORegistration)context.getBean("userProfileRegDBOBean");
				if(userProfRegDBO!=null){
					/*
					* DBO layer call
					*/
					secCode = userProfRegDBO.newSecurityCode(wewSecurityCodeRequestTO);
					if(ResultDBOHelper.SUCCESS){
						ResultSLHelper.SUCCESS = true; ResultSLHelper.RESULT=RegistrationSLConstants.SUCCESS; ResultSLHelper.ERROR=RegistrationSLConstants.SL_000;
					}else if(ResultDBOHelper.ERROR==RegistrationDBOConstant.REG_DBO_101){
						ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RegistrationSLConstants.FAILURE; ResultSLHelper.ERROR=RegistrationSLConstants.REG_SL_101;
					}else if(ResultDBOHelper.ERROR==RegistrationDBOConstant.REG_GEN_111){
						ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RegistrationSLConstants.FAILURE; ResultSLHelper.ERROR=RegistrationSLConstants.REG_SLGEN_111;
					}
				}
			}else{
				//invalid input
				ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RegistrationSLConstants.FAILURE; ResultSLHelper.ERROR=RegistrationSLConstants.REG_SL_101;
			}
		}catch(Exception e){
			//default value for error has been set already
		}
		return secCode;
	}

	public List<com.pFoods.dbo.userProfile.vo.AddressVO> retriveUserAddressesSLHelper(
			RetrieveUserAddressListRequestTO retrieveUserAddressListRequestTO, AbstractApplicationContext context) {
		List<com.pFoods.dbo.userProfile.vo.AddressVO> addressList = null;
		ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RegistrationSLConstants.FAILURE; ResultSLHelper.ERROR=RegistrationSLConstants.REG_SLGEN_111;
		try{
			if(retrieveUserAddressListRequestTO!=null && context!=null){
				IUserProfileDBORegistration userProfRegDBO = (UserProfileDBORegistration)context.getBean("userProfileRegDBOBean");
				if(userProfRegDBO!=null){
					/*
					* DBO layer call
					*/
					addressList = userProfRegDBO.retriveUserAddresses(retrieveUserAddressListRequestTO);
					if(ResultDBOHelper.SUCCESS){
						ResultSLHelper.SUCCESS = true; ResultSLHelper.RESULT=RegistrationSLConstants.SUCCESS; ResultSLHelper.ERROR=RegistrationSLConstants.SL_000;
					}else if(ResultDBOHelper.ERROR==RegistrationDBOConstant.REG_DBO_101){
						ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RegistrationSLConstants.FAILURE; ResultSLHelper.ERROR=RegistrationSLConstants.REG_SL_101;
					}else if(ResultDBOHelper.ERROR==RegistrationDBOConstant.REG_GEN_111){
						ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RegistrationSLConstants.FAILURE; ResultSLHelper.ERROR=RegistrationSLConstants.REG_SLGEN_111;
					}
				}
			}else{
				//invalid input
				ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RegistrationSLConstants.FAILURE; ResultSLHelper.ERROR=RegistrationSLConstants.REG_SL_101;
			}
		}catch(Exception e){
			//default value for error has been set already
		}
		return addressList;
	}
}
