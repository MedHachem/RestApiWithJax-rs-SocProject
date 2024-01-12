package com.howtodoinjava.jerseydemo.Controller;

import com.howtodoinjava.jerseydemo.GoogleMapsTrafficInfo;
import com.howtodoinjava.jerseydemo.MapQuestTrafficFlowResponse;
import com.howtodoinjava.jerseydemo.Service.TomTomServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Controller
@Path("/api")
public class TomTomController {
    @Value("${tomtom.api.key}")
    private String apiKey;
    @Value("${openweathermap.api.key}")
    private String oapiKey;
    @Value("${here.api.appId}")
    private String appId;

    @Value("${here.api.appCode}")
    private String appCode;
    @Value("${mapquest.api.key}")
    private String MapquestapiKey;
    @Autowired
    private TomTomServices tomTomServices;
    private final RestTemplate restTemplate;

    public TomTomController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GET
    @Path("/search")
    public String search(@QueryParam("query") String query) {
        return tomTomServices.search(query);
    }
/////// donner le traffic
@GET
@Path("/flow")
@Produces(MediaType.APPLICATION_JSON)
public Response getTrafficFlow(@QueryParam("location") String location) {
    String apiUrl = "http://www.mapquestapi.com/traffic/v2/incidents" +
            "?key=" + MapquestapiKey +
            "&location=" + location;

    try {
        // Utilisez un client JAX-RS (Jersey) pour effectuer l'appel HTTP
        Client client = ClientBuilder.newClient();
        String trafficFlowResponse = client.target(apiUrl)
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);

        // Replace this with your actual logic to process the traffic flow information
        String processedTrafficFlow = processTrafficFlow(trafficFlowResponse);

        return Response.ok(processedTrafficFlow).build();
    } catch (Exception e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Error calling MapQuest Traffic API: " + e.getMessage())
                .build();
    }
}

    private String processTrafficFlow(String trafficFlowResponse) {
        // Implement your logic to process the raw traffic flow response here
        // For example, you may want to convert the response to a specific format or extract relevant information
        return "Processed Traffic Flow: " + trafficFlowResponse;
    }





    //////////////// donner les incidents
    @GET
    @Path("/incidents")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTrafficIncidents() {
        return tomTomServices.getTomTomTrafficIncidents();
    }






///Search places ( cafe-stade-restaurant)
    @GET
    @Path("/searchplace")
    public Response searchPlaces(@QueryParam("query") String query,
                                 @QueryParam("lat") double latitude,
                                 @QueryParam("lon") double longitude) {
        String apiUrl = "https://api.tomtom.com/search/2/search/" +
                query +
                ".json?key=" + apiKey +
                "&lat=" + latitude +
                "&lon=" + longitude;

        try {
            String apiResponse = restTemplate.getForObject(apiUrl, String.class);

            // Replace this with your actual logic to process the API response
            String processedResponse = processApiResponse(apiResponse);

            return Response.ok(processedResponse).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error calling TomTom API: " + e.getMessage())
                    .build();
        }
    }
    ///////// donner le weather dans une ville

    @GET
    @Path("/currentWeather")
    public Response getCurrentWeather(@QueryParam("city") String city) {
        String apiUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + oapiKey;

        try {
            String apiResponse = restTemplate.getForObject(apiUrl, String.class);

            // Replace this with your actual logic to process the API response
            String processedResponse = processApiResponse(apiResponse);

            return Response.ok(processedResponse).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error calling OpenWeatherMap API: " + e.getMessage())
                    .build();
        }
    }



    // Replace this with your actual logic to process the API response
    private String processApiResponse(String apiResponse) {
        // Assuming the response is a JSON matrix
        return "Processed Weather Info: " + apiResponse;
    }








}
