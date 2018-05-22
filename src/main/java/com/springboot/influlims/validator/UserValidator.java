package com.springboot.influlims.validator;

import com.springboot.influlims.entity.UserEntity;
import com.springboot.influlims.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> aClass) {
		return UserEntity.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		UserEntity user = (UserEntity) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "Required");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
		if(user.getPassword().length() < 8) {
			errors.rejectValue("password", "Size.userForm.password");
		}
	}
}
