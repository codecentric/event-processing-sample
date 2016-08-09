package de.codecentric.processing;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import de.codecentric.events.EventFactory;

/**
 * 
 * Example routes to consume and produce new events based on certain criteria.
 * Find the documentation of the EPL on: {@link <a href="http://www.espertech.com/esper/documentation.php" >espertech.com – documentation</a>}
 * 
 */
@Component
public class CepExampleRoutes extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		/*
		 * create a new event AverageTotalEvent with an average value of the attribute totalAmount of the CartCreatedEvent – within a sliding time-window of 20 seconds. 
		 */
		from("esper://esper?eql=insert into AverageTotalEvent select avg(totalAmount) as amount from de.codecentric.events.CartCreatedEvent.win:time(20 sec)")
		.to("seda:eventChannel");
		
		/*
		 * create a new event ForeignInvoiceCreatedEvent if the billingCountry of the CartCreatedEvent is not Germany – checked within a sliding batched window of 5 seconds
		 */
		from("esper://esper?eql=insert into de.codecentric.events.ForeignInvoiceCreatedEvent(totalAmount, billingCountry) select totalAmount, billingCountry from de.codecentric.events.CartCreatedEvent.win:time_batch(5 sec) where billingCountry not in ('Germany')")
		.to("seda:eventChannel");
		
		/*
		 * create a new event LowConversionEvent if the average totalAmount of the CartCreatedEvents is below 700 – checked within a sliding batched window of  5 seconds
		 */
		from("esper://esper?eql=insert into de.codecentric.events.LowConversionEvent(avgTotalAmount) select avg(totalAmount) as avgTotalAmount from de.codecentric.events.CartCreatedEvent.win:time_batch(5 sec) having avg(totalAmount) < 700")
		.to("seda:eventChannel");
		
	}
}
