package com.example.experimental.apilayer.api;

import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestBase {

    public static Response get(String apiKey, String path, Map<String, Object> params) {
        return given(SpecificationBuilder.getRequestSpecification())
                .header("api-token", apiKey)
                .queryParams(params)
                .when()
                .get(path)
                .then()
                .spec(SpecificationBuilder.getResponseSpecification())
                .extract()
                .response();
    }


}
