package com.learnkafka.libraryeventsproducer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learnkafka.libraryeventsproducer.domain.LibraryEvent;
import com.learnkafka.libraryeventsproducer.producer.LibraryEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class LibraryEventsController {

    @Autowired
    private LibraryEventProducer libraryEventProducer;

    @PostMapping("/v1/libraryevent")
    public ResponseEntity<LibraryEvent> postLibraryEvent(
            @RequestBody LibraryEvent libraryEvent
    ) throws JsonProcessingException, ExecutionException, InterruptedException {

        // invoke kafka producer
        // libraryEventProducer.sendLibraryEvent(libraryEvent);
        libraryEventProducer.sendLibraryEventSynchronous(libraryEvent);
        return ResponseEntity.status(HttpStatus.CREATED).body(libraryEvent);
    }

}
