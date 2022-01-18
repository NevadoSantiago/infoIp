package com.meli.infoIp.services;

import com.meli.infoIp.exceptions.InvocationException;
import com.meli.infoIp.model.InvocationResponse;
import com.meli.infoIp.model.entity.Invocation;
import com.meli.infoIp.repository.InvocationRepository;
import com.meli.infoIp.services.interfaces.InvocationInterface;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvocationService implements InvocationInterface {

    private final String NO_INVOCATION_DATA_CAUSE = "No hay datos para consultar";
    private final String NO_INVOCATION_DATA_ACTION = "Realice una consulta para generar datos";

    @Autowired
    InvocationRepository repository;

    public void setOrUpdateInvocation(String country, Double distance){
        Optional<Invocation> optionalInvocation = getInvocationByCountry(country);
        Invocation entity;
        if(optionalInvocation.isPresent()){
            entity = optionalInvocation.get();
            entity.addInvocation();
        }else{
            entity = new Invocation(
                country,
                distance
            );
        }
        repository.save(entity);
    }
    public InvocationResponse findFarthestInvocation(){
        Optional<Invocation> optionalInvocation = repository.findTopByOrderByDistanceDesc();
        if(optionalInvocation.isPresent())
            return optionalInvocation.map(InvocationResponse::entityToResponse).get();
        throw new InvocationException(NO_INVOCATION_DATA_CAUSE,NO_INVOCATION_DATA_ACTION);
    }
    public InvocationResponse findNearestInvocation(){
        Optional<Invocation> optionalInvocation = repository.findTopByOrderByDistanceAsc();
        if(optionalInvocation.isPresent())
            return optionalInvocation.map(InvocationResponse::entityToResponse).get();
        throw new InvocationException(NO_INVOCATION_DATA_CAUSE,NO_INVOCATION_DATA_ACTION);
    }
    public Double calculateAverageDistance(){
        List<Invocation> invocations = repository.findAll();
        if(invocations.isEmpty())
            throw new InvocationException(NO_INVOCATION_DATA_CAUSE,NO_INVOCATION_DATA_ACTION);
        Double total = 0.0;
        Integer count = 0;
        for(Invocation inv : invocations){
            count += inv.getInvocations();
            total += inv.getInvocations() * inv.getDistance();
        }
        return total/count;
    }
    private Optional<Invocation> getInvocationByCountry(String country){
        return repository.findById(country);
    }
}
