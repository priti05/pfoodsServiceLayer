package com.pFoods.sl.registration.To;

import org.springframework.beans.factory.annotation.Autowired;

import com.pFoods.dbo.userProfile.TO.RegistrationDBORequestTO;
import com.pFoods.dbo.userProfile.TO.UpdateAddressDBOTO;

public class RegistrationSLRequestTO {

	@Autowired
	private RegistrationDBORequestTO registrationDBORequestTO;
	
	@Autowired
	private UpdateAddressDBOTO updateAddressDBOTo;

	public RegistrationDBORequestTO getRegistrationDBORequestTO() {
		return registrationDBORequestTO;
	}

	public void setRegistrationDBORequestTO(RegistrationDBORequestTO registrationDBORequestTO) {
		this.registrationDBORequestTO = registrationDBORequestTO;
	}

	public UpdateAddressDBOTO getUpdateAddressDBOTo() {
		return updateAddressDBOTo;
	}

	public void setUpdateAddressDBOTo(UpdateAddressDBOTO updateAddressDBOTo) {
		this.updateAddressDBOTo = updateAddressDBOTo;
	}

	
	
	
	
	
}
