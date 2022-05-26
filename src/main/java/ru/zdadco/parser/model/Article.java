package ru.zdadco.parser.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "articles")
@Data
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(generator = "article_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "article_gen", sequenceName = "articles_id_seq", allocationSize = 1)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Instant publishDate;
    @ManyToMany
    @JoinTable(
            name = "articles_categories",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;
    private String title;
    private String description;
    private String url;
    @ManyToOne
    @JoinColumn(name = "statistic_id")
    private Statistic statistic;

    public Article(User user, ZonedDateTime publishDate, List<Category> categories, String title, String description, String url, Statistic statistic) {
        this.user = user;
        this.publishDate = publishDate.toInstant();
        this.categories = categories;
        this.title = title;
        this.description = description;
        this.url = url;
        this.statistic = statistic;
    }
}
