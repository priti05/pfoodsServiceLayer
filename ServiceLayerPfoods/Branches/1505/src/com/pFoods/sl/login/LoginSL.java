package com.pFoods.sl.login;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pFoods.dbo.doLogin.to.CheckVerifiedStatusRequestTO;
import com.pFoods.sl.login.To.ValidateLoginCredentialTO;
import com.pFoods.sl.login.constant.LoginSLConstants;
import com.pFoods.sl.login.helper.LoginSLHelper;
import com.pFoods.sl.result.ResultSLHelper;


public class LoginSL implements ILoginSL{

	@Override
	public AbstractApplicationContext getAppContext() {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(LoginSLConstants.SpringXML);
		context.registerShutdownHook();
		return context;	
	}

	@Override
	public boolean validateLoginCredential(ValidateLoginCredentialTO validateLoginCredentialTO) {
		boolean validated=false;
		try{
			ResultSLHelper.clearResult();
			AbstractApplicationContext context = getAppContext();
			LoginSLHelper loginSLHelper  = (LoginSLHelper)context.getBean("loginSLHelper");
			validated  = loginSLHelper.validateLoginCredHlp(validateLoginCredentialTO,context);
		}catch(Exception e){
			ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=LoginSLConstants.FAILURE; ResultSLHelper.ERROR=LoginSLConstants.LGN_SLGEN_111;
		}
		return validated;	
	}

	@Override
	public boolean checkVerifiedStatusSL(CheckVerifiedStatusRequestTO checkVerifiedStatusRequestTO) {
		boolean validated=false;
		try{
			ResultSLHelper.clearResult();
			AbstractApplicationContext context = getAppContext();
			LoginSLHelper loginSLHelper  = (LoginSLHelper)context.getBean("loginSLHelper");
			validated  = loginSLHelper.checkVerifiedStatusSLHelper(checkVerifiedStatusRequestTO,context);
		}catch(Exception e){
			ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=LoginSLConstants.FAILURE; ResultSLHelper.ERROR=LoginSLConstants.LGN_SLGEN_111;
		}
		return validated;
	}

}
