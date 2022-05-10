package ru.zdadco.parser.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.zdadco.parser.ParserRunner;
import ru.zdadco.parser.model.Article;

import java.util.ArrayList;
import java.util.List;

public class HtmlParser {

    public List<Article> parse(String html) {
        Document document = Jsoup.parse(html);

        Elements articleEls = document.select(".tm-articles-list__item");

        List<Article> articles = new ArrayList<>();
        for (Element articleEl : articleEls) {
            String username = articleEl.select(".tm-user-info__username").text();

            Elements titleEl = articleEl.select(".tm-article-snippet__title-link");
            String title = titleEl.select("span").text();
            String url = ParserRunner.HABR_DOMAIN + titleEl.attr("href");

            String description = articleEl.select(".article-formatted-body p").text();

            articles.add(new Article(username, title, description, url));
        }

        return articles;

    }

}
