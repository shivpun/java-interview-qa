package com.interview.java.optional;

import java.io.Serializable;

/*
 * customerId: ==NULL=>Company, !=NULL=>Customer
 * customerType: P=>Partner, S=>Supplier.
 * action: Y=>Active, N=>De-Activated.
 */

public class Information implements Serializable {

	private static final long serialVersionUID = -9193953827408704407L;

	private String customerId;

	private String customerType;

	private String action;

	public Information() {
	}

	public Information(String customerId, String customerType, String action) {
		setCustomerId(customerId);
		setCustomerType(customerType);
		setAction(action);
	}

	public static Information of(String customerId, String customerType, String action) {
		return new Information(customerId, customerType, action);
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public String toString() {
		return "Information [customerId=" + customerId + ", customerType=" + customerType + ", action=" + action + "]";
	}
}
