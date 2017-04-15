package org.deltaa.superrduperr.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Ashish Gajabi
 * Model class for error message.
 */
@XmlRootElement
public class ErrorMessage {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
