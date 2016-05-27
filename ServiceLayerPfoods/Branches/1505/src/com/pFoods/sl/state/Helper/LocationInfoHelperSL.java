package com.pFoods.sl.state.Helper;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.support.AbstractApplicationContext;

import com.pFoods.dbo.result.ResultDBOHelper;
import com.pFoods.dbo.state.LocationInfoDBO;
import com.pFoods.dbo.state.StateModal.State;
import com.pFoods.sl.result.ResultSLHelper;
import com.pFoods.sl.state.constant.LocationInfoConstantSL;

public class LocationInfoHelperSL {

	public List<State> retriveLocationInfo(AbstractApplicationContext context) {
		ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=LocationInfoConstantSL.FAILURE; ResultSLHelper.ERROR=LocationInfoConstantSL.LCT_SLGEN_111;
		
		LocationInfoDBO  locationInfoDBO = (LocationInfoDBO)context.getBean("locationInfoDBO");
		List<State> stateList = locationInfoDBO.retrieveStateList();
		if(ResultDBOHelper.SUCCESS && StringUtils.equalsIgnoreCase(ResultDBOHelper.RESULT, LocationInfoConstantSL.SUCCESS)){
			ResultSLHelper.SUCCESS = true; ResultSLHelper.RESULT=LocationInfoConstantSL.SUCCESS; ResultSLHelper.ERROR=LocationInfoConstantSL.SL_000;
		}
		return stateList;
	}

}
