package org.sv.kafka.producer

import org.apache.kafka.common.serialization.StringSerializer

object ProducerExample extends App {

  import org.apache.kafka.clients.producer._

  import java.util.Properties

  val props = new Properties()
  private val bootstrapServers = "localhost:9092"
  props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers)
  props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getName)
  props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getName)

  val producer = new KafkaProducer[String, String](props)

  val TOPIC = "test"

  for (i <- 71 to 80) {
//    val record = new ProducerRecord(TOPIC, i % 6, "key", s"hello $i")
    val record = new ProducerRecord(TOPIC, "key", s"hello $i")
    producer.send(record)
    println(s"producer sends: $record")
  }

  val record = new ProducerRecord(TOPIC, "key", "the end " + new java.util.Date)
  producer.send(record)
  println(s"producer sends: $record")

  producer.close()
}
