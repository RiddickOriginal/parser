package ru.zdadco.parser.service.loader;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.zdadco.parser.service.loader.exception.LoaderException;

@Component
@RequiredArgsConstructor
public class Loader {

    private final RestTemplate restTemplate;

    public String load(String url) throws LoaderException {
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        if (response.getStatusCode().isError()) {
            throw new LoaderException("bad status code");
        }
        return response.getBody();
    }

}
