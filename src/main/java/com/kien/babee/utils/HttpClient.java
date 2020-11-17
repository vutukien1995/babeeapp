package com.kien.babee.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

public class HttpClient {

    public static void main(String[] args) {
        HttpResponse<String> response = Unirest.get("https://wordsapiv1.p.rapidapi.com/words/work")
                .header("x-rapidapi-key", "3a87b8d16dmsh11522835d743403p188339jsne6cfb6c7aaa1")
                .header("x-rapidapi-host", "wordsapiv1.p.rapidapi.com")
                .asString();


        System.out.println("Response: " + response.getBody());

    }

}
