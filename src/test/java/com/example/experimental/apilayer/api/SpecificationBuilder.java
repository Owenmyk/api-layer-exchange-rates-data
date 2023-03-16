package com.example.experimental.apilayer.api;

import com.example.experimental.apilayer.utils.ConfigLoader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecificationBuilder {

    public static RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigLoader.getInstance().getApiBase())
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    public static ResponseSpecification getResponseSpecification() {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }

}
