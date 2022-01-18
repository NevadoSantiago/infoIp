package com.meli.infoIp.repository;

import com.meli.infoIp.model.entity.Invocation;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvocationRepository extends JpaRepository<Invocation, String> {

    Optional<Invocation> findTopByOrderByDistanceDesc();
    Optional<Invocation> findTopByOrderByDistanceAsc();

}
