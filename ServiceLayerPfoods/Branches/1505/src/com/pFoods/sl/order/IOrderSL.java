package com.pFoods.sl.order;

import com.pFoods.dbo.admin.to.AdminResponseTo;
import com.pFoods.dbo.order.to.CheckInRequestTO;
import com.pFoods.dbo.order.to.OrderRequestTO;

public interface IOrderSL {

	public void createOrder(OrderRequestTO orderRequestTO);
	public AdminResponseTo adminInfoSL();
	public void checkInSL(CheckInRequestTO checkInRequestTO);
	
}
