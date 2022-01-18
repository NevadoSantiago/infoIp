package com.meli.infoIp.services;

import com.meli.infoIp.model.entity.Invocation;
import com.meli.infoIp.repository.InvocationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvocationService {

    @Autowired
    InvocationRepository repository;

    public List<Invocation> getAllInvocations(){
        List<Invocation> list = repository.findAll();
        return list;
    }
}
