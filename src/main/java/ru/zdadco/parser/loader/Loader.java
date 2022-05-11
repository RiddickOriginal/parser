package ru.zdadco.parser.loader;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.zdadco.parser.loader.exception.LoaderException;

public class Loader {

    private final RestTemplate restTemplate = new RestTemplate();

    public String load(String url) throws LoaderException {
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        if (response.getStatusCode().isError()) {
            throw new LoaderException("bad status code");
        }
        return response.getBody();
    }

}
