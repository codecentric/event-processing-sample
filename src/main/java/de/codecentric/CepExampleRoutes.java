package de.codecentric;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * 
 * Example routes to consume Events.
 * 
 */
@Component
public class CepExampleRoutes extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		from("esper://esper?eql=insert into AverageTotalEvent select avg(totalAmount) as amount from de.codecentric.CartCreatedEvent.win:time(20 sec)")
		.to("seda:eventChannel");
		from("esper://esper?eql=insert into de.codecentric.ForeignInvoiceCreatedEvent(totalAmount, billingCountry) select totalAmount, billingCountry from de.codecentric.CartCreatedEvent.win:time_batch(5 sec) where billingCountry not in ('Germany')")
		.to("seda:eventChannel");
		from("esper://esper?eql=insert into de.codecentric.LowConversionEvent(avgTotalAmount) select avg(totalAmount) as avgTotalAmount from de.codecentric.CartCreatedEvent.win:time_batch(5 sec) having avg(totalAmount) < 700")
		.to("seda:eventChannel");
		
	}
}
