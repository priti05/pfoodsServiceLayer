package com.pFoods.sl.login;

import org.springframework.context.support.AbstractApplicationContext;

import com.pFoods.dbo.doLogin.to.CheckVerifiedStatusRequestTO;
import com.pFoods.sl.login.To.ValidateLoginCredentialTO;

public interface ILoginSL {
	public AbstractApplicationContext getAppContext();
	
	/**
	 * This should be called to validate login credential.
	 * It returns false if user ID not available or password doesn't match
	 * with applicable error messages. 
	 * @param validateLoginCredentialTO
	 * @return true or false
	 * @exception
	 */
	public boolean validateLoginCredential(ValidateLoginCredentialTO validateLoginCredentialTO);
	public boolean checkVerifiedStatusSL(CheckVerifiedStatusRequestTO checkVerifiedStatusRequestTO);
}
