package com.pFoods.sl.event;

import java.util.List;

import com.pFoods.dbo.event.to.ContactBookRequestTO;
import com.pFoods.dbo.event.to.CreateEventRequestTO;
import com.pFoods.dbo.event.to.EventListDetailsRequestTO;
import com.pFoods.dbo.event.to.EventListDetailsResponseTO;
import com.pFoods.dbo.event.to.UpdateStatusRequestTO;
import com.pFoods.dbo.event.vo.ContactBookVO;

public interface IEventSL {

	public void createEventSL(CreateEventRequestTO createEventRequestTO);
	public EventListDetailsResponseTO manageEvent(EventListDetailsRequestTO eventListDetailsRequestTO);
	public List<ContactBookVO> checkWhoIsUserSL(ContactBookRequestTO ContactBookRequestTO);
	public void updateStatusSL(UpdateStatusRequestTO updateStatusRequestTO);
	
}
