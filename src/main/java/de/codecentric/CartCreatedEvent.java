package de.codecentric;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeInfo.As;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;
import org.codehaus.jackson.map.annotate.JsonRootName;

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
