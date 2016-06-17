package de.codecentric;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.espertech.esper.event.map.MapEventBean;

/**
 * 
 * Example routes to consume Events.
 * 
 * @author berthold.schulte@codecentric.de
 *
 */
@Component
public class CepRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		from("esper://esper-dom?eql=select count(totalAmount) as cnt from de.codecentric.CartCreatedEvent.win:time_batch(5 sec) where totalAmount > 900")
				.process(new Processor() {

					@Override
					public void process(Exchange exchange) throws Exception {
						MapEventBean event = (MapEventBean) exchange.getIn().getBody(MapEventBean.class);
						System.out.println("counts " + event.get("cnt"));
					}
				});
		
		from("esper://esper-dom?eql=select avg(totalAmount) as cnt from de.codecentric.CartCreatedEvent.win:time_batch(5 sec)")
		.process(new Processor() {

			@Override
			public void process(Exchange exchange) throws Exception {
				MapEventBean event = (MapEventBean) exchange.getIn().getBody(MapEventBean.class);
				System.out.println("avg " + event.get("cnt"));
			}
		});

	}

}
