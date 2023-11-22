package rs.edu.shipping.kafka;

import lombok.NoArgsConstructor;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class KafkaConfig {
  private String groupId = "my-java-application";

  private Properties getProperties(){
    Properties properties = new Properties();
    properties.put("bootstrap.servers", "aware-spider-6876-eu2-kafka.upstash.io:9092");
    properties.put("sasl.mechanism", "SCRAM-SHA-256");
    properties.put("security.protocol", "SASL_SSL");
    properties.put("sasl.jaas.config", "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"YXdhcmUtc3BpZGVyLTY4NzYkvA8z9Z-yhLyqCSi_zgnO76p_IXskE-5mLBBSu8k\" password=\"ZTljZWFhNjEtNDUzMy00ODQ5LWE5MjYtMmIzYjZhMDE4N2Qz\";");

    return properties;
  }

  public Properties getProducerProperties(){
    Properties properties = getProperties();
    properties.setProperty("key.serializer", StringSerializer.class.getName());
    properties.setProperty("value.serializer", StringSerializer.class.getName());
    return properties;
  }

  public Properties getConsumerProperties(){
    Properties properties = getProperties();
    properties.setProperty("key.deserializer", StringDeserializer.class.getName());
    properties.setProperty("value.deserializer", StringDeserializer.class.getName());
    properties.setProperty("group.id", groupId);
    properties.setProperty("auto.offset.reset", "latest");

    return properties;
  }



}
