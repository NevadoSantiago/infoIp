package com.meli.infoIp.controller.rest;

import com.meli.infoIp.exceptions.InvocationException;
import com.meli.infoIp.model.InvocationResponse;
import com.meli.infoIp.services.interfaces.InvocationInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/invocations")
public class InvocationsController {

    @Autowired
    InvocationInterface invocationService;

    @GetMapping("/farthest")
    public ResponseEntity<?> getFarthestInvocation(){
        try{
            InvocationResponse response = invocationService.findFarthestInvocation();
            return ResponseEntity.ok(response);
        }catch(InvocationException e){
            return new ResponseEntity<>(e.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/nearest")
    public ResponseEntity<?> getNearestInvocation(){
        try{
            InvocationResponse response = invocationService.findNearestInvocation();
            return ResponseEntity.ok(response);
        }catch(InvocationException e){
            return new ResponseEntity<>(e.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/distanceAverage")
    public ResponseEntity<?> getDistanceAverageInvocation(){
        try{
            Double response = invocationService.calculateAverageDistance();
            return ResponseEntity.ok(response);
        }catch(InvocationException e){
            return new ResponseEntity<>(e.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }
    }




}
