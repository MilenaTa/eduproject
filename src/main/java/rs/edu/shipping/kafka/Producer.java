package rs.edu.shipping.kafka;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Log4j
public class Producer {

  @Value("${kafka.topic}")
  private String topic;

  public void sendMessage(String key, String message) {
    KafkaConfig config = new KafkaConfig();
    var properties = config.getProducerProperties();
    KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
//    ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, message);
//    producer.send(producerRecord);

    // create a Producer Record
    ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, key, message);

    // send data
    producer.send(producerRecord, new Callback() {
      @Override
      public void onCompletion(RecordMetadata metadata, Exception e) {
        // executes everytime a record successfully sent or an exception is thrown
        if (e == null) {
          // the record was successfully sent
          log.info("Key: " + key + "| Partition: " + metadata.partition());
        } else {
          log.error("Error while producing " + e);
        }
      }
    });

    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    producer.flush();
    producer.close();
  }

}
