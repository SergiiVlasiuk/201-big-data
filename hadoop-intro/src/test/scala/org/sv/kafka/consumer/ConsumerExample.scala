package org.sv.kafka.consumer

import org.apache.kafka.clients.consumer.{ConsumerConfig, KafkaConsumer}
import org.apache.kafka.common.serialization.StringDeserializer

import java.util
import scala.collection.JavaConverters._

object ConsumerExample extends App {

  import java.util.Properties

  val bootstrapServers = "localhost:9092"
  val TOPIC = "test"
  val groupId = "kafka-group-consumer"
  val props = new Properties()

  props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers)
  props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer].getName)
  props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer].getName)
  props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId)

  val consumer = new KafkaConsumer[String, String](props)

  consumer.subscribe(util.Collections.singletonList(TOPIC))

  while (true) {
    val records = consumer.poll(100)
    for (record <- records.asScala) {
      println(s"consumer receives: $record")
    }
  }
}
