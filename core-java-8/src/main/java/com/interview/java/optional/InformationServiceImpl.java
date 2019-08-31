package com.interview.java.optional;

import java.util.List;

public class InformationServiceImpl implements InformationService {
	
	public InformationResult processForPartner(List<Information> informations) {
		
		Flow deActivatedCustomerPartnerFlow = Flow.of(customerAsDeActivatedPartner(), informations, new InformationResult("PARTNER_DEACTIVATED", null))
												  .next(customerAsActivePartner(), informations,  new InformationResult("PARTNER_ACTIVATED", "Can't Disclose"))
												  .next(companyAllowed(), informations,  new InformationResult("COMPANY_ACTIVATED", null))
												  .ifPresent(companyAsDeActivatedSupplier(), informations,  new InformationResult("SUPPLIER_DEACTIVATED", null))
												  .next(companyAsActiveSupplier(), informations, new InformationResult("SUPPLIER_ACTIVATED", null))
												  .end();
		
		return deActivatedCustomerPartnerFlow.process().orElse(new InformationResult("ACTIVATED", null));
	}
}
