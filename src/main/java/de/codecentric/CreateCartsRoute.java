package de.codecentric;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


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
				exchange.getIn().setBody(new CartCreatedEvent((int) (Math.random() * 1000) + 1,"Germany"));
			}
		})
		.bean(eventFactory)
		.marshal().json().convertBodyTo(String.class)
				.setHeader(KafkaConstants.PARTITION_KEY)
				.simple(".")
				.setHeader(KafkaConstants.KEY).simple( "1")
				.to("kafka:localhost:9092?topic=eventChannel&serializerClass=kafka.serializer.StringEncoder");

		from("timer://foo?fixedRate=true&period=3000").process(new Processor() {

			@Override
			public void process(Exchange exchange) throws Exception {
				exchange.getIn().setBody(new CartCreatedEvent((int) (Math.random() * 1000) + 1,"USA"));
			}
		})
		.bean(eventFactory)
		.marshal().json().convertBodyTo(String.class)
				.setHeader(KafkaConstants.PARTITION_KEY)
				.simple(".")
				.setHeader(KafkaConstants.KEY).simple( "1")
				.to("kafka:localhost:9092?topic=eventChannel&serializerClass=kafka.serializer.StringEncoder");

		
		
	}

}
