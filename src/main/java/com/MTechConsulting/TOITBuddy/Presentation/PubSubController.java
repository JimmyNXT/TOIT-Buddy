package com.MTechConsulting.TOITBuddy.Presentation;

import com.MTechConsulting.TOITBuddy.Domain.DTO.PubSubMessageDTO;
import com.MTechConsulting.TOITBuddy.Domain.DTO.SubscriptionDTO;
import com.MTechConsulting.TOITBuddy.Logic.Service.PubSubService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/live/PubSub")
public class PubSubController {
    private final PubSubService service;

    @Autowired
    public PubSubController(PubSubService service) {
        this.service = service;
    }

    @GetMapping("/Subscriptions")
    @ApiOperation(nickname = "Get Subscriptions", value = "Get the Subscriptions")
    public ResponseEntity<List<SubscriptionDTO>> getSubscriptions(){
        return new ResponseEntity<>(service.getSubscriptions(), HttpStatus.OK);
    }

    @GetMapping("/Messages/ByName/{name}")
    @ApiOperation(nickname = "Get Messages for a Subscription", value = "Get the Messages for a given Subscription name")
    public ResponseEntity<List<PubSubMessageDTO>> getMessagesByName(@PathVariable String name){
        return new ResponseEntity<>(service.getMessagesByName(name), HttpStatus.OK);
    }

    @GetMapping("/Messages/ByTopic/{topic}")
    @ApiOperation(nickname = "Get Messages for a Subscription", value = "Get the Messages for a given Subscription topic")
    public ResponseEntity<List<PubSubMessageDTO>> getMessagesByTopic(@PathVariable String topic){
        return new ResponseEntity<>(service.getMessagesByTopic(topic), HttpStatus.OK);
    }
}
