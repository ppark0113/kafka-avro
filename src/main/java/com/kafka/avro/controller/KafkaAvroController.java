package com.kafka.avro.controller;


import com.kafka.avro.model.Student;
import com.kafka.avro.service.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class KafkaAvroController {


    @Autowired
    ProducerService producerService;

    @PostMapping(value = "/send/avro/student/info/sync")
    public String sendMessageSync(@RequestBody Student message) {
        producerService.sendMessageSync(message);
        return "Success";
    }

    @PostMapping(value = "/send/avro/student/info/async")
    public String sendMessageAsync(@RequestBody Student message) {
        producerService.sendMessageAsync(message);
        return "Success";
    }


    @PostMapping(value = "/send/avro/student/info/sync/100")
    public String sendMessageSync100(@RequestBody Student message) {
        for(int i=0; i<100; i++) {
            log.info("count : " + i);
            producerService.sendMessageSync(message);
        }
        //error의 이유

        return "Success";
    }

    @PostMapping(value = "/send/avro/student/info/async/100")
    public String sendMessageAsync100(@RequestBody Student message) {
        for(int i=0; i<100; i++) {
            log.info("count : " + i);
            producerService.sendMessageAsync(message);
        }
        return "Success";
    }

    @PostMapping(value = "/send/avro/student/info/partition/{partition}")
    public String sendMessageToPartition(@RequestBody Student message, @PathVariable("partition") int partition) {
        producerService.sendMessageToPartition(message , partition);
        return "Success";
    }
}
