
#Notes/Guide to create a simple testable environment via docker

With a docker-machine installation create a single instance of kafka like:

	docker pull spotify/kafka
	
	docker run -p 0.0.0.0:9092:9092 -p 0.0.0.0:2081:2081 spotify/kafka

log in the running container, edit `/opt/kafka_2.11-0.8.2.1/config/server.properties`

and change the property `advertised.host.name` to `advertised.host.name=<docker-machine ip>`

	docker restart <kafka server>.


##Prepare the topic for the event channel:

	docker exec -it dd1c /opt/kafka_2.11-0.8.2.1/bin/kafka-topics.sh --create --topic eventChannel --partitions 1 --replication-factor 1 --zookeeper <docker-machine ip>:2181
	
	docker exec -it dd1c /opt/kafka_2.11-0.8.2.1/bin/kafka-topics.sh --list --zookeeper <docker-machine ip>:2181

##Test the topic like:

	docker exec -it dd1c /opt/kafka_2.11-0.8.2.1/bin/kafka-console-consumer.sh --zookeeper <docker-machine ip>:2181 --topic eventChannel
	
	docker exec -it dd1c /opt/kafka_2.11-0.8.2.1/bin/kafka-console-producer.sh --broker-list <docker-machine ip>:9092 --topic eventChannel


##Prepare the application configuration:
Replace the kafka and zookeeper urls in the [application.properties](src/main/resources/application.properties) to your current <docker-machine ip> values.

##Set up Elastic:


	docker pull kibana
	
	docker pull elasticsearch
	
	docker pull logstash
	
Replace the zk_connect and elasticsearch parameter value hosts:port with the corresponding values assigned in your kafka and elasticsearch docker containers.
	
	docker run -d --name elastic -p 0.0.0.0:9200:9200 -p 0.0.0.0:9300:9300 elasticsearch 
	
	docker run -d --name logstash -it -v "$PWD":/config-dir logstash logstash -f /config-dir/logstash.conf
	
	docker run --link elastic:elasticsearch -d -p 0.0.0.0:5601:5601 kibana
