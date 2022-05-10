package ru.zdadco.parser.loader;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Loader {

    private final RestTemplate restTemplate = new RestTemplate();

    public String load(String url) {
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }

}
