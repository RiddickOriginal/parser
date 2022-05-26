package ru.zdadco.parser.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "statistics")
@Data
@NoArgsConstructor
public class Statistic {
    @Id
    @GeneratedValue(generator = "statistics_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "statistics_gen", sequenceName = "statistics_id_seq", allocationSize = 1)
    private Long id;
    private int reputation;
    private int views;
    private int bookmarks;
    private int comments;

    public Statistic(int reputation, int views, int bookmarks, int comments) {
        this.reputation = reputation;
        this.views = views;
        this.bookmarks = bookmarks;
        this.comments = comments;
    }
}
