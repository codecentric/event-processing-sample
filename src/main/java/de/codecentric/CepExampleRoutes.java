package de.codecentric;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * Example routes to consume Events.
 * 
 */
@Component
public class CepExampleRoutes extends RouteBuilder {

	Logger LOG = LoggerFactory.getLogger(CepExampleRoutes.class);
	
	@Autowired
	private EventFactory eventFactory;

	@Override
	public void configure() throws Exception {
/*
		from("esper://esper-dom?eql=select count(*) as cnt from de.codecentric.CartCreatedEvent.win:time(5 sec) where totalAmount > 200")
				.process(new Processor() {

					@Override
					public void process(Exchange exchange) throws Exception {
						MapEventBean event = (MapEventBean) exchange.getIn().getBody(MapEventBean.class);
						LOG.info("Current count of records: {} > 200", event.get("cnt"));
					}
				});

		from("esper://esper-dom?eql=select avg(totalAmount) as avg from de.codecentric.CartCreatedEvent.win:time(5 sec)")
				.process(new Processor() {

					@Override
					public void process(Exchange exchange) throws Exception {
						MapEventBean event = (MapEventBean) exchange.getIn().getBody(MapEventBean.class);
						LOG.info("Current average ammount: {}", event.get("avg"));
					}
				});
*/
/*
		from("esper://esper-dom?eql=select billingCountry, totalAmount from de.codecentric.CartCreatedEvent.win:time_batch(5 sec) where billingCountry not in ('Germany')")
				.process(new Processor() {

					@Override
					public void process(Exchange exchange) throws Exception {
						MapEventBean event = (MapEventBean) exchange.getIn().getBody(MapEventBean.class);
						LOG.info("have to write an invoice with taxes vor country: {}", event.get("billingCountry"));
						int totalAmount = (int) event.get("totalAmount");
						
						exchange.getIn().setBody(new ForeignInvoiceCreatedEvent(totalAmount, 
								(String) event.get("billingCountry")));
					}
				}).marshal().json().convertBodyTo(String.class).setHeader(KafkaConstants.PARTITION_KEY)
				.simple(".").setHeader(KafkaConstants.KEY).simple("1")
				.to("kafka:localhost:9092?topic=eventChannel&serializerClass=kafka.serializer.StringEncoder");
*/

		from("esper://esper-dom?eql=insert into de.codecentric.ForeignInvoiceCreatedEvent(totalAmount, billingCountry) select totalAmount, billingCountry from de.codecentric.CartCreatedEvent.win:time_batch(5 sec) where billingCountry not in ('Germany')")
		.bean(EventFactory.class, "createFromEsperEvent").marshal().json().convertBodyTo(String.class).setHeader(KafkaConstants.PARTITION_KEY)
		.simple(".").setHeader(KafkaConstants.KEY).simple("1")
		.to("kafka:localhost:9092?topic=eventChannel&serializerClass=kafka.serializer.StringEncoder");
		
		from("esper://esper-dom?eql=insert into de.codecentric.LowConversion(avgTotalAmount) select avg(totalAmount) as avgTotalAmount from de.codecentric.CartCreatedEvent.win:time_batch(5 sec) having avg(totalAmount) < 700")
		.bean(EventFactory.class, "createFromEsperEvent").marshal().json().convertBodyTo(String.class).setHeader(KafkaConstants.PARTITION_KEY)
		.simple(".").setHeader(KafkaConstants.KEY).simple("1")
		.to("kafka:localhost:9092?topic=eventChannel&serializerClass=kafka.serializer.StringEncoder");
		
	}
}
