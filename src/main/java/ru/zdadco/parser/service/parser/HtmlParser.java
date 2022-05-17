package ru.zdadco.parser.service.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import ru.zdadco.parser.ParserRunner;
import ru.zdadco.parser.model.Article;
import ru.zdadco.parser.model.Category;
import ru.zdadco.parser.model.Statistic;
import ru.zdadco.parser.model.User;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class HtmlParser {

    public List<Article> parse(String html) {
        Document document = Jsoup.parse(html);

        Elements articleEls = document.select(".tm-articles-list__item");

        List<Article> articles = new ArrayList<>();
        for (Element articleEl : articleEls) {
            User user = parseUser(articleEl);
            List<Category> categories = parseCategories(articleEl);
            Statistic statistic = parseStatistic(articleEl);

            String rawPublishDate = articleEl.select("time").attr("datetime");
            ZonedDateTime publishDateTime = ZonedDateTime.parse(rawPublishDate);

            Elements titleEl = articleEl.select(".tm-article-snippet__title-link");
            String title = titleEl.select("span").text();
            String url = ParserRunner.HABR_DOMAIN + titleEl.attr("href");

            String description = articleEl.select(".article-formatted-body p").text();

            articles.add(new Article(user, publishDateTime, categories, title, description, url, statistic));
        }

        return articles;
    }

    private User parseUser(Element element) {
        Elements userEl = element.select(".tm-user-info__username");
        String username = userEl.text();
        String userLink = ParserRunner.HABR_DOMAIN + userEl.attr("href");

        return new User(username, userLink);
    }

    private List<Category> parseCategories(Element element) {
        List<Category> categories = new ArrayList<>();
        Elements categoryEls = element.select(".tm-article-snippet__hubs-item a");
        for (Element categoryEl : categoryEls) {
            String categoryLink = ParserRunner.HABR_DOMAIN + categoryEl.attr("href");
            String categoryName = categoryEl.select("span").first().text();
            categories.add(new Category(categoryName, categoryLink));
        }
        return categories;
    }

    private Statistic parseStatistic(Element element) {
        String rawRating = element.select(".tm-votes-meter__value").text();
        int rating = Integer.parseInt(rawRating);

        String rawViews = element.select(".tm-icon-counter__value").text();
        int views;
        if (rawViews.contains("K")) {
            rawViews = rawViews.replaceAll("[.K]", "");
            views = Integer.parseInt(rawViews) * 100;
        } else {
            views = Integer.parseInt(rawViews);
        }

        String rawBookmarks = element.select(".bookmarks-button__counter").text();
        int bookmarks = Integer.parseInt(rawBookmarks);

        String rawComments = element.select(".tm-article-comments-counter-link__value").text();
        int comments = Integer.parseInt(rawComments);

        return new Statistic(rating, views, bookmarks, comments);
    }

}
