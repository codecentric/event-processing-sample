Event-driven Microservices – CEP-Examples
------------

This project is related to the examples used in the blog article:


It outlines complex event processing (CEP) within an event-driven architecture (EDA) in a lightweight way. 
The event channel of the EDA is realized by the message-broker Apache Kafka. The event processing engine and event processing language from EsperTech realizes the CEP part and Apache Camel is used to connect the event channel to the event processing engine.
The example can be started as a spring boot application.

Additionally the Elastic stack is used to visualize some results/statistics of events that occurred.

A few hints to setup the environment are summarized in [environment-notes](environment-notes.md).

The class `CepExampleApplication.java` is the boot-class of the application.
The package `de.codecentric.channel` contains the event-channel integration.
The package `de.codecentric.events` contains a few example events and a simplified event-structure to keep events standardized in the system. However Esper has it's own event-structure internally.
The package `de.codecentric.processing` contains routes to create faked shopping-cart events and CEP examples which do complex event processing on the cart events.

