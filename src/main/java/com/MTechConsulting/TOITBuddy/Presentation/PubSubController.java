package com.MTechConsulting.TOITBuddy.Presentation;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/PubSub")
public class PubSubController {
    @GetMapping("/Subscriptions")
    @ApiOperation(nickname = "Get Subscriptions", value = "Get the Subscriptions")
    public ResponseEntity<Boolean> getSubscriptions(){
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }
}
