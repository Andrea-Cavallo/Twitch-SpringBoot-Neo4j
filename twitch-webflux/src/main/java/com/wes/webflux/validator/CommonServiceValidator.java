package com.wes.webflux.validator;

import java.util.Objects;

public abstract class CommonServiceValidator {

	protected CommonServiceValidator() {

	}

	protected static boolean equals(String expectedName, String currentName) {
		return Objects.equals(expectedName, currentName);
	}

	protected static boolean equalsObj(Object expectedName, Object currentName) {
		return Objects.equals(expectedName, currentName);
	}
}
