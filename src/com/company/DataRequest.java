package com.company;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class DataRequest {

    /**
     * sets up a client and makes API request
     * @return response which is a json object converted to a string so that
     * it can easily be passed into the next function parseJsonForWord to extract
     * more specific data we're after
     */

    public static String getWordObject() {

        try {

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://quotes.rest/qod?category=inspire"))
                    .header("Content-Type", "application/json")
                    .build();
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body().toString();

        } catch (Throwable e) {
            e.printStackTrace();
        }

        return "Error attempting API Request. Try again.";
    }

    /**
     * This method takes in @param jsonString, the Http response object converted to
     * a string from the getWord() method
     *
     * Using the org.json library I have access to JSONObject, JSONArray and other helpful
     * methods that allow me to parse the @param jsonString for what values I want
     *
     */

    public static String parseJsonForWord(String jsonString) {

        try {

            System.out.println(jsonString);

            JSONObject json = new JSONObject(jsonString);
            JSONObject contents = json.getJSONObject("contents");
            JSONArray quotes = contents.getJSONArray("quotes");
            JSONObject quoteObj = quotes.getJSONObject(0);
            String quote = quoteObj.getString("quote");

//            testing if author is null which will throw an error but can only run this when the API runs the author is null
//            if (quoteObj.isNull("quote")) {
//                System.out.println("hellooo");
//            }
            String author = quoteObj.getString("author");

            System.out.println("quote: " + quote);
            System.out.println("author: " + author);

            return("Quote of the Day: " + quote + "\n\n"
                    + "Author: " + author + "\n\n"
                    + "Reply 'stop' to stop receiving these messages.");

        } catch (Throwable e) {
            e.printStackTrace();
        }

        return null;
    }

}
