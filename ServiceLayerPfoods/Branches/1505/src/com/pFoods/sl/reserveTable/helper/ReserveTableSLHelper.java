package com.pFoods.sl.reserveTable.helper;

import org.apache.commons.lang3.StringUtils;

import com.pFoods.dbo.restaurant.IRestaurantProfileDBORegistration;
import com.pFoods.dbo.restaurant.RestaurantProfileDBORegistration;
import com.pFoods.dbo.restaurant.constants.RestuarantProfileDBOConstant;
import com.pFoods.dbo.restaurant.to.RestaurantReserveTableRequestTO;
import com.pFoods.dbo.result.ResultDBOHelper;
import com.pFoods.sl.reserveTable.constant.ReserveTableSLConstant;
import com.pFoods.sl.result.ResultSLHelper;

public class ReserveTableSLHelper {

	public void reservationSLHelper(RestaurantReserveTableRequestTO restaurantReserveTableRequestTO) {
		ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=ReserveTableSLConstant.FAILURE; ResultSLHelper.ERROR=ReserveTableSLConstant.RVT_SLGEN_111;
		if(restaurantReserveTableRequestTO!=null){
			IRestaurantProfileDBORegistration restaurantProfileDBORegistration = new RestaurantProfileDBORegistration();
			restaurantProfileDBORegistration.reserveTable(restaurantReserveTableRequestTO);
			if(ResultDBOHelper.SUCCESS){
				//SUCCESS CALL
				ResultSLHelper.SUCCESS = true; ResultSLHelper.RESULT=ReserveTableSLConstant.SUCCESS; ResultSLHelper.ERROR=ReserveTableSLConstant.SL_000;
			}else if(StringUtils.equalsIgnoreCase(ResultDBOHelper.ERROR, RestuarantProfileDBOConstant.RSPF_DBO_101 )){
				//INVALID INPUT
				ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=ReserveTableSLConstant.FAILURE; ResultSLHelper.ERROR=ReserveTableSLConstant.RVT_SL_101;
			}else if(StringUtils.equalsIgnoreCase(ResultDBOHelper.ERROR, RestuarantProfileDBOConstant.RSPF_GEN_111 )){
				//SYSTEM DOWN
				ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=ReserveTableSLConstant.FAILURE; ResultSLHelper.ERROR=ReserveTableSLConstant.RVT_SLGEN_111;
			}			
		}else{
			//INVALID INPUT
			ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=ReserveTableSLConstant.FAILURE; ResultSLHelper.ERROR=ReserveTableSLConstant.RVT_SL_101;
		}
		
	}

}
