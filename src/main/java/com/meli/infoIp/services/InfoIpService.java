package com.meli.infoIp.services;

import com.meli.infoIp.model.InfoIpResponse;
import com.meli.infoIp.model.apiCall.CurrenciesResponse;
import com.meli.infoIp.model.apiCall.InfoCountryResponse;
import com.meli.infoIp.services.interfaces.InfoIpInterface;
import com.meli.infoIp.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
@Slf4j
public class InfoIpService implements InfoIpInterface {

    private final String ERROR_IPADDRESS_IS_NOT_PRESENT =
        "El valor del ip debe ser válido";

    @Autowired
    ApiCalls apiCallsService;
    @Autowired
    InvocationService invocationService;

    public InfoIpResponse getInfoByIpAddress(String ipAddress) throws Exception {
        StringUtils.validateStringIsPresent(
            ipAddress,
            ERROR_IPADDRESS_IS_NOT_PRESENT + " getInfoByIpAddress method");
        log.info("Starting data collection of {}",ipAddress);
        InfoCountryResponse infoCountryResponse = apiCallsService.getInfoCountryByName(
            apiCallsService.getCountryNameByIpAddress(ipAddress)
        );
        CurrenciesResponse infoCurrencyResponse = apiCallsService.getActualCurrencieInformation();
        InfoIpResponse response = InfoIpResponse.buildResponseByInformation(
        infoCurrencyResponse,
        infoCountryResponse
    );
        invocationService.setOrUpdateInvocation(
            response.getCountryName(),
            response.getEstimatedDistance());
    return response;
    }
}
