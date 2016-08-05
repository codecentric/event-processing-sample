package de.codecentric;

/**
 * Event structure containing the payload of an event and the event's meta-data
 */
public class Event {

	private String timestamp;
	private String type;
	private Object payload;

	/**
	 * Immutable event with meta-data and payload
	 * 
	 * @param type
	 * @param timestamp
	 * @param payload
	 */
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