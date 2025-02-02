package com.ctlfab.estatehandle.client;

import com.ctlfab.estatehandle.dto.LocationDTO;
import com.ctlfab.estatehandle.dto.PoiDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class HereClient {
    @Value("${here.api.key}")
    private String apiKey;

    @Value("${here.geocode.api.baseUrl}")
    private String geocodeBaseUrl;


    @Value("${here.browse.api.baseUrl}")
    private String browseBaseUrl;

    /**
     * The method makes a GET request to retrieve location by Geocode API
     *
     * @param query A search query to geocode,
     * example of query format (5+Rue+Daunou%2C+75000+Paris%2C+France), replace space with + and dot with %2
     * @return A {@link LocationDTO} object containing the geolocation data, or {@code null} otherwise
     */
    public LocationDTO callGeocode(String query) {
        log.info("Retrieve location from geocode api");

        String uri = "/geocode?q=" + query +
                "&limit=4&apiKey="+apiKey;
        WebClient webClient = WebClient.create(geocodeBaseUrl);

        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .mapNotNull(jsonString -> {
                    try {
                        ObjectMapper mapper = new ObjectMapper();
                        JsonNode rootNode = mapper.readTree(jsonString).path("items").get(0);

                        JsonNode addressNode = rootNode.path("address");
                        String county = addressNode.path("county").asText();
                        String city = addressNode.path("city").asText();
                        String postalCode = addressNode.path("postalCode").asText();
                        String street = addressNode.path("street").asText() + ", " + addressNode.path("houseNumber").asText();

                        JsonNode positionNode = rootNode.path("position");
                        Float lat = positionNode.path("lat").floatValue();
                        Float lng = positionNode.path("lng").floatValue();

                        return new LocationDTO(county, city, postalCode, street, lat, lng);
                    }catch (JsonProcessingException e) {
                        return null;
                    }
                }).block();
    }

    /**
     * The method makes a GET request to retrieve point of interest of location by Browse API
     *
     * @param lat latitude
     * @param lng longitude
     * @return A list of {@link PoiDTO} object containing the geolocation data, or {@code null} otherwise
     */
    public List<PoiDTO> callBrowser(Float lat, Float lng) {
        log.info("Retrieve poi from Browser");

        String uri = "/browse?at=" + lat + "," + lng +
                "&categories=400-4100,800-8250" +
                "&limit=10" +
                "&in=circle:" + lat + "," + lng + ";r=500" +
                "&apiKey="+apiKey;
        WebClient webClient = WebClient.create(browseBaseUrl);

        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .mapNotNull(jsonString -> {
                    try{
                        ObjectMapper mapper = new ObjectMapper();
                        JsonNode rootNode = mapper.readTree(jsonString).path("items");

                        List<PoiDTO> poiList = new LinkedList<>();
                        for (int index = 0; index < rootNode.size(); index++) {
                            JsonNode itemNode = rootNode.get(index);

                            JsonNode positionNode = itemNode.path("position");
                            Float poiLat = positionNode.path("lat").floatValue();
                            Float poiLng = positionNode.path("lng").floatValue();

                            JsonNode categoryNode = itemNode.path("categories").get(0);
                            String poiCategory = categoryNode.path("name").asText();

                            PoiDTO poi = new PoiDTO(poiLat, poiLng, poiCategory);
                            poiList.add(poi);
                        }

                        return poiList;
                    }catch (JsonProcessingException e) {
                        return null;
                    }
                })
                .block();

    }
}
