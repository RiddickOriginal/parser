package ru.zdadco.parser.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class Article {
    private Long id;
    private User user;
    private ZonedDateTime publishDate;
    private List<Category> categories;
    private String title;
    private String description;
    private String url;
    private Statistic statistic;
}
