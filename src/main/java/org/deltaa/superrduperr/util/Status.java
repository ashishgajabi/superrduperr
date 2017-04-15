package org.deltaa.superrduperr.util;

/**
 * @author Ashish Gajabi
 * Enum class to maintain status of TODO items
 *
 */
public enum Status {
	
	Completed ("Completed"), Open ("Open");
	
	public String status;
	
	Status(String status){
		this.status = status;
	}
	
	@Override
	public String toString() {
		return this.status;
	}
	
}
