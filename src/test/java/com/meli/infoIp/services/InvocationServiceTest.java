package com.meli.infoIp.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.meli.infoIp.exceptions.IllegalArgumentException;
import com.meli.infoIp.exceptions.InvocationException;
import com.meli.infoIp.model.entity.Invocation;
import com.meli.infoIp.repository.InvocationRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class InvocationServiceTest {

    @Mock
    InvocationRepository repository;

    InvocationService invocationService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
        invocationService = new InvocationService(repository);
    }

    @Test
    public void verifyAddInvocationIfItExistsOnDB(){
        String country = "Argentina";
        Invocation entity = buildFirstInvocation();
        when(repository.findById(country)).thenReturn(Optional.of(entity));
        invocationService.setOrUpdateInvocation(country, 1000.0);
        entity.addInvocation();
        verify(repository).save(entity);
    }

    @Test
    public void invocationExceptionIfThereIsNoDataOnDB(){
        when(repository.findTopByOrderByDistanceDesc()).thenReturn(Optional.empty());
        when(repository.findTopByOrderByDistanceAsc()).thenReturn(Optional.empty());
        when(repository.findAll()).thenReturn(new ArrayList<>());
        assertThrows(InvocationException.class, ()
            -> invocationService.findFarthestInvocation());
        assertThrows(InvocationException.class, ()
            -> invocationService.findNearestInvocation());
        assertThrows(InvocationException.class, ()
            -> invocationService.calculateAverageDistance());
    }
    @Test
    public void verifyAverageCalculation(){
        when(repository.findAll()).thenReturn(buildInvocationList());
        assertThat(invocationService.calculateAverageDistance()).isEqualTo(2000.0);
    }

    @Test
    public void IllegalArgumentExceptionIfCountryNameIsNull(){
        assertThrows(IllegalArgumentException.class, ()
        -> invocationService.setOrUpdateInvocation(null,0.0));
    }
    @Test
    public void IllegalArgumentExceptionIfDistanceIsNull(){
        assertThrows(IllegalArgumentException.class, ()
            -> invocationService.setOrUpdateInvocation("Argentina",null));
    }

    public Invocation buildFirstInvocation(){
        return new Invocation(
            "Argentina",
            1000.0
        );
    }
    public List<Invocation> buildInvocationList(){
        List<Invocation> list = new ArrayList<>();
        list.add(new Invocation("Argentina", 1000.0));
        list.add(new Invocation("España", 3000.0));
        return list;
    }


}
