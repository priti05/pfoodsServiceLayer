package com.pFoods.sl.restaurant.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.support.AbstractApplicationContext;

import com.pFoods.dbo.restaurant.IRestaurantProfileDBORegistration;
import com.pFoods.dbo.restaurant.RestaurantProfileDBORegistration;
import com.pFoods.dbo.restaurant.constants.RestuarantProfileDBOConstant;
import com.pFoods.dbo.restaurant.imageModel.Images;
import com.pFoods.dbo.restaurant.imageModel.Images.Profile;
import com.pFoods.dbo.restaurant.loginModal.RestaurantLoginInfo;
import com.pFoods.dbo.restaurant.profileModal.RestaurantAddress;
import com.pFoods.dbo.restaurant.profileModal.RestaurantProfile;
import com.pFoods.dbo.restaurant.profileModal.RestaurantProfile.Deliever;
import com.pFoods.dbo.restaurant.profileModal.RestaurantProfile.PickUp;
import com.pFoods.dbo.restaurant.profileModal.RestaurantProfile.ReserveTable;
import com.pFoods.dbo.restaurant.to.RestaurantInsertImagesRequestTO;
import com.pFoods.dbo.restaurant.to.RestaurantMenuRegistrationDBORequestTO;
import com.pFoods.dbo.restaurant.to.RestaurantNameandAddressInfo;
import com.pFoods.dbo.restaurant.to.RestaurantProfileInformationRequestTO;
import com.pFoods.dbo.restaurant.to.RestaurantProfileInformationResponseTO;
import com.pFoods.dbo.restaurant.to.RestaurantRatingRequestTO;
import com.pFoods.dbo.restaurant.to.RestaurantRegistrationDBORequestTO;
import com.pFoods.dbo.restaurant.to.RestaurantSearchRequestTO;
import com.pFoods.dbo.restaurant.to.RestaurantSearchResponseTO;
import com.pFoods.dbo.restaurant.to.RestaurantValidateLoginCredentialRequestTO;
import com.pFoods.dbo.result.ResultDBOHelper;
import com.pFoods.sl.restaurant.To.RestaurantImageInsertionRequestTo;
import com.pFoods.sl.restaurant.To.RestaurantLoginCredentialSLRequestTo;
import com.pFoods.sl.restaurant.To.RestaurantRegistrationMenuSLRequestTo;
import com.pFoods.sl.restaurant.To.RestaurantRegistrationSLRequestTo;
import com.pFoods.sl.restaurant.constant.RestaurantRegistrationSLConstant;
import com.pFoods.sl.restaurant.vo.RestaurantAddressVO;
import com.pFoods.sl.restaurant.vo.RestaurantImageVO;
import com.pFoods.sl.restaurant.vo.RestaurantLoginVO;
import com.pFoods.sl.restaurant.vo.RestaurantProfileVO;
import com.pFoods.sl.result.ResultSLHelper;

public class RestaurantRegistrationSLHelper {

	/**
	 * To register restaurant profile
	 * @param restaurantRegistrationSLRequestTo
	 * @param context
	 */
	public void createRestaurantRegisterModel(RestaurantRegistrationSLRequestTo restaurantRegistrationSLRequestTo,
			AbstractApplicationContext context) {
		ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SLGEN_111;
		if(restaurantRegistrationSLRequestTo!=null){
			RestaurantLoginVO restaurantLoginVO = restaurantRegistrationSLRequestTo.getRestaurantLoginVO();
			RestaurantProfileVO restaurantProfileVO = restaurantRegistrationSLRequestTo.getRestaurantProfileVO();
			RestaurantAddressVO restaurantAddressVO =restaurantRegistrationSLRequestTo.getRestaurantAddressVO();
			int tasteId = restaurantRegistrationSLRequestTo.getTasteId();
			if(restaurantLoginVO!=null && restaurantProfileVO!=null && restaurantAddressVO!=null){
				String rid = restaurantLoginVO.getUserId();
				String pass = restaurantLoginVO.getPassword();
				String resName = restaurantProfileVO.getName();
				String resDescr = restaurantProfileVO.getDescription();
				String resPhone = restaurantProfileVO.getPhoneNumber();
				String dlvrMini = restaurantProfileVO.getDelieveryMinimum();
				Deliever deliever = restaurantProfileVO.getDelieverOption();
				String dlvrCharge = restaurantProfileVO.getDelieveryCharge();
				PickUp pickUp = restaurantProfileVO.getPickUpOption();
				ReserveTable reserve = restaurantProfileVO.getReserveTableOption();
				String strNumber = restaurantAddressVO.getStreetNumber();
				String str = restaurantAddressVO.getStreet();
				String adr1 = restaurantAddressVO.getAddress1();
				String adr2 = restaurantAddressVO.getAddress2();
				String state = restaurantAddressVO.getState();				
				String town = restaurantAddressVO.getTown();
				String zip = restaurantAddressVO.getZip();
				
				if(StringUtils.isNotBlank(rid) && StringUtils.isNotBlank(pass) && StringUtils.isNotBlank(resName)  &&
						StringUtils.isNotBlank(resDescr) && StringUtils.isNotBlank(resPhone) && StringUtils.isNotBlank(dlvrMini) && StringUtils.isNotBlank(dlvrCharge) &&
						deliever!=null && pickUp!=null && reserve!=null && StringUtils.isNotBlank(strNumber) && StringUtils.isNotBlank(str) &&
						StringUtils.isNotBlank(state) && StringUtils.isNotBlank(town) && StringUtils.isNotBlank(zip)){
					RestaurantRegistrationDBORequestTO restaurantRegistrationDBORequestTO = (RestaurantRegistrationDBORequestTO)context.getBean("restaurantRegistrationDBORequestTO");
					
					RestaurantLoginInfo restaurantLoginInfo = restaurantRegistrationDBORequestTO.getRestaurantLoginInfo();
					restaurantLoginInfo.setUserId(rid);
					restaurantLoginInfo.setPassword(pass);

					RestaurantProfile restaurantProfile = restaurantRegistrationDBORequestTO.getRestaurantProfile();
					restaurantProfile.setName(resName);
					restaurantProfile.setDescription(resDescr);
					restaurantProfile.setPhoneNumber(resPhone);
					restaurantProfile.setDelieverOption(deliever);
					restaurantProfile.setDelieveryCharge(dlvrCharge);
					restaurantProfile.setDelieveryMinimum(dlvrMini);
					restaurantProfile.setPickUpOption(pickUp);
					restaurantProfile.setReserveTableOption(reserve);
					restaurantProfile.setRestaurantLoginInfo(restaurantLoginInfo);
					
					RestaurantAddress restaurantAddress = restaurantRegistrationDBORequestTO.getRestaurantAddress();
					restaurantAddress.setStreetNumber(strNumber);
					restaurantAddress.setStreet(str);
					restaurantAddress.setAddress1(adr1);
					restaurantAddress.setAddress2(adr2);
					restaurantAddress.setState(state);
					restaurantAddress.setTown(town);
					restaurantAddress.setZip(zip);
					restaurantAddress.setRestaurantLoginInfo(restaurantLoginInfo);
					
					restaurantRegistrationDBORequestTO.setTasteId(tasteId);
					IRestaurantProfileDBORegistration restaurantProfileDBORegistration = (RestaurantProfileDBORegistration)context.getBean("restaurantProfileDBORegistration");
					restaurantProfileDBORegistration.registerRestaurant(restaurantRegistrationDBORequestTO);
					if(ResultDBOHelper.SUCCESS){
						//SUCCESS CALL
						ResultSLHelper.SUCCESS = true; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.SUCCESS; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.SL_000;
					}else if(StringUtils.equalsIgnoreCase(ResultDBOHelper.ERROR, RestuarantProfileDBOConstant.RSPF_DBO_101 )){
						//INVALID INPUT
						ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SL_101;
					}else if(StringUtils.equalsIgnoreCase(ResultDBOHelper.ERROR, RestuarantProfileDBOConstant.RSPF_GEN_111 )){
						//SYSTEM DOWN
						ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SLGEN_111;
					}else if(StringUtils.equalsIgnoreCase(ResultDBOHelper.ERROR, RestuarantProfileDBOConstant.RSPF_DBO_102 )){
						//RID NOT AVAILABE to REGISTER
						ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SL_102;
					}
				}else{
					//INVALID INPUT
					ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SL_101;			
				}
			}else{ 
				//INVALID INPUT
				ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SL_101;
			}	
		}else{
			//INVALID INPUT
			ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SL_101;
		}
	}
	
	/**
	 * To upload restaurant menu
	 * @param restaurantRegistrationSLRequestTo
	 * @param context
	 */
	public void createRestaurantRegisterMenuModel(
			RestaurantRegistrationMenuSLRequestTo restaurantRegistrationMenuSLRequestTo,
			AbstractApplicationContext context) {
		ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SLGEN_111;
		if(restaurantRegistrationMenuSLRequestTo!=null){
			String rid = restaurantRegistrationMenuSLRequestTo.getRid();
			if(StringUtils.isNotBlank(rid)){
				RestaurantMenuRegistrationDBORequestTO restaurantMenuRegistrationDBORequestTO = (RestaurantMenuRegistrationDBORequestTO)context.getBean("restaurantMenuRegistrationDBORequestTO");
				restaurantMenuRegistrationDBORequestTO.setRid(rid);
				restaurantMenuRegistrationDBORequestTO.setMenuList(restaurantRegistrationMenuSLRequestTo.getMenuList());
				IRestaurantProfileDBORegistration restaurantProfileDBORegistration = (RestaurantProfileDBORegistration)context.getBean("restaurantProfileDBORegistration");
				restaurantProfileDBORegistration.registerRestaurantMenuProfile(restaurantMenuRegistrationDBORequestTO);
				if(ResultDBOHelper.SUCCESS){
					//SUCCESS CALL
					ResultSLHelper.SUCCESS = true; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.SUCCESS; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.SL_000;
				}else if(StringUtils.equalsIgnoreCase(ResultDBOHelper.ERROR, RestuarantProfileDBOConstant.RSPF_DBO_101 )){
					//INVALID INPUT
					ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SL_101;
				}else if(StringUtils.equalsIgnoreCase(ResultDBOHelper.ERROR, RestuarantProfileDBOConstant.RSPF_GEN_111 )){
					//SYSTEM DOWN
					ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SLGEN_111;
				}else if(StringUtils.equalsIgnoreCase(ResultDBOHelper.ERROR, RestuarantProfileDBOConstant.RSPF_DBO_102 )){
					//RID NOT AVAILABE to REGISTER
					ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SL_102;
				}
			}else{
				//INVALID INPUT
				ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SL_101;
			}	
		}else{
			//INVALID INPUT
			ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SL_101;
		}	
	}

	public boolean validateCredentialHelper(RestaurantLoginCredentialSLRequestTo restaurantLoginCredentialSLRequestTo,
			AbstractApplicationContext context) {
		ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SLGEN_111;
		boolean validated=false;
		if(restaurantLoginCredentialSLRequestTo!=null){
			String rid = restaurantLoginCredentialSLRequestTo.getRid();
			String password = restaurantLoginCredentialSLRequestTo.getPassword();
			if(StringUtils.isNotBlank(rid) && StringUtils.isNotBlank(password)){
				RestaurantValidateLoginCredentialRequestTO restaurantValidateLoginCredentialRequestTO = (RestaurantValidateLoginCredentialRequestTO)context.getBean("restaurantValidateLoginCredentialRequestTO");
				restaurantValidateLoginCredentialRequestTO.setPassword(password);
				restaurantValidateLoginCredentialRequestTO.setRid(rid);
				IRestaurantProfileDBORegistration restaurantProfileDBORegistration = (RestaurantProfileDBORegistration)context.getBean("restaurantProfileDBORegistration");
				validated = restaurantProfileDBORegistration.validateLoginRestaurant(restaurantValidateLoginCredentialRequestTO);
				if(ResultDBOHelper.SUCCESS){
					//SUCCESS CALL
					ResultSLHelper.SUCCESS = true; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.SUCCESS; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.SL_000;
				}else if(StringUtils.equalsIgnoreCase(ResultDBOHelper.ERROR, RestuarantProfileDBOConstant.RSPF_DBO_101 )){
					//INVALID INPUT
					ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SL_101;
				}else if(StringUtils.equalsIgnoreCase(ResultDBOHelper.ERROR, RestuarantProfileDBOConstant.RSPF_GEN_111 )){
					//SYSTEM DOWN
					ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SLGEN_111;
				}else if(StringUtils.equalsIgnoreCase(ResultDBOHelper.ERROR, RestuarantProfileDBOConstant.RSPF_DBO_103) ){
					//RID NOT FOUND
					ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SL_103;
				}
			}else{
				//INVALID INPUT
				ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SL_101;
			}	
		}else{
			//INVALID INPUT
			ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SL_101;
		}	
		return validated;
	}

	public void insertImagesHelper(RestaurantImageInsertionRequestTo restaurantImageInsertionRequestTo,
			AbstractApplicationContext context) {
		ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SLGEN_111;
		if(restaurantImageInsertionRequestTo!=null){
			String rid = restaurantImageInsertionRequestTo.getRid();
			List<RestaurantImageVO> imageList = restaurantImageInsertionRequestTo.getRestaurantImageVOList();
			if(StringUtils.isNotBlank(rid) && imageList!=null && imageList.size()>0){
				RestaurantInsertImagesRequestTO restaurantInsertImagesRequestTO = new RestaurantInsertImagesRequestTO();
				restaurantInsertImagesRequestTO.setRid(rid);
				List<Images> imageListDBO = new ArrayList<Images>(); 
				for(RestaurantImageVO imageVo:imageList){
					if(imageVo!=null){
						byte[] image = imageVo.getImage();
						Profile profileImage = imageVo.getProfileImage();
						if(image!=null && profileImage!=null){
							Images imageModel = new Images();
							imageModel.setImage(image);
							imageModel.setProfileImage(profileImage);
							imageListDBO.add(imageModel);
						}
					}
				}
				restaurantInsertImagesRequestTO.setRid(rid);
				restaurantInsertImagesRequestTO.setImageList(imageListDBO);
				IRestaurantProfileDBORegistration restaurantProfileDBORegistration = (RestaurantProfileDBORegistration)context.getBean("restaurantProfileDBORegistration");
				restaurantProfileDBORegistration.insertRestuarantProfileImages(restaurantInsertImagesRequestTO);
				if(ResultDBOHelper.SUCCESS){
					//SUCCESS CALL
					ResultSLHelper.SUCCESS = true; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.SUCCESS; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.SL_000;
				}else if(StringUtils.equalsIgnoreCase(ResultDBOHelper.ERROR, RestuarantProfileDBOConstant.RSPF_DBO_101 )){
					//INVALID INPUT
					ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SL_101;
				}else if(StringUtils.equalsIgnoreCase(ResultDBOHelper.ERROR, RestuarantProfileDBOConstant.RSPF_GEN_111 )){
					//SYSTEM DOWN
					ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SLGEN_111;
				}else if(StringUtils.equalsIgnoreCase(ResultDBOHelper.ERROR, RestuarantProfileDBOConstant.RSPF_DBO_102) ){
					//RID NOT FOUND
					ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SL_102;
				}
			}else{
				//INVALID INPUT
				ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SL_101;
			}	
		}else{
			//INVALID INPUT
			ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SL_101;
		}	
		
	}

	public List<RestaurantNameandAddressInfo> retrieveRestaurantNameandAddressInfoSLHelper(AbstractApplicationContext context) {
		ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SLGEN_111;
				IRestaurantProfileDBORegistration restaurantProfileDBORegistration = (RestaurantProfileDBORegistration)context.getBean("restaurantProfileDBORegistration");
				List<RestaurantNameandAddressInfo>  restaurantNameandAddressInfoList = restaurantProfileDBORegistration.retrieveRestaurantNameandAddressInfo();
				if(ResultDBOHelper.SUCCESS){
					//SUCCESS CALL
					ResultSLHelper.SUCCESS = true; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.SUCCESS; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.SL_000;
				}else if(StringUtils.equalsIgnoreCase(ResultDBOHelper.ERROR, RestuarantProfileDBOConstant.RSPF_DBO_101 )){
					//INVALID INPUT
					ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SL_101;
				}else if(StringUtils.equalsIgnoreCase(ResultDBOHelper.ERROR, RestuarantProfileDBOConstant.RSPF_GEN_111 )){
					//SYSTEM DOWN
					ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SLGEN_111;
				}
		return restaurantNameandAddressInfoList;		
	}

	public List<RestaurantSearchResponseTO> searchRestaurantSLHelper(RestaurantSearchRequestTO restaurantSearchRequestTO,
			AbstractApplicationContext context) {
		ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SLGEN_111;
		List<RestaurantSearchResponseTO> restaurantSearchResponseTOList =null;
		if(restaurantSearchRequestTO!=null){
			IRestaurantProfileDBORegistration restaurantProfileDBORegistration = (RestaurantProfileDBORegistration)context.getBean("restaurantProfileDBORegistration");
			restaurantSearchResponseTOList = restaurantProfileDBORegistration.searchRestaurantDBO(restaurantSearchRequestTO);
			if(ResultDBOHelper.SUCCESS){
				//SUCCESS CALL
				ResultSLHelper.SUCCESS = true; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.SUCCESS; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.SL_000;
			}else if(StringUtils.equalsIgnoreCase(ResultDBOHelper.ERROR, RestuarantProfileDBOConstant.RSPF_DBO_101 )){
				//INVALID INPUT
				ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SL_101;
			}else if(StringUtils.equalsIgnoreCase(ResultDBOHelper.ERROR, RestuarantProfileDBOConstant.RSPF_GEN_111 )){
				//SYSTEM DOWN
				ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SLGEN_111;
			}			
		}else{
			//INVALID INPUT
			ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SL_101;
		}
		return restaurantSearchResponseTOList;
		
	}

	public RestaurantProfileInformationResponseTO getRestaurantProfileSLHelper(
			RestaurantProfileInformationRequestTO restaurantProfileInformationRequestTO,
			AbstractApplicationContext context) {
		
		ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SLGEN_111;
		RestaurantProfileInformationResponseTO restaurantProfileInformationResponseTO =null;
		if(restaurantProfileInformationRequestTO!=null  &&  StringUtils.isNotBlank(restaurantProfileInformationRequestTO.getRid())){
			IRestaurantProfileDBORegistration restaurantProfileDBORegistration = (RestaurantProfileDBORegistration)context.getBean("restaurantProfileDBORegistration");
			restaurantProfileInformationResponseTO = restaurantProfileDBORegistration.getRestaurantProfile(restaurantProfileInformationRequestTO);
			if(ResultDBOHelper.SUCCESS){
				//SUCCESS CALL
				ResultSLHelper.SUCCESS = true; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.SUCCESS; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.SL_000;
			}else if(StringUtils.equalsIgnoreCase(ResultDBOHelper.ERROR, RestuarantProfileDBOConstant.RSPF_DBO_101 )){
				//INVALID INPUT
				ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SL_101;
			}else if(StringUtils.equalsIgnoreCase(ResultDBOHelper.ERROR, RestuarantProfileDBOConstant.RSPF_GEN_111 )){
				//SYSTEM DOWN
				ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SLGEN_111;
			}			
		}else{
			//INVALID INPUT
			ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SL_101;
		}
		return restaurantProfileInformationResponseTO;
		
		
	}

	public void updateRestaurantRatingSLHelper(RestaurantRatingRequestTO restaurantRatingRequestTO,
			AbstractApplicationContext context) {
		ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SLGEN_111;
		if(restaurantRatingRequestTO!=null){
			IRestaurantProfileDBORegistration restaurantProfileDBORegistration = (RestaurantProfileDBORegistration)context.getBean("restaurantProfileDBORegistration");
			restaurantProfileDBORegistration.syncRating(restaurantRatingRequestTO);
			if(ResultDBOHelper.SUCCESS){
				//SUCCESS CALL
				ResultSLHelper.SUCCESS = true; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.SUCCESS; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.SL_000;
			}else if(StringUtils.equalsIgnoreCase(ResultDBOHelper.ERROR, RestuarantProfileDBOConstant.RSPF_DBO_101 )){
				//INVALID INPUT
				ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SL_101;
			}else if(StringUtils.equalsIgnoreCase(ResultDBOHelper.ERROR, RestuarantProfileDBOConstant.RSPF_GEN_111 )){
				//SYSTEM DOWN
				ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SLGEN_111;
			}			
		}else{
			//INVALID INPUT
			ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SL_101;
		}
		
	}
}
