package de.codecentric.events;

import java.io.Serializable;

/**
 * 
 * Example event
 * 
 * Indicates a low conversion rate
 * 
 */

public class LowConversionEvent implements Serializable{

	private static final long serialVersionUID = -6359339927415113604L;
	
	private Double averageAmount;

	/**
	 * Immutable event 
	 * @param amount average amount of low conversions
	 */
	public LowConversionEvent(Double amount){
		this.averageAmount = amount;
		
	}
	public Double getAverageAmount() {
		return averageAmount;
	}

}
