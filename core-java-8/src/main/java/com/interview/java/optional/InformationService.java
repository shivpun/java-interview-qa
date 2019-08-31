package com.interview.java.optional;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface InformationService {
	
	Predicate<Information> isCustomer = information->information.getCustomerId()!=null;
	
	Predicate<Information> isCompany = information->information.getCustomerId()==null;
	
	Predicate<Information> isActive = information->"Y".equals(information.getAction());
	
	Predicate<Information> isDeActivated = information->"N".equals(information.getAction());
	
	Predicate<Information> isPartner = information->"P".equals(information.getCustomerType());
	
	Predicate<Information> isSupplier = information->"S".equals(information.getCustomerType());
	
	Predicate<Information> isCompanyAllowed = isCompany.and(isActive.negate().or(isDeActivated.negate()));
	
	default Function<List<Information>,Optional<Information>> customerAsActivePartner() {
		return informations-> {
			Optional<Information> information = informations.stream().filter(isActive.and(isCustomer).and(isPartner)).findFirst();
			information.orElse(null);
			Consumer<Information> consumer = (info)->System.out.println("Active customer found");
			information.ifPresent(consumer);
			return information;
		};
	}
	
	default Function<List<Information>,Optional<Information>> customerAsDeActivatedPartner() {
		return informations-> {
			Optional<Information> information =  informations.stream().filter(isDeActivated.and(isCustomer).and(isPartner)).findFirst();
			information.orElse(null);
			Consumer<Information> consumer = (info)->System.out.println("customer has been DeActivated");
			information.ifPresent(consumer);
			return information;
		};
	}
	
	default Function<List<Information>,Optional<Information>> companyAsDeActivatedSupplier() {
		return informations->{
			Optional<Information> information =  informations.stream().filter(isDeActivated.and(isCompany).and(isSupplier)).findFirst();
			information.orElse(null);
			Consumer<Information> consumer = (info)->System.out.println("company has been DeActivated");
			information.ifPresent(consumer);
			return information;
		};
	}
	
	default Function<List<Information>,Optional<Information>> companyAsActiveSupplier() {
		return informations->{
			Optional<Information> information =  informations.stream().filter(isActive.and(isCompany).and(isSupplier)).findFirst();
			information.orElse(null);
			Consumer<Information> consumer = (info)->System.out.println("Active Company found");
			information.ifPresent(consumer);
			return information;
		};
	}
	
	default Function<List<Information>,Optional<Information>> companyAllowed() {
		return informations->{
			Optional<Information> information =  informations.stream().filter(isCompanyAllowed).findFirst();
			information.orElse(null);
			Consumer<Information> consumer = (info)->System.out.println("Company is Allowed");
			information.ifPresent(consumer);
			return information;
		};
	}
}
