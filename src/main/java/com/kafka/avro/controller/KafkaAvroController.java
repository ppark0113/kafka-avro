package com.kafka.avro.controller;


import com.kafka.avro.model.Student;
import com.kafka.avro.service.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class KafkaAvroController {

    @Autowired
    ProducerService producerService;

    @PostMapping(value = "/send/avro/student/info")
    public String kafkaMessage(@RequestBody Student message) {
        producerService.sendMessage(message);
        return "Success";
    }
}
