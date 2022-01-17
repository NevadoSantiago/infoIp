package com.meli.infoIp.model.apiCall;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class InfoCountryResponse {

    @JsonProperty("cca3")
    private String isoCode;
    

}



