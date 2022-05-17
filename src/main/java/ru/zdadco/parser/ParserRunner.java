package ru.zdadco.parser;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.zdadco.parser.model.User;
import ru.zdadco.parser.service.loader.Loader;
import ru.zdadco.parser.service.loader.exception.LoaderException;
import ru.zdadco.parser.model.Article;
import ru.zdadco.parser.service.parser.HtmlParser;
import ru.zdadco.parser.service.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@SpringBootApplication
public class ParserRunner implements CommandLineRunner {

    private final Loader loader;
    private final HtmlParser parser;
    private final UserRepository userRepository;

    public static final String HABR_DOMAIN = "https://habr.com";
    private static final String HABR_URL = HABR_DOMAIN + "/ru/all";

    public static void main(String[] args) {
        SpringApplication.run(ParserRunner.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            String response = loader.load(HABR_URL);
            List<Article> articles = parser.parse(response);
            List<User> users = articles.stream().map(Article::getUser).collect(Collectors.toList());
            userRepository.saveUsers(users);
        } catch (LoaderException e) {
            e.printStackTrace();
        }
    }
}
