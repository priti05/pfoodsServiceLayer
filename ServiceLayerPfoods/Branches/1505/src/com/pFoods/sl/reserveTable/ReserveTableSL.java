package com.pFoods.sl.reserveTable;

import org.springframework.beans.factory.annotation.Autowired;

import com.pFoods.dbo.restaurant.to.RestaurantReserveTableRequestTO;
import com.pFoods.sl.reserveTable.constant.ReserveTableSLConstant;
import com.pFoods.sl.reserveTable.helper.ReserveTableSLHelper;
import com.pFoods.sl.result.ResultSLHelper;

public class ReserveTableSL implements IReserveTableSL {
	
	@Autowired
	private ReserveTableSLHelper reserveTableSLHelper;

	@Override
	public void reservationSL(RestaurantReserveTableRequestTO restaurantReserveTableRequestTO) {
		try{
			reserveTableSLHelper.reservationSLHelper(restaurantReserveTableRequestTO);
		}catch(Exception e){
			ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=ReserveTableSLConstant.FAILURE; ResultSLHelper.ERROR=ReserveTableSLConstant.RVT_SLGEN_111;
		}
		
	}

}
