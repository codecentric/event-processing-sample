#Event-driven Microservices with lightweight Complex Event Processing

**Note**: This project is related to the examples used in the blog article:
[CodeCentric Blog: Event-driven Mircoservices](https://blog.codecentric.de/?p=37652&preview=true) and the code is just for demonstration purposes – it is not production ready. 

## Summary

The project outlines the usage of complex event processing (CEP) within an event-driven architecture (EDA) in a lightweight way. 
An event channel of the EDA is realized by the message-broker [Apache Kafka](http://kafka.apache.org). The CEP part is realized with the the event processing engine and event processing language from [EsperTech](http://espertech.com). [Apache Camel](http://camel.apache.org) is used to connect the event channel to the event processing engine.
The example can be started as a [spring boot application](http://projects.spring.io/spring-boot/).

Additionally the [Elastic stack](https://www.elastic.co) is used to visualize some results/statistics of events that occurred.

## Application

A few hints to setup a testable environment are summarized in [environment-notes](environment-notes.md).

To code is structured as follows:

The class `CepExampleApplication.java` is the boot-class of the application.

**Packages**
* `de.codecentric.channel` – contains the event-channel integration
* `de.codecentric.events` – contains a few example events and a simplified event-structure to keep events standardized in the system. However Esper has it's own event-structure internally
* `de.codecentric.processing` – contains routes to create faked shopping-cart events and CEP examples which do complex event processing on the cart events
