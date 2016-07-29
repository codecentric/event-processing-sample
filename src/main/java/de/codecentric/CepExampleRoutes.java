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
	
	@Override
	public void configure() throws Exception {

		from("esper://esper-dom?eql=insert into AverageTotalEvent select avg(totalAmount) as amount from de.codecentric.CartCreatedEvent.win:time(20 sec)")
		.to("direct:eventChannel");
		from("esper://esper-dom?eql=insert into de.codecentric.ForeignInvoiceCreatedEvent(totalAmount, billingCountry) select totalAmount, billingCountry from de.codecentric.CartCreatedEvent.win:time_batch(5 sec) where billingCountry not in ('Germany')")
		.to("direct:eventChannel");
		from("esper://esper-dom?eql=insert into de.codecentric.LowConversionEvent(avgTotalAmount) select avg(totalAmount) as avgTotalAmount from de.codecentric.CartCreatedEvent.win:time_batch(5 sec) having avg(totalAmount) < 700")
		.to("direct:eventChannel");
		
	}
}
