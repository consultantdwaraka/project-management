/**
 * 
 */
package com.project.management.bootstrap.utils;

/**
 * @author n0315896
 *
 */
public enum StatusEnum {

	COMPLETED("Completed"), IN_PROGRESS("In Progress"), SUSPENDED("Suspended");
	String status;

	StatusEnum(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
