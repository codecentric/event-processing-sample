package de.codecentric.processing;

import java.util.Random;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.codecentric.events.CartCreatedEvent;
import de.codecentric.events.EventFactory;


/**
 * 
 * Creates faked carts with a random total-amount below 1000 for demo purposes.
 * The carts are pushed to a kafka server and the topic 'carts'
 * 
 */
@Component
public class CreateCartsRoute extends RouteBuilder {

	@Autowired
	private EventFactory eventFactory;

	@Override
	public void configure() throws Exception {
		from("timer://foo?fixedRate=true&period=1000").process(new Processor() {

			@Override
			public void process(Exchange exchange) throws Exception {
				exchange.getIn().setBody(new CartCreatedEvent(new Random().nextInt(1000),"Germany"));
			}
		})
		.bean(eventFactory)
		.marshal().json().convertBodyTo(String.class)
		
				.setHeader(KafkaConstants.PARTITION_KEY)
				.simple(".")
				.setHeader(KafkaConstants.KEY).simple( "1")
				.to("kafka:{{kafka.host}}:{{kafka.port}}?topic=eventChannel&serializerClass=kafka.serializer.StringEncoder&producerType=async");

		from("timer://foo?fixedRate=true&period=3000").process(new Processor() {

			@Override
			public void process(Exchange exchange) throws Exception {
				exchange.getIn().setBody(new CartCreatedEvent(new Random().nextInt(1000),"USA"));
			}
		})
		.bean(eventFactory)
		.marshal().json().convertBodyTo(String.class)
				.setHeader(KafkaConstants.PARTITION_KEY)
				.simple(".")
				.setHeader(KafkaConstants.KEY).simple( "1")
				.to("kafka:{{kafka.host}}:{{kafka.port}}?topic=eventChannel&serializerClass=kafka.serializer.StringEncoder&producerType=async");
		
	}

}
