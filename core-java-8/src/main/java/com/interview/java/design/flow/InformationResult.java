package com.interview.java.design.flow;

import java.io.Serializable;

public class InformationResult implements Serializable {

	private static final long serialVersionUID = 3009443622667376907L;
	
	private String action;
	
	private String reason;
	
	public InformationResult(String action, String reason) {
		super();
		this.action = action;
		this.reason = reason;
	}
	
	public static InformationResult of(String action, String reason) {
		return new InformationResult(action, reason);
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "InformationResult [action=" + action + ", reason=" + reason + "]";
	}
}
