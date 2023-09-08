package com.example.project01.authorization;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class MyApiClient {
    @Value("${api.key}")
    private String apiKey;

    public void makeAPICall() throws InterruptedException {
        String uri = "https://sandbox-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";

        List<Map.Entry<String, String>> parameters = new ArrayList<>();
        parameters.add(Map.entry("start", "1"));
        parameters.add(Map.entry("limit", "5000"));
        parameters.add(Map.entry("convert", "USD"));

        try {
            String result = sendRequest(uri, parameters);
            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Parse the JSON string into a JsonNode object
            JsonNode jsonNode = objectMapper.readTree(result);

            // Extract symbol and name from the JSON data
            JsonNode dataNode = jsonNode.get("data");
            if (dataNode != null && dataNode.isArray()) {
                // Assuming there's only one object in the "data" array
//                JsonNode firstData = dataNode.get(0);
//                String symbol = firstData.get("symbol").asText();
//                String name = firstData.get("name").asText();

                for(JsonNode itemNode : dataNode){
                    String id = itemNode.get("id").asText();
                    String symbol = itemNode.get("symbol").asText();
                    String name = itemNode.get("name").asText();
                    String slug = itemNode.get("slug").asText();
                    String cmc_rank = itemNode.get("cmc_rank").asText();
                    String total_supply = itemNode.get("total_supply").asText();



                    //   Use the symbol and name as needed
                    System.out.println("id: "+ id);
                    System.out.println("Symbol: " + symbol);
                    System.out.println("Name: " + name);
                    System.out.println("Slug: "+ slug);
                    System.out.println("cmc_rank: "+ cmc_rank);
                    System.out.println("total_supply: "+ total_supply);
                    System.out.println();
                }


            }
           // System.out.println(result);
        } catch (IOException e) {
            System.out.println("Error: cannot access content - " + e.toString());
        } catch (URISyntaxException e) {
            System.out.println("Error: Invalid URL " + e.toString());
        }
    }
    public String sendRequest(String uri, List<Map.Entry<String, String>> parameters) throws URISyntaxException, IOException, InterruptedException {
        //new instance of HttpClient is created
        HttpClient client = HttpClient.newHttpClient();

        //instance of HttpHeader - to store the request headers
        HttpHeaders headers = new HttpHeaders();
        //Headers - ACCEPT and X-CMC_PRO_API_KEY
        //ACCEPT- to indicate that client expects a JSON response
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        //customer ley with variable named apiKey
        headers.set("X-CMC_PRO_API_KEY", apiKey);

        //buildQueryString- to construct query string from list of parameters
        String queryString = buildQueryString(parameters);
        //URI-by concatenating uri end point with constructed query string
        URI requestUri = new URI(uri + "?" + queryString);

        //Building request URI, Http method
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(requestUri)
                .GET();

        headers.forEach((name, values) -> {
            values.forEach(value -> {
                requestBuilder.header(name, value);
            });
        });

        //To build final request object
        HttpRequest request = requestBuilder.build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Response status code "+response.statusCode());

        return response.body();
    }
    private String buildQueryString(List<Map.Entry<String, String>> parameters) {
        StringBuilder queryString = new StringBuilder();
        for (Map.Entry<String, String> parameter : parameters) {
            if (queryString.length() > 0) {
                queryString.append("&");
            }
            queryString.append(parameter.getKey()).append("=").append(parameter.getValue());
        }
        return queryString.toString();
    }
}
