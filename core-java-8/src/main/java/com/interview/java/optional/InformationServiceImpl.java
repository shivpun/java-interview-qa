package com.interview.java.optional;

import java.util.List;

public class InformationServiceImpl implements InformationService {
	
	public Information processForPartner(List<Information> informations) {
		
		Flow deActivatedCustomerPartnerFlow = Flow.of(customerAsDeActivatedPartner(), informations)
												  .next(customerAsActivePartner(), informations)
												  .next(companyAllowed(), informations)
												  .ifPresent(companyAllowed(), informations)
												  .next(companyAsDeActivatedSupplier(), informations)
												  .next(companyAsActiveSupplier(), informations);
		
		return deActivatedCustomerPartnerFlow.process().orElse(new Information(null, null, "ACTIVATED"));
	}
}
