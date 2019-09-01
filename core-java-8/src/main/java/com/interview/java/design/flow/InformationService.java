package com.interview.java.design.flow;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface InformationService {
	
	Predicate<Information> isCustomer = information->information.getCustomerId()!=null;
	
	//Predicate<Information> isCompany = information->information.getCustomerId()==null;
	
	Predicate<Information> isActive = information->"Y".equals(information.getAction());
	
	Predicate<Information> isDeActivated = information->"N".equals(information.getAction());
	
	Predicate<Information> isPartner = information->"P".equals(information.getCustomerType());
	
	Predicate<Information> isSupplier = information->"S".equals(information.getCustomerType());
	
	Predicate<Information> isCompanyAllowed = isCustomer.negate().and(isActive.negate().or(isDeActivated.negate()));
	
	default Function<List<Information>,Optional<Information>> customerAsActivePartner() {
		return informations-> {
			Information information = informations.stream().filter(isActive.and(isCustomer).and(isPartner)).findFirst().orElse(null);
			Optional<Information> infOpt = Optional.ofNullable(information);
			Consumer<Information> consumer = (info)->System.out.println("Active customer found");
			infOpt.ifPresent(consumer);
			return infOpt;
		};
	}
	
	default Function<List<Information>,Optional<Information>> customerAsDeActivatedPartner() {
		return informations-> {
			Information information =  informations.stream().filter(isDeActivated.and(isCustomer).and(isPartner)).findFirst().orElse(null);
			Optional<Information> infOpt = Optional.ofNullable(information);
			Consumer<Information> consumer = (info)->System.out.println("customer has been DeActivated");
			infOpt.ifPresent(consumer);
			return infOpt;
		};
	}
	
	default Function<List<Information>,Optional<Information>> companyAsDeActivatedSupplier() {
		return informations->{
			Information information =  informations.stream().filter(isDeActivated.and(isCustomer.negate()).and(isSupplier)).findFirst().orElse(null);
			Optional<Information> infOpt = Optional.ofNullable(information);
			Consumer<Information> consumer = (info)->System.out.println("company has been DeActivated");
			infOpt.ifPresent(consumer);
			return infOpt;
		};
	}
	
	default Function<List<Information>,Optional<Information>> companyAsActiveSupplier() {
		return informations->{
			Information information =  informations.stream().filter(isActive.and(isCustomer.negate()).and(isSupplier)).findFirst().orElse(null);
			Optional<Information> infOpt = Optional.ofNullable(information);
			Consumer<Information> consumer = (info)->System.out.println("Active Company found");
			infOpt.ifPresent(consumer);
			return infOpt;
		};
	}
	
	default Function<List<Information>,Optional<Information>> companyAllowed() {
		return informations->{
			Information information =  informations.stream().filter(isCompanyAllowed).findFirst().orElse(new Information());
			Optional<Information> infOpt = Optional.ofNullable(information);
			Consumer<Information> consumer = (info)->System.out.println("Company is Allowed");
			infOpt.ifPresent(consumer);
			return infOpt;
		};
	}
}
