package com.kafka.avro.listener;

import com.kafka.avro.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaAvroMessageConsumer {

  private static int value = 1;

  //@KafkaListener(topics = "avro-pds", groupId = "pds")
  public void listen(Student message) {
    log.info("Received Messasge in group : {}", message);
  }

  @KafkaListener(topicPartitions = @TopicPartition(topic = "avro-pds" , partitions = {"0", "1" , "2"}))
  public void listenToPartition(Student message , @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
    log.info("Received Messasge in group : {} from partition {}", message , partition);
    //auto commit 위험성
  }

}
