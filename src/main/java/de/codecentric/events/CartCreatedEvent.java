package de.codecentric.events;

import java.io.Serializable;

/**
 * 
 * Example event
 * 
 * A cart. E.g. a shopping cart.
 * 
 */
public class CartCreatedEvent implements Serializable {

	private static final long serialVersionUID = -6915960334227425088L;
	private int totalAmount;
	private String billingCountry;

	/**
	 * Immutable event with amount and billingCountry
	 * 
	 * @param amount total amount of the Cart
	 * @param billingCountry country of the billing
	 */
	public CartCreatedEvent(int amount, String billingCountry) {
		this.totalAmount = amount;
		this.billingCountry = billingCountry;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public String getBillingCountry() {
		return billingCountry;
	}

}
