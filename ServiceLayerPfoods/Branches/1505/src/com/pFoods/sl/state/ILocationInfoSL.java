package com.pFoods.sl.state;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;

import com.pFoods.dbo.state.StateModal.State;

public interface ILocationInfoSL {
	public List<State> retriveLocationList();

	AbstractApplicationContext getAppContext();
}
