package com.wes.webflux.validator;

import com.wes.webflux.controller.exception.InvalidPropertyException;
import com.wes.webflux.entity.User;

// to be implemented
public class UserValidator extends CommonServiceValidator {

	private UserValidator() {
		super();
	}

	public static void nameCantStartWithNumber(User user) {
		if (doesNameStartsWithNumber(user.getName()))
			throw new InvalidPropertyException(null, "Name can't start with Number");
	}
	
	
	private static boolean doesNameStartsWithNumber(String name) {
		
		if ( name.startsWith("1") || name.startsWith("2") ||
				name.startsWith("3") ||name.startsWith("4")||
				name.startsWith("5") ||name.startsWith("6") ||
				name.startsWith("7") ||name.startsWith("8")|| 
				name.startsWith("9") 
				) {
			return true;
		} else return false;
	}
}
