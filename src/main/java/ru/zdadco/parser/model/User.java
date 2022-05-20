package ru.zdadco.parser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(generator = "users_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "users_gen", sequenceName = "users_id_seq", allocationSize = 1)
    private Long id;
    private String username;
    private String url;

    @OneToMany(mappedBy = "user")
    private List<Article> articles;

    public User(String username, String url) {
        this.username = username;
        this.url = url;
    }
}
