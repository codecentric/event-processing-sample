package de.codecentric;

import java.io.Serializable;

/**
 * Event structure containing the payload of an event and the event meta-data
 */
public class Event implements Serializable {

	private static final long serialVersionUID = 8548381377946319542L;

	private String timestamp;
	private String type;
	private Object payload;

	public Event(String type, String timestamp, Object payload) {
		this.type = type;
		this.timestamp = timestamp;
		this.payload = payload;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getType() {
		return type;
	}

	public Object getPayload() {
		return payload;
	}

}