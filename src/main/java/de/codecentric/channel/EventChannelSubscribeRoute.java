package de.codecentric.channel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * 
 * Consumes events from a kafka server and the topic 'eventChannel'. Events are
 * unmarshaled, filtered and send to the esper endpoint. Only events not created
 * by this application instance are sent to the esper endpoint.
 * 
 */
@Component
public class EventChannelSubscribeRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("kafka:{{kafka.host}}:{{kafka.port}}?zookeeperConnect={{zookeeper.host}}:{{zookeeper.port}}&topic=eventChannel&groupId=testEQL&autoOffsetReset=largest")
		.unmarshal().json()
		.filter().simple("${body.source} != ${properties:eventsource}")
		.setBody().simple("${body.payload}")
		.to("esper://esper");
		
	}

}
