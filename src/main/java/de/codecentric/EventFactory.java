package de.codecentric;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.Body;
import org.springframework.stereotype.Component;

import com.espertech.esper.client.EventBean;

@Component
public class EventFactory{
	
	private final DateFormat dateFormat = SimpleDateFormat.getDateTimeInstance();
	
	public Event createFromEsperEvent(@Body EventBean body){
		System.out.println("EventBean  " + body.getUnderlying().getClass().getName());
		return new Event(body.getEventType().getName(),dateFormat.format(new Date()),body.getUnderlying());
	}
	
	public Event create(@Body Object body){
		return new Event(body.getClass().getName(),dateFormat.format(new Date()),body);
	}
}