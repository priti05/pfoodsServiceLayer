package com.pFoods.sl.restaurant;

import java.util.List;

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

public interface IRestaurantRegistrationSL {
	
	public boolean registerRestaurant(RestaurantRegistrationSLRequestTo restaurantRegistrationSLRequestTo);
	public boolean registerRestaurantMenu(RestaurantRegistrationMenuSLRequestTo restaurantRegistrationMenuSLRequestTo);
	public boolean validateCredential(RestaurantLoginCredentialSLRequestTo restaurantLoginCredentialSLRequestTo);
	public void insertImages(RestaurantImageInsertionRequestTo RestaurantImageInsertionRequestTo);
	public List<RestaurantNameandAddressInfo>  retrieveRestaurantNameandAddressInfoSL();	
	public List<RestaurantSearchResponseTO> searchRestaurantSL(RestaurantSearchRequestTO restaurantSearchRequestTO);
	public RestaurantProfileInformationResponseTO getRestaurantProfileSL(RestaurantProfileInformationRequestTO restaurantProfileInformationRequestTO);
	public void updateRestaurantRating(RestaurantRatingRequestTO restaurantRatingRequestTO);
	

}
