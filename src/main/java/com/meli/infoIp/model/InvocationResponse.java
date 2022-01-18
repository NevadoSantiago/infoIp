package com.meli.infoIp.model;

import com.meli.infoIp.model.entity.Invocation;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InvocationResponse {

    private String country;
    private Double distance;

    public static InvocationResponse entityToResponse(Invocation invocation){
        if(invocation==null) throw new IllegalArgumentException("Object is null");
        return InvocationResponse.builder()
            .country(invocation.getCountry())
            .distance(invocation.getDistance())
            .build();
    }

}
