package de.codecentric;

import java.io.Serializable;

public class ForeignInvoiceCreatedEvent implements Serializable{

	private static final long serialVersionUID = -1804130519890607683L;

	private int totalAmount;
	private String billingCountry;
	private String type = this.getClass().getCanonicalName();

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
