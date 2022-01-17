package com.meli.infoIp.model.apiCall;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor*/
public class CurrenciesResponse {

     /*   @JsonProperty("Id")
        private String id;
        @JsonProperty("Name")
        private String name;
        @JsonProperty("EffectiveEndDate")
        private String endDate;
        @JsonProperty("Description")
        private String description;
        @JsonProperty("EffectiveStartDate")
        private String startDate;
        @Nullable
        @JsonProperty("tpcChangeCounter__c")
        private Long tpcChangeCounter;
        @JsonProperty("Category")
        private String category;

        public final ProductResponse copy() {
            return ProductResponse.builder()
                .id(id)
                .name(name)
                .description(description)
                .category(category)
                .startDate(startDate)
                .endDate(endDate)
                .tpcChangeCounter(tpcChangeCounter)
                .build();
        }*/
}
