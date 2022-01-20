package com.meli.infoIp.services.interfaces;

import com.meli.infoIp.model.InfoIpResponse;

public interface InfoIpInterface {

    InfoIpResponse getInfoByIpAddress(String ipAddress) throws Exception;
}
