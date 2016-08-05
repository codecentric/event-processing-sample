package de.codecentric.channel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.springframework.stereotype.Component;

import de.codecentric.events.EventFactory;

/**
 * Publish Events created from Esper events to a kafka server and the topic 'eventChannel'.
 */
@Component
public class EventChannelPublishRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("seda:eventChannel")
		.bean(EventFactory.class, "createFromEsperEvent").marshal().json().convertBodyTo(String.class).setHeader(KafkaConstants.PARTITION_KEY)
		.simple(".").setHeader(KafkaConstants.KEY).simple("1")
		.to("kafka:{{kafka.host}}:{{kafka.port}}?topic=eventChannel&serializerClass=kafka.serializer.StringEncoder&producerType=async");
	}

}
