package org.jsp.userbootapp.exception;

@SuppressWarnings("serial")
public class IdNotFoundException extends RuntimeException {
	@Override
	public String getMessage() {
		return "Invalid Id";
	}
}
	
	


