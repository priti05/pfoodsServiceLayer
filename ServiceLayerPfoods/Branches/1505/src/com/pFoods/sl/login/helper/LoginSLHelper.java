package com.pFoods.sl.login.helper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.support.AbstractApplicationContext;

import com.pFoods.dbo.doLogin.Login;
import com.pFoods.dbo.doLogin.constant.LoginConstants;
import com.pFoods.dbo.doLogin.to.CheckVerifiedStatusRequestTO;
import com.pFoods.dbo.result.ResultDBOHelper;
import com.pFoods.sl.login.To.ValidateLoginCredentialTO;
import com.pFoods.sl.login.constant.LoginSLConstants;
import com.pFoods.sl.result.ResultSLHelper;

public class LoginSLHelper {

	public boolean validateLoginCredHlp(ValidateLoginCredentialTO validateLoginCredentialTO, AbstractApplicationContext context) {
		/*
		 * This need to be override if we get success response from DBO layer
		 * By default it sets "LGN_SLGEN_111" which stands for "system Down"
		 */
		ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=LoginSLConstants.FAILURE; ResultSLHelper.ERROR=LoginSLConstants.LGN_SLGEN_111;
		try{
			if(validateLoginCredentialTO!=null){
				String userID = validateLoginCredentialTO.getUserID();
				String password=validateLoginCredentialTO.getPassword();
				if(StringUtils.isNotBlank(userID) && StringUtils.isNotBlank(password)){
					if(context!=null){
						Login loginDBO = (Login)context.getBean("login");
						boolean validated = loginDBO.retrievePassword(userID, password);
						/*
						 * 	in case of error return in above call, passFromDBO should be returned null
						 */
							if(validated){
								if(ResultDBOHelper.SUCCESS){
									ResultSLHelper.SUCCESS = true; ResultSLHelper.RESULT=LoginSLConstants.SUCCESS; ResultSLHelper.ERROR=LoginSLConstants.SL_000;
									return true;
								}
							}else{
								if(ResultDBOHelper.ERROR==LoginConstants.LGN_DBO_100){
									//UID not found
									 ResultSLHelper.ERROR=LoginSLConstants.LGN_SL_102;
								}else if(ResultDBOHelper.ERROR==LoginConstants.LGN_DBO_101){
									//invalid input
									 ResultSLHelper.ERROR=LoginSLConstants.LGN_SL_101;
								}else if(ResultDBOHelper.ERROR==LoginConstants.LGN_DBO_900){
									//UID and Pass doesn't match
									 ResultSLHelper.ERROR=LoginSLConstants.LGN_SL_103;
								}else if(ResultDBOHelper.ERROR==LoginConstants.LGN_DBO_901){
									//soft lock
									ResultSLHelper.ERROR=LoginSLConstants.LGN_SL_901;
								}else if(ResultDBOHelper.ERROR==LoginConstants.LGN_DBO_902){
									// hard lock
									ResultSLHelper.ERROR=LoginSLConstants.LGN_SL_902;
								}else if(ResultDBOHelper.ERROR==LoginConstants.LGN_DBO_904){
									//warning of soft lock and hard lock
									ResultSLHelper.ERROR=LoginSLConstants.LGN_SL_904;
								}								
							}												
					}
				}else{
					//invalid input
					ResultSLHelper.ERROR=LoginSLConstants.LGN_SL_101;
				}
			}else{
				//invalid input
				ResultSLHelper.ERROR=LoginSLConstants.LGN_SL_101;
			}
			
			
			
		}catch(Exception e){
			
		}
		return false;
	}

	public boolean checkVerifiedStatusSLHelper(CheckVerifiedStatusRequestTO checkVerifiedStatusRequestTO,
			AbstractApplicationContext context) {
		ResultSLHelper.SUCCESS = false; ResultSLHelper.RESULT=LoginSLConstants.FAILURE; ResultSLHelper.ERROR=LoginSLConstants.LGN_SLGEN_111;
		try{
			if(checkVerifiedStatusRequestTO!=null){
				String userID = checkVerifiedStatusRequestTO.getUid();
				if(StringUtils.isNotBlank(userID)){
					if(context!=null){
						Login loginDBO = (Login)context.getBean("login");
						boolean validated = loginDBO.checkVerifiedStatus(checkVerifiedStatusRequestTO);
						/*
						 * 	in case of error return in above call, passFromDBO should be returned null
						 */
							if(validated){
								if(ResultDBOHelper.SUCCESS){
									ResultSLHelper.SUCCESS = true; ResultSLHelper.RESULT=LoginSLConstants.SUCCESS; ResultSLHelper.ERROR=LoginSLConstants.SL_000;
									return true;
								}
							}else if(ResultDBOHelper.ERROR==LoginConstants.LGN_DBO_101){
									//invalid input
									 ResultSLHelper.ERROR=LoginSLConstants.LGN_SL_101;
								}								
							}												
					}else{
						//invalid input
						ResultSLHelper.ERROR=LoginSLConstants.LGN_SL_101;
					}
				}else{
					//invalid input
					ResultSLHelper.ERROR=LoginSLConstants.LGN_SL_101;
				}
		}catch(Exception e){
			
		}
		return false;
	}

}
