package ru.zdadco.parser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Category {
    private Long id;
    private String name;
    private String url;
}
