package com.pFoods.sl.event.helper;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.pFoods.dbo.event.IEvent;
import com.pFoods.dbo.event.constant.EventConstant;
import com.pFoods.dbo.event.to.ContactBookRequestTO;
import com.pFoods.dbo.event.to.CreateEventRequestTO;
import com.pFoods.dbo.event.to.EventListDetailsRequestTO;
import com.pFoods.dbo.event.to.EventListDetailsResponseTO;
import com.pFoods.dbo.event.to.UpdateStatusRequestTO;
import com.pFoods.dbo.event.vo.ContactBookVO;
import com.pFoods.dbo.result.ResultDBOHelper;
import com.pFoods.sl.event.constant.EventSLConstant;
import com.pFoods.sl.result.ResultSLHelper;

public class EventSLHelper {
	
	@Autowired
	private IEvent event;

	public void createEventSLHelper(CreateEventRequestTO createEventRequestTO) {
		ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=EventSLConstant.FAILURE; ResultSLHelper.ERROR=EventSLConstant.EVT_SLGEN_111;
		if(createEventRequestTO!=null){
			event.createEvent(createEventRequestTO);
			if(ResultDBOHelper.SUCCESS){
				//SUCCESS CALL
				ResultSLHelper.SUCCESS = true; ResultSLHelper.RESULT=EventSLConstant.SUCCESS; ResultSLHelper.ERROR=EventSLConstant.SL_000;
			}else if(StringUtils.equalsIgnoreCase(ResultDBOHelper.ERROR, EventConstant.EVT_DBO_101 )){
				//INVALID INPUT
				ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=EventSLConstant.FAILURE; ResultSLHelper.ERROR=EventSLConstant.EVT_SL_101;
			}else if(StringUtils.equalsIgnoreCase(ResultDBOHelper.ERROR, EventConstant.EVT_GEN_111 )){
				//SYSTEM DOWN
				ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=EventSLConstant.FAILURE; ResultSLHelper.ERROR=EventSLConstant.EVT_SLGEN_111;
			}			
		}else{
			//INVALID INPUT
			ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=EventSLConstant.FAILURE; ResultSLHelper.ERROR=EventSLConstant.EVT_SL_101;
		}
		
	}

	public EventListDetailsResponseTO manageEventHelper(EventListDetailsRequestTO eventListDetailsRequestTO) {
		ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=EventSLConstant.FAILURE; ResultSLHelper.ERROR=EventSLConstant.EVT_SLGEN_111;
		EventListDetailsResponseTO eventListDetailsResponseTO = null;
		if(eventListDetailsRequestTO!=null){
			eventListDetailsResponseTO = event.retrieveEventList(eventListDetailsRequestTO);
			if(ResultDBOHelper.SUCCESS){
				//SUCCESS CALL
				ResultSLHelper.SUCCESS = true; ResultSLHelper.RESULT=EventSLConstant.SUCCESS; ResultSLHelper.ERROR=EventSLConstant.SL_000;
			}else if(StringUtils.equalsIgnoreCase(ResultDBOHelper.ERROR, EventConstant.EVT_DBO_101 )){
				//INVALID INPUT
				ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=EventSLConstant.FAILURE; ResultSLHelper.ERROR=EventSLConstant.EVT_SL_101;
			}else if(StringUtils.equalsIgnoreCase(ResultDBOHelper.ERROR, EventConstant.EVT_GEN_111 )){
				//SYSTEM DOWN
				ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=EventSLConstant.FAILURE; ResultSLHelper.ERROR=EventSLConstant.EVT_SLGEN_111;
			}			
		}else{
			//INVALID INPUT
			ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=EventSLConstant.FAILURE; ResultSLHelper.ERROR=EventSLConstant.EVT_SL_101;
		}
		
		return eventListDetailsResponseTO;
	}

	public List<ContactBookVO> checkWhoIsUserSLHelper(ContactBookRequestTO contactBookRequestTO) {
		ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=EventSLConstant.FAILURE; ResultSLHelper.ERROR=EventSLConstant.EVT_SLGEN_111;
		List<ContactBookVO>  contactBookVOList= null;
		if(contactBookRequestTO!=null){
			contactBookVOList = event.checkWhoIsUser(contactBookRequestTO);
			if(ResultDBOHelper.SUCCESS){
				//SUCCESS CALL
				ResultSLHelper.SUCCESS = true; ResultSLHelper.RESULT=EventSLConstant.SUCCESS; ResultSLHelper.ERROR=EventSLConstant.SL_000;
			}else if(StringUtils.equalsIgnoreCase(ResultDBOHelper.ERROR, EventConstant.EVT_DBO_101 )){
				//INVALID INPUT
				ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=EventSLConstant.FAILURE; ResultSLHelper.ERROR=EventSLConstant.EVT_SL_101;
			}else if(StringUtils.equalsIgnoreCase(ResultDBOHelper.ERROR, EventConstant.EVT_GEN_111 )){
				//SYSTEM DOWN
				ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=EventSLConstant.FAILURE; ResultSLHelper.ERROR=EventSLConstant.EVT_SLGEN_111;
			}			
		}else{
			//INVALID INPUT
			ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=EventSLConstant.FAILURE; ResultSLHelper.ERROR=EventSLConstant.EVT_SL_101;
		}
		
		return contactBookVOList;
	}

	public void updateStatusSLHelper(UpdateStatusRequestTO updateStatusRequestTO) {
		// TODO Auto-generated method stub
		event.updateStatus(updateStatusRequestTO);
	}

}
