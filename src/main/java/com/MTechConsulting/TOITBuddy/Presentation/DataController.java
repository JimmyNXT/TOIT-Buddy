package com.MTechConsulting.TOITBuddy.Presentation;

import com.MTechConsulting.TOITBuddy.Domain.DTO.DataMessageDTO;
import com.MTechConsulting.TOITBuddy.Domain.DTO.DataSubscriptionDTO;
import com.MTechConsulting.TOITBuddy.Domain.DTO.PubSubSubscriptionDTO;
import com.MTechConsulting.TOITBuddy.Logic.Service.DataService;
import io.swagger.annotations.ApiOperation;
import io.toit.proto.toit.api.DataProto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/live/Data")
public class DataController {
    private final DataService service;

    @Autowired
    public DataController(DataService service) {
        this.service = service;
    }

    @GetMapping("/Subscriptions")
    @ApiOperation(nickname = "Get Subscriptions", value = "Get the Subscriptions")
    public ResponseEntity<List<DataSubscriptionDTO>> getSubscriptions(
            @RequestBody DataProto.DataType type
    ){
        return new ResponseEntity<>(service.getSubscriptions(type), HttpStatus.OK);
    }

    @GetMapping("/Messages")
    @ApiOperation(nickname = "Get Subscriptions", value = "Get the Subscriptions")
    public ResponseEntity<List<DataMessageDTO>> getSMessages(
            @RequestParam String subscriptionMame,
            @RequestBody DataProto.DataType type
    ){
        return new ResponseEntity<>(service.getMessages(subscriptionMame, type), HttpStatus.OK);
    }
}
