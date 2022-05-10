package ru.zdadco.parser;

import ru.zdadco.parser.loader.Loader;
import ru.zdadco.parser.model.Article;
import ru.zdadco.parser.parser.HtmlParser;

import java.util.List;

public class ParserRunner {

    public static final String HABR_DOMAIN = "https://habr.com";
    private static final String HABR_URL = HABR_DOMAIN + "/ru/all";

    public static void main(String[] args) {
        Loader loader = new Loader();
        HtmlParser parser = new HtmlParser();


        String response = loader.load(HABR_URL);
        List<Article> articles = parser.parse(response);
        System.out.println();

    }
}
