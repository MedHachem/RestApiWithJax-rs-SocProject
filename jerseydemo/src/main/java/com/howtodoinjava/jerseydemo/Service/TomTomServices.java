package com.howtodoinjava.jerseydemo.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Service
public class TomTomServices {

    @Value("${tomtom.api.key}")
    private String apiKey;

    public String search(String query) {
        String apiUrl = "https://api.tomtom.com/search/2/search/" +
                query +
                ".json?key=" +
                apiKey;

        Client client = ClientBuilder.newClient();
        return client.target(apiUrl)
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);
    }
    public String getTomTomTrafficIncidents() {
        // URL de l'API TomTom Traffic Incidents (exemple)
        String apiUrl = "https://api.tomtom.com/traffic/services/4/incidentDetails/s3/37/-122/38/-121/10/10?key=" + apiKey;

        Client client = ClientBuilder.newClient();
        try {
            return client.target(apiUrl)
                    .request(MediaType.APPLICATION_JSON)
                    .get(String.class);
        } finally {
            client.close();
        }
    }


}
