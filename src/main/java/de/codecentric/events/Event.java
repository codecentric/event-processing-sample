package de.codecentric.events;

import java.util.UUID;

/**
 * Event structure containing the payload of an event and the event's meta-data
 */
public class Event {

	private String timestamp;
	private String type;
	private Object payload;
	private String id;
	private String source;

	/**
	 * Immutable event with meta-data and payload
	 * 
	 * @param source
	 * @param type
	 * @param format
	 * @param payload
	 */
	public Event(String source, String type, String timestamp, Object payload) {
		this.source = source;
		this.type = type;
		this.timestamp = timestamp;
		this.payload = payload;
		this.id = UUID.randomUUID().toString();
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

	public String getId() {
		return id;
	}

	public String getSource() {
		return source;
	}
}