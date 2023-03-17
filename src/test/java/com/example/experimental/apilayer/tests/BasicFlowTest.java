package com.example.experimental.apilayer.tests;

import com.example.experimental.apilayer.api.RestBase;
import com.example.experimental.apilayer.dto.response.GetSymbolsResponse;
import com.example.experimental.apilayer.utils.Helpers;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.example.experimental.apilayer.api.PathConstants.LATEST;
import static com.example.experimental.apilayer.api.PathConstants.SYMBOLS;

public class BasicFlowTest extends BaseTest {

    String symbol;
    Map<String, String> symbols;

    @Test
    void getAvailableSymbols() {
        GetSymbolsResponse response = RestBase.get(apiKey, SYMBOLS)
                .then()
                .statusCode(200)
                .extract()
                .response()
                .as(GetSymbolsResponse.class);

        symbols = response.getSymbols();
        symbol = Helpers.getRandomKeyFromMap(symbols);

    }

    @Test(dependsOnMethods = "getAvailableSymbols")
    void getLatestForSingleSymbol() {
        Map<String, Object> params = new HashMap<>();
        params.put("base", symbol);
        RestBase.get(apiKey, LATEST, params)
                .then()
                .statusCode(200);
    }

}
