package ru.zdadco.parser.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.zdadco.parser.model.Article;
import ru.zdadco.parser.service.util.ArticleService;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/article") // localhost:8000
@RequiredArgsConstructor
public class Controller {
    private final ArticleService articleService;

    @GetMapping("/new")
    public List<Article> getNewArticles() { // localhost:8000/article/new
        return articleService.getNew();
    }

    @GetMapping("/user?userId={userId}") // localhost:8000/article/user?userId=2
    public List<Article> getArticlesByUser(@PathVariable Long userId) {
        return articleService.getByUser(userId);
    }

    @PostMapping("/date") // localhost:8000/article/date {'date': '2022-05-24 00:00:00'}
    public List<Article> getArticlesByDate(@RequestBody Instant date) {
        return articleService.getByPublishDate(date);
    }
}
