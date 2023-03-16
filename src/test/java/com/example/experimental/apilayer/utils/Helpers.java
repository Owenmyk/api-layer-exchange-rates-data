package com.example.experimental.apilayer.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Helpers {

    public static String getRandomKeyFromMap(Map<String, String> map){
        Random r = new Random();
        List<String> keys = new ArrayList<>(map.keySet());
        return keys.get(r.nextInt(keys.size()));



    }

}
