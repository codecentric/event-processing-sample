package de.codecentric;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.Body;
import org.apache.camel.Handler;
import org.springframework.stereotype.Component;

import com.espertech.esper.client.EventBean;

/**
 * Creates Events from different payloads in a camel route.
 */
@Component
public class EventFactory {

	private final DateFormat dateFormat = SimpleDateFormat.getDateTimeInstance();

	/**
	 * Creates events based on the given esper event
	 * The esper event is reduced to payload and meta-data.
	 * 
	 * @param body an esper event
	 * @return an event with payload and metadata
	 */
	public Event createFromEsperEvent(@Body EventBean body) {
		return new Event(body.getEventType().getName(), dateFormat.format(new Date()), body.getUnderlying());
	}

	
	/**
	 * Creates events based on the given body.
	 * 
	 * @param body payload of the event
	 * @return an event with payload and metadata
	 */
	@Handler
	public Event create(@Body Object body) {
		return new Event(body.getClass().getName(), dateFormat.format(new Date()), body);
	}
}