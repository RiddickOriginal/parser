package ru.zdadco.parser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zdadco.parser.model.Article;
import ru.zdadco.parser.model.User;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findByUserAndTitle(User user, String title);

    List<Article> getAllByUser(User user);

    List<Article> getAllByPublishDate(Instant date);
}
