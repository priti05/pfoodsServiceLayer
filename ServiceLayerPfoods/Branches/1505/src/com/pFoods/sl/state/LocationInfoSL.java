package com.pFoods.sl.state;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pFoods.dbo.state.StateModal.State;
import com.pFoods.sl.result.ResultSLHelper;
import com.pFoods.sl.state.Helper.LocationInfoHelperSL;
import com.pFoods.sl.state.constant.LocationInfoConstantSL;

public class LocationInfoSL implements ILocationInfoSL{

	@Override
	public AbstractApplicationContext getAppContext() {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(LocationInfoConstantSL.SpringXML);
		context.registerShutdownHook();
		return context;	
	}
	
	@Override
	public List<State> retriveLocationList() {
		List<State> stateList= null;
		try{
			ResultSLHelper.clearResult();
			AbstractApplicationContext context = getAppContext();
			LocationInfoHelperSL locationInfoHelperSL  = (LocationInfoHelperSL)context.getBean("locationInfoHelperSL");
			stateList  = locationInfoHelperSL.retriveLocationInfo(context);
		}catch(Exception e){
			ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=LocationInfoConstantSL.FAILURE; ResultSLHelper.ERROR=LocationInfoConstantSL.LCT_SLGEN_111;
		}
		return stateList;
	}

}
