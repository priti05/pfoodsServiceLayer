package com.pFoods.sl.restaurant;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pFoods.dbo.restaurant.to.RestaurantNameandAddressInfo;
import com.pFoods.dbo.restaurant.to.RestaurantProfileInformationRequestTO;
import com.pFoods.dbo.restaurant.to.RestaurantProfileInformationResponseTO;
import com.pFoods.dbo.restaurant.to.RestaurantRatingRequestTO;
import com.pFoods.dbo.restaurant.to.RestaurantSearchRequestTO;
import com.pFoods.dbo.restaurant.to.RestaurantSearchResponseTO;
import com.pFoods.sl.restaurant.To.RestaurantImageInsertionRequestTo;
import com.pFoods.sl.restaurant.To.RestaurantLoginCredentialSLRequestTo;
import com.pFoods.sl.restaurant.To.RestaurantRegistrationMenuSLRequestTo;
import com.pFoods.sl.restaurant.To.RestaurantRegistrationSLRequestTo;
import com.pFoods.sl.restaurant.constant.RestaurantRegistrationSLConstant;
import com.pFoods.sl.restaurant.helper.RestaurantRegistrationSLHelper;
import com.pFoods.sl.result.ResultSLHelper;

public class RestaurantaRegistrationSL implements IRestaurantRegistrationSL {

	public AbstractApplicationContext getAppContext(){
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(RestaurantRegistrationSLConstant.SpringXML);
		context.registerShutdownHook();
		return context;		
	}
	
	@Override
	public boolean registerRestaurant(RestaurantRegistrationSLRequestTo restaurantRegistrationSLRequestTo) {
		boolean flag=false;
		try{
			AbstractApplicationContext context = getAppContext();
			RestaurantRegistrationSLHelper restaurantRegistrationSLHelper  = (RestaurantRegistrationSLHelper)context.getBean("restaurantRegistrationSLHelper");
			restaurantRegistrationSLHelper.createRestaurantRegisterModel(restaurantRegistrationSLRequestTo, context);
			flag=true;
		}catch(Exception e){
			ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SLGEN_111;
		}
		return flag;
	}
	
	@Override
	public boolean registerRestaurantMenu(RestaurantRegistrationMenuSLRequestTo restaurantMenuRegistrationMenuDBORequestTO){
		boolean flag=false;
		try{
			AbstractApplicationContext context = getAppContext();
			RestaurantRegistrationSLHelper restaurantRegistrationSLHelper  = (RestaurantRegistrationSLHelper)context.getBean("restaurantRegistrationSLHelper");
			restaurantRegistrationSLHelper.createRestaurantRegisterMenuModel(restaurantMenuRegistrationMenuDBORequestTO, context);
			flag=true;
		}catch(Exception e){
			ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SLGEN_111;
		}
		return flag;
	}

	@Override
	public boolean validateCredential(RestaurantLoginCredentialSLRequestTo restaurantLoginCredentialSLRequestTo) {
		boolean flag=false;
		try{
			AbstractApplicationContext context = getAppContext();
			RestaurantRegistrationSLHelper restaurantRegistrationSLHelper  = (RestaurantRegistrationSLHelper)context.getBean("restaurantRegistrationSLHelper");
			flag = restaurantRegistrationSLHelper.validateCredentialHelper(restaurantLoginCredentialSLRequestTo, context);
		}catch(Exception e){
			ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SLGEN_111;
		}
		return flag;
	}

	@Override
	public void insertImages(RestaurantImageInsertionRequestTo restaurantImageInsertionRequestTo) {
		try{
			AbstractApplicationContext context = getAppContext();
			RestaurantRegistrationSLHelper restaurantRegistrationSLHelper  = (RestaurantRegistrationSLHelper)context.getBean("restaurantRegistrationSLHelper");
			restaurantRegistrationSLHelper.insertImagesHelper(restaurantImageInsertionRequestTo, context);
		}catch(Exception e){
			ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SLGEN_111;
		}		
	}

	@Override
	public List<RestaurantNameandAddressInfo> retrieveRestaurantNameandAddressInfoSL() {
		
		List<RestaurantNameandAddressInfo>  restaurantNameandAddressInfoList = null;
		try{
			AbstractApplicationContext context = getAppContext();
			RestaurantRegistrationSLHelper restaurantRegistrationSLHelper  = (RestaurantRegistrationSLHelper)context.getBean("restaurantRegistrationSLHelper");
			restaurantNameandAddressInfoList = restaurantRegistrationSLHelper.retrieveRestaurantNameandAddressInfoSLHelper(context);
		}catch(Exception e){
			ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SLGEN_111;
		}
		return restaurantNameandAddressInfoList;
	}

	@Override
	public List<RestaurantSearchResponseTO> searchRestaurantSL(RestaurantSearchRequestTO restaurantSearchRequestTO) {
		List<RestaurantSearchResponseTO> restaurantSearchResponseTOList = null;
		try{
			AbstractApplicationContext context = getAppContext();
			RestaurantRegistrationSLHelper restaurantRegistrationSLHelper  = (RestaurantRegistrationSLHelper)context.getBean("restaurantRegistrationSLHelper");
			restaurantSearchResponseTOList = restaurantRegistrationSLHelper.searchRestaurantSLHelper(restaurantSearchRequestTO, context);
		}catch(Exception e){
			ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SLGEN_111;
		}
		return restaurantSearchResponseTOList;
	}

	@Override
	public RestaurantProfileInformationResponseTO getRestaurantProfileSL(
			RestaurantProfileInformationRequestTO restaurantProfileInformationRequestTO) {
		RestaurantProfileInformationResponseTO restaurantProfileInformationResponseTO = null;
		try{
			AbstractApplicationContext context = getAppContext();
			RestaurantRegistrationSLHelper restaurantRegistrationSLHelper  = (RestaurantRegistrationSLHelper)context.getBean("restaurantRegistrationSLHelper");
			restaurantProfileInformationResponseTO = restaurantRegistrationSLHelper.getRestaurantProfileSLHelper(restaurantProfileInformationRequestTO, context);
		}catch(Exception e){
			ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SLGEN_111;
		}
		return restaurantProfileInformationResponseTO;
	}

	@Override
	public void updateRestaurantRating(RestaurantRatingRequestTO restaurantRatingRequestTO) {
		try{
			AbstractApplicationContext context = getAppContext();
			RestaurantRegistrationSLHelper restaurantRegistrationSLHelper  = (RestaurantRegistrationSLHelper)context.getBean("restaurantRegistrationSLHelper");
			restaurantRegistrationSLHelper.updateRestaurantRatingSLHelper(restaurantRatingRequestTO, context);
		}catch(Exception e){
			ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=RestaurantRegistrationSLConstant.FAILURE; ResultSLHelper.ERROR=RestaurantRegistrationSLConstant.RSPF_SLGEN_111;
		}
		
	}

}
