package com.pFoods.sl.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pFoods.dbo.event.to.ContactBookRequestTO;
import com.pFoods.dbo.event.to.CreateEventRequestTO;
import com.pFoods.dbo.event.to.EventListDetailsRequestTO;
import com.pFoods.dbo.event.to.EventListDetailsResponseTO;
import com.pFoods.dbo.event.to.UpdateStatusRequestTO;
import com.pFoods.dbo.event.vo.ContactBookVO;
import com.pFoods.sl.event.constant.EventSLConstant;
import com.pFoods.sl.event.helper.EventSLHelper;
import com.pFoods.sl.result.ResultSLHelper;

public class EventSL implements IEventSL {

	@Autowired
	private EventSLHelper eventSLHelper;
	
	
	@Override
	public void createEventSL(CreateEventRequestTO createEventRequestTO) {
		try{
			eventSLHelper.createEventSLHelper(createEventRequestTO);
		}catch(Exception e){
			ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=EventSLConstant.FAILURE; ResultSLHelper.ERROR=EventSLConstant.EVT_SLGEN_111;
		}
		
	}

	@Override
	public EventListDetailsResponseTO manageEvent(EventListDetailsRequestTO eventListDetailsRequestTO) {
		EventListDetailsResponseTO eventListDetailsResponseTO=null;
		try{
			eventListDetailsResponseTO = eventSLHelper.manageEventHelper(eventListDetailsRequestTO);
		}catch(Exception e){
			ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=EventSLConstant.FAILURE; ResultSLHelper.ERROR=EventSLConstant.EVT_SLGEN_111;
		}
		return eventListDetailsResponseTO;
	}

	@Override
	public List<ContactBookVO> checkWhoIsUserSL(ContactBookRequestTO ContactBookRequestTO) {
		List<ContactBookVO> contactBookVOList=null;
		try{
			contactBookVOList = eventSLHelper.checkWhoIsUserSLHelper(ContactBookRequestTO);
		}catch(Exception e){
			ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=EventSLConstant.FAILURE; ResultSLHelper.ERROR=EventSLConstant.EVT_SLGEN_111;
		}
		return contactBookVOList;
	}

	@Override
	public void updateStatusSL(UpdateStatusRequestTO updateStatusRequestTO) {
		// TODO Auto-generated method stub
		try{
			eventSLHelper.updateStatusSLHelper(updateStatusRequestTO);
		}catch(Exception e){
			ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=EventSLConstant.FAILURE; ResultSLHelper.ERROR=EventSLConstant.EVT_SLGEN_111;
		}
	}

}
