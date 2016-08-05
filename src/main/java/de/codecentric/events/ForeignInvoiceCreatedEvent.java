package de.codecentric.events;

import java.io.Serializable;

/**
 * 
 * Example event
 * 
 * An invoice for a company in foreign country
 * 
 */

public class ForeignInvoiceCreatedEvent implements Serializable {

	private static final long serialVersionUID = -1804130519890607683L;

	private int totalAmount;
	private String billingCountry;

	/**
	 * Immutable event with amount and billing for an invoice with an foreign
	 * billing country
	 * 
	 * @param totalAmount
	 * @param billingCountry
	 */
	public ForeignInvoiceCreatedEvent(int totalAmount, String billingCountry) {
		this.totalAmount = totalAmount;
		this.billingCountry = billingCountry;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public String getBillingCountry() {
		return billingCountry;
	}

}
