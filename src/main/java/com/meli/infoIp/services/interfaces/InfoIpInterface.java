package com.meli.infoIp.services.interfaces;

import com.meli.infoIp.model.InfoIpResponse;
import org.json.JSONException;

public interface InfoIpInterface {

    InfoIpResponse getInfoByIpAddress(String ipAddress) throws Exception;
}
