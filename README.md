#Event-driven Microservices with lightweight Event Processing

**Note**:The code of this project is just for demonstration purposes – it is not production ready.

## Summary

The project outlines the usage of event processing within an event-driven architecture (EDA) in a lightweight way. The project does not distinguish between the terms complex event processing (CEP) and event stream processing (ESP). ESP is rather treated as a subset of CEP.
An event channel of the EDA is realized by the message-broker [Apache Kafka](http://kafka.apache.org). The CEP part is realized with the the event processing engine and event processing language from [EsperTech](http://espertech.com). [Apache Camel](http://camel.apache.org) is used to connect the event channel to the event processing engine.
The example can be started as a [spring boot application](http://projects.spring.io/spring-boot/).

Additionally the [Elastic stack](https://www.elastic.co) is used to visualize some results/statistics of events that occurred.

## Application

A few hints to setup a testable environment and how to test the application are summarized in the [environment-notes](environment-notes.md).

The code is structured as follows:

The class `CepExampleApplication.java` is the boot-class of the application.

**Packages**
* `de.codecentric.channel` – contains the event-channel integration
* `de.codecentric.events` – contains a few example events and a simplified event-structure to keep events standardized in the system. However Esper has it's own event-structure internally
* `de.codecentric.processing` – contains routes to create faked shopping-cart events and CEP examples which do complex event processing on the cart events

	
##Test the application
Start the application in your IDE or as an Spring Boot application. The application produces faked shopping carts ([CreateCartsRoute](src/main/java/de/codecentric/processing/CreateCartsRoute.java)) which are published to the configured kafka server resp. event-channel. Furthermore the application reads from the event-channel, evaluates the incomming events and occasionally produces derived events. Consult the source code of ([CepExamplesRoute](src/main/java/de/codecentric/processing/CepExampleRoutes.java)) for details.