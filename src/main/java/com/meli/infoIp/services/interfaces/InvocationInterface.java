package com.meli.infoIp.services.interfaces;

import com.meli.infoIp.model.InvocationResponse;

public interface InvocationInterface {

    InvocationResponse findFarthestInvocation();
    InvocationResponse findNearestInvocation();
    Double calculateAverageDistance();
    void setOrUpdateInvocation(String country, Double distance);



}
