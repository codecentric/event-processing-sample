package de.codecentric;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.springframework.stereotype.Component;

/**
 * 
 * Consumes events from a kafka server and the topic 'eventChannel'.
 * Events are unmarshaled and send to the esper endpoint.
 * 
 */
@Component
public class EventChannelSubscribeRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("kafka:localhost:9092?zookeeperConnect=localhost:2181&topic=eventChannel&groupId=testEQL&autoOffsetReset=largest").log("${body}")
		.unmarshal().json().setBody().simple("${body.payload}")
		.to("esper://esper-dom");
		
	}

}
