package de.codecentric;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.camel.model.dataformat.JsonDataFormat;
import org.springframework.stereotype.Component;


/**
 * Creates faked carts with a random total-amount below 1000 for demo purposes.
 * The carts are pushed to a kafka server and the topic 'carts'
 * 
 * @author berthold.schulte@codecentric.de
 *
 */
@Component
public class CreateCartsRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		JsonDataFormat jsonDataFormat = new JsonDataFormat();
	
		from("timer://foo?fixedRate=true&period=1000").process(new Processor() {

			@Override
			public void process(Exchange exchange) throws Exception {
				exchange.getIn().setBody(new CartCreatedEvent((int) (Math.random() * 1000) + 1));

			}
		}).marshal(jsonDataFormat).convertBodyTo(String.class).setHeader(KafkaConstants.PARTITION_KEY)
				.simple(".")
				// .setHeader(KafkaConstants.KEY).simple( "1")
				.to("kafka:localhost:9092?topic=carts&serializerClass=kafka.serializer.StringEncoder");

	}

}
