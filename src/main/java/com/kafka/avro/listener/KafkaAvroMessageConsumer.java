package com.kafka.avro.listener;

import com.kafka.avro.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaAvroMessageConsumer {

  @KafkaListener(topics = "avro-pds", groupId = "pds")
  public void listen(Student message) {
    log.info("Received Messasge in group : {}", message);
  }
}
