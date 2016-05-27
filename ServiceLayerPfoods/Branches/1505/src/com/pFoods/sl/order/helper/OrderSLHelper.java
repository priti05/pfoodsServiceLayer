package com.pFoods.sl.order.helper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.pFoods.dbo.admin.to.AdminResponseTo;
import com.pFoods.dbo.order.IOrder;
import com.pFoods.dbo.order.constant.OrderConstant;
import com.pFoods.dbo.order.to.CheckInRequestTO;
import com.pFoods.dbo.order.to.OrderRequestTO;
import com.pFoods.dbo.result.ResultDBOHelper;
import com.pFoods.sl.order.constant.OrderSLConstant;
import com.pFoods.sl.result.ResultSLHelper;

public class OrderSLHelper {
	
	@Autowired
	private IOrder order;

	public void createOrderHelper(OrderRequestTO orderRequestTO) {
		ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=OrderSLConstant.FAILURE; ResultSLHelper.ERROR=OrderSLConstant.ORD_SLGEN_111;
		if(orderRequestTO!=null){
			order.enterOrder(orderRequestTO);
			if(ResultDBOHelper.SUCCESS){
				//SUCCESS CALL
				ResultSLHelper.SUCCESS = true; ResultSLHelper.RESULT=OrderSLConstant.SUCCESS; ResultSLHelper.ERROR=OrderSLConstant.SL_000;
			}else if(StringUtils.equalsIgnoreCase(ResultDBOHelper.ERROR, OrderConstant.ORD_DBO_101 )){
				//INVALID INPUT
				ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=OrderSLConstant.FAILURE; ResultSLHelper.ERROR=OrderSLConstant.ORD_SL_101;
			}else if(StringUtils.equalsIgnoreCase(ResultDBOHelper.ERROR, OrderConstant.ORD_GEN_111 )){
				//SYSTEM DOWN
				ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=OrderSLConstant.FAILURE; ResultSLHelper.ERROR=OrderSLConstant.ORD_SLGEN_111;
			}			
		}else{
			//INVALID INPUT
			ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=OrderSLConstant.FAILURE; ResultSLHelper.ERROR=OrderSLConstant.ORD_SL_101;
		}
		
	}

	public AdminResponseTo adminInfoSLHelper() {
		AdminResponseTo adminResponseTo = null;
		ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=OrderSLConstant.FAILURE; ResultSLHelper.ERROR=OrderSLConstant.ORD_SLGEN_111;
		
		adminResponseTo = order.adminInfoDBO();
			if(ResultDBOHelper.SUCCESS){
				//SUCCESS CALL
				ResultSLHelper.SUCCESS = true; ResultSLHelper.RESULT=OrderSLConstant.SUCCESS; ResultSLHelper.ERROR=OrderSLConstant.SL_000;
			}			
		return adminResponseTo;
	}

	public void checkInSLHelper(CheckInRequestTO checkInRequestTO) {
		
		order.checkInDBO(checkInRequestTO);

		
	}

	

}
