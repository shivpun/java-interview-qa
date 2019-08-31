package com.interview.java.design.flow;

import java.util.List;

public class InformationServiceImpl implements InformationService {
	
	public InformationResult processForPartner(List<Information> informations) throws IllegalAccessException {
		
		Flow deActivatedCustomerPartnerFlow = Flow.of(customerAsDeActivatedPartner(), informations, new InformationResult("PARTNER_DEACTIVATED", null))
												  .orNext(customerAsActivePartner(), informations,  new InformationResult("PARTNER_ACTIVATED", "Can't Disclose"))
												  .orNext(companyAllowed(), informations,  new InformationResult("COMPANY_ACTIVATED", null))
												  .ifPresent(companyAsDeActivatedSupplier(), informations,  new InformationResult("SUPPLIER_DEACTIVATED", null))
												  .orNext(companyAsActiveSupplier(), informations, new InformationResult("SUPPLIER_ACTIVATED", null))
												  .end();
		
		return deActivatedCustomerPartnerFlow.process().orElse(new InformationResult("ACTIVATED", null));
	}
}
