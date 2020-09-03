### Kafka Docs
```
docker exec -it kafka-0 kafka-topics --create --topic raw_merchants --partitions 1 --replication-factor 1 --if-not-exists --zookeeper zookeeper-0:2181

docker exec -it kafka-0 kafka-topics --create --topic merchant_request --partitions 1 --replication-factor 1 --if-not-exists --zookeeper zookeeper-0:2181
docker exec -it kafka-0 kafka-topics --create --topic merchant_reply --partitions 1 --replication-factor 1 --if-not-exists --zookeeper zookeeper-0:2181

docker exec -it kafka-0 kafka-topics --list --zookeeper zookeeper-0:2181


docker exec -it kafka-0 kafka-topics --create --topic merchant_request --partitions 1 --replication-factor 1 --if-not-exists --zookeeper zookeeper-0:2181

docker exec -it kafka-0 kafka-console-producer --topic merchant_request --bootstrap-server kafka-0:9092

docker exec -it kafka-0 kafka-console-consumer --topic merchant_request --bootstrap-server kafka-0:9092

```
