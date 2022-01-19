package com.meli.infoIp.services;

import com.meli.infoIp.exceptions.IllegalArgumentException;
import com.meli.infoIp.exceptions.InvocationException;
import com.meli.infoIp.model.InvocationResponse;
import com.meli.infoIp.model.entity.Invocation;
import com.meli.infoIp.repository.InvocationRepository;
import com.meli.infoIp.services.interfaces.InvocationInterface;
import com.meli.infoIp.utils.StringUtils;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class InvocationService implements InvocationInterface {

    @Autowired
    InvocationRepository repository;

    public final String COUNTRY_NAME_VALUE_ISNT_PRESENT_ERROR =
        "El nombre del pais no puede ser nulo";

    public final String DISTANCE_VALUE_ISNT_PRESENT_ERROR =
        "El parametro de la distancia no puede ser nulo";

    private final String NO_INVOCATION_DATA_CAUSE = "No hay datos para consultar";
    private final String NO_INVOCATION_DATA_ACTION = "Realice una consulta para generar datos";

    public void setOrUpdateInvocation(String country, Double distance){
        StringUtils.validateStringIsPresent(
            country,
            COUNTRY_NAME_VALUE_ISNT_PRESENT_ERROR + " setOrUpdateInvocation method");
        if(distance == null) throw new IllegalArgumentException(
            DISTANCE_VALUE_ISNT_PRESENT_ERROR + " setOrUpdateInvocation method");

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
