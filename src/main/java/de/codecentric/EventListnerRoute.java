package de.codecentric;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonDataFormat;
import org.springframework.stereotype.Component;

/**
 * Consumes events from a kafka server and the topic 'carts'.
 * Events are unmarshaled and send to the esper endpoint.
 * 
 * @author berthold.schulte@codecentric.de
 *
 */
@Component
public class EventListnerRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		JsonDataFormat jsonDataFormat = new JsonDataFormat();
		jsonDataFormat.setUnmarshalType(CartCreatedEvent.class);

		from("kafka:localhost:9092?zookeeperConnect=localhost:2181&topic=carts&groupId=testing")
		.log("Got: ${body}")
		.unmarshal(jsonDataFormat)
		.to("esper://esper-dom");
	}

}
