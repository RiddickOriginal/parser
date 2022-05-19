package ru.zdadco.parser.service.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.zdadco.parser.model.Article;
import ru.zdadco.parser.model.Category;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ArticleRepository {

    private final JdbcTemplate template;

    public void saveArticles(List<Article> articles) {
        template.batchUpdate("insert into articles (publish_date, title, description, url) values (?, ?, ?, ?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setTimestamp(1, Timestamp.valueOf(articles.get(i).getPublishDate().toLocalDateTime()));
                ps.setString(2, articles.get(i).getTitle());
                ps.setString(3, articles.get(i).getDescription());
                ps.setString(4, articles.get(i).getUrl());
            }

            @Override
            public int getBatchSize() {
                return articles.size();
            }
        });
    }

}
