package com.meli.infoIp.services.interfaces;

import com.meli.infoIp.model.InvocationResponse;
import com.meli.infoIp.model.entity.Invocation;

public interface InvocationInterface {

    InvocationResponse findFarthestInvocation();
    InvocationResponse findNearestInvocation();
    Double calculateAverageDistance();
    void setOrUpdateInvocation(String country, Double distance);



}
