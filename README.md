docker start dd1c
docker-machine ssh default -L 9092:192.168.99.100:9092
docker-machine ssh default -L 2181:192.168.99.100:2181
docker exec -it dd1c /opt/kafka_2.11-0.8.2.1/bin/kafka-console.consumer.sh --zookeeper 192.168.99.100:2181 --topic test
docker exec -it dd1c /opt/kafka_2.11-0.8.2.1/bin/kafka-console-producer.sh --broker-list 192.168.99.100:9092 --topic test
docker exec -it dd1c /opt/kafka_2.11-0.8.2.1/bin/kafka-topics.sh --list --zookeeper 192.168.99.100:2181
docker exec -it dd1c /opt/kafka_2.11-0.8.2.1/bin/kafka-topics.sh --create --topic carts --partitions 1 --replication-factor 1 --zookeeper 192.168.99.100:2181
