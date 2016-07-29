package de.codecentric;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.springframework.stereotype.Component;

@Component
public class EventChannelPublishRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("direct:eventChannel")
		.bean(EventFactory.class, "createFromEsperEvent").marshal().json().convertBodyTo(String.class).setHeader(KafkaConstants.PARTITION_KEY)
		.simple(".").setHeader(KafkaConstants.KEY).simple("1")
		.to("kafka:localhost:9092?topic=eventChannel&serializerClass=kafka.serializer.StringEncoder");

	}

}
