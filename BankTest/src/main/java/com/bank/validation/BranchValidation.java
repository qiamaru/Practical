package com.bank.validation;

import com.bank.exception.DemoAppException;

public class BranchValidation {

	private	BranchValidation(){
		throw new DemoAppException("Utility Class Only");
	}

	/**
	 * Validates a general item name string.
	 * 
	 * @param BranchName The string to validate.
	 * @throws DemoAppException if the item name is null or blank.
	 */
	public static void validateBranchNameNotNull(String branchName) {
		if (branchName == null || branchName.isBlank()) {
			throw new DemoAppException("Branch name must not be empty or spaces.");
		}
	}
}