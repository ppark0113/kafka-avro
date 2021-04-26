package com.kafka.avro.service;

import com.kafka.avro.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class ProducerService {

    @Value("${kafka.topic.name}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, Student> kafkaTemplate;

    public void sendMessageAsync(Student message) {
        ListenableFuture<SendResult<String, Student>> future = kafkaTemplate.send(topicName, message);
        future.addCallback(
                new ListenableFutureCallback<SendResult<String, Student>>() {
                    @Override
                    public void onSuccess(SendResult<String, Student> result) {
                        log.info(
                                "Sent message=[{}] with offset=[{}]", message, result.getRecordMetadata().offset());
                    }

                    @Override
                    public void onFailure(Throwable ex) {
                        log.info("Unable to send message=[{}] due to : {}", message, ex.getMessage());
                    }
                });
    }

    public void sendMessageSync(Student message) {
        log.info("Sent message=[{}]", message);
        try {
            kafkaTemplate.send(topicName, message).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void sendMessageToPartition(Student message , int partition) {
        log.info("Sent message=[{}]", message);
        kafkaTemplate.send(topicName, partition , null, message);
    }

}
