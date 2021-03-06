package com.nexteducation.dependencyTool.annotations.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.nexteducation.dependencyTool.annotations.validator.Password;

public class PasswordConstraintValidator implements ConstraintValidator<Password, String> {

	@Override
	public void initialize(Password password) {
		System.out.println("**************" + password.message() + "***********");
	}

	@Override
	public boolean isValid(String passwordfield, ConstraintValidatorContext context) {
		if (passwordfield == null)
			return false;

		return passwordfield.matches("[1-9]*");
//		return passwordfield.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_]).{6,20})");
	}

}
