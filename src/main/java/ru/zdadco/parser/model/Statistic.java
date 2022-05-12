package ru.zdadco.parser.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Statistic {
    private int reputations;
    private int views;
    private int bookmarks;
    private int comments;
}