package com.pFoods.sl.order;

import org.springframework.beans.factory.annotation.Autowired;

import com.pFoods.dbo.admin.to.AdminResponseTo;
import com.pFoods.dbo.order.to.CheckInRequestTO;
import com.pFoods.dbo.order.to.OrderRequestTO;
import com.pFoods.sl.order.constant.OrderSLConstant;
import com.pFoods.sl.order.helper.OrderSLHelper;
import com.pFoods.sl.result.ResultSLHelper;

public class OrderSL implements IOrderSL {

	@Autowired
	private OrderSLHelper orderSLHelper;
	
	@Override
	public void createOrder(OrderRequestTO orderRequestTO) {
		try{
			orderSLHelper.createOrderHelper(orderRequestTO);
		}catch(Exception e){
			ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=OrderSLConstant.FAILURE; ResultSLHelper.ERROR=OrderSLConstant.ORD_SLGEN_111;
		}
		
	}

	@Override
	public AdminResponseTo adminInfoSL() {
		AdminResponseTo adminResponseTo = null;
		try{
			adminResponseTo = orderSLHelper.adminInfoSLHelper();
		}catch(Exception e){
			ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=OrderSLConstant.FAILURE; ResultSLHelper.ERROR=OrderSLConstant.ORD_SLGEN_111;
		}
		return adminResponseTo;
	}

	@Override
	public void checkInSL(CheckInRequestTO checkInRequestTO) {
		try{
			orderSLHelper.checkInSLHelper(checkInRequestTO);
		}catch(Exception e){
			ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=OrderSLConstant.FAILURE; ResultSLHelper.ERROR=OrderSLConstant.ORD_SLGEN_111;
		}
		
	}

}
