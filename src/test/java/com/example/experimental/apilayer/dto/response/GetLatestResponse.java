package com.example.experimental.apilayer.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Map;

@Data

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetLatestResponse {

    private String base;
    private String date;
    private Map<String, Double> rates;
    private boolean success;
    private long timestamp;

}
