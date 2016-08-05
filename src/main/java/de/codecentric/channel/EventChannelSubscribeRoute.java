package de.codecentric.channel;

import org.apache.camel.builder.RouteBuilder;
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
		from("kafka:{{kafka.host}}:{{kafka.port}}?zookeeperConnect={{zookeeper.host}}:{{zookeeper.port}}&topic=eventChannel&groupId=testEQL&autoOffsetReset=largest").log("${body}")
		.unmarshal().json().setBody().simple("${body.payload}")
		.to("esper://esper");
		
	}

}
