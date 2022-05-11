package ru.zdadco.parser.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Article {
    //private User user [username, url]
    private String username;
    //private ZonedDateTime publishDateTime;
    //private List<Category> categories; [name, url]
    private String title;
    private String description;
    private String url;
    //private Statistic statistic [int rep, view, bookmarks, comments]
}
