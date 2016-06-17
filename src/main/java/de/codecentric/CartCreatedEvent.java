package de.codecentric;

import java.io.Serializable;

/**
 * A cart. E.g. a shopping cart.
 * Example event type. 
 * 
 * @author berthold.schulte@codecentric.de
 *
 */
public class CartCreatedEvent implements Serializable{

	private static final long serialVersionUID = -6915960334227425088L;
	private int totalAmount;

	public CartCreatedEvent(int amount) {
		this.totalAmount = amount;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

}
