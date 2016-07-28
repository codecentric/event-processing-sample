package de.codecentric;

import java.io.Serializable;

/**
 * 
 * Example event
 * 
 * Indicates a low conversion rate
 * 
 */

public class LowConversion implements Serializable{

	private static final long serialVersionUID = -6359339927415113604L;
	
	private Double averageAmount;

	public LowConversion(Double amount){
		this.averageAmount = amount;
		
	}
	public Double getAverageAmount() {
		return averageAmount;
	}

}
