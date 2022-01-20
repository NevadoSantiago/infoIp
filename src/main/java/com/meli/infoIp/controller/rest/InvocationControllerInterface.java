package com.meli.infoIp.controller.rest;

import com.meli.infoIp.model.InvocationResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface InvocationControllerInterface {

    @ApiOperation(value = "", nickname = "get farther invocation",
        notes = "Api para consultar la invocacion mas lejana registrada", authorizations = {
    })
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Ok", response = InvocationResponse.class),
        @ApiResponse(code = 400, message = "Error, ver mensaje")
    })
    @GetMapping(
        value = "/farthest",
        produces = { "application/json" }
    )
    ResponseEntity<?> getFarthestInvocation();

    @ApiOperation(value = "", nickname = "get nearest invocation",
        notes = "Api para consultar la invocacion mas cercana registrada", authorizations = {
    })
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Ok", response = InvocationResponse.class),
        @ApiResponse(code = 400, message = "Error, ver mensaje")
    })
    @GetMapping(
        value = "/nearest",
        produces = { "application/json" }
    )
    ResponseEntity<?> getNearestInvocation();

    @ApiOperation(value = "", nickname = "get distance average invocations",
        notes = "Api para consultar el promedio de distancia de todas las consulta registradas", authorizations = {
    })
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Ok", response = Boolean.class),
        @ApiResponse(code = 400, message = "Error, ver mensaje")
    })
    @GetMapping(
        value = "/distanceAverage",
        produces = { "application/json" }
    )
    ResponseEntity<?> getDistanceAverageInvocation();
}
