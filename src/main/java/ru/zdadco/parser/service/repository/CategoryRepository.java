package ru.zdadco.parser.service.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.zdadco.parser.model.Category;
import ru.zdadco.parser.model.Statistic;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {

    private final JdbcTemplate template;

    public void saveCategories(List<Category> categories) {
        template.batchUpdate("insert into categories (name, url) values (?, ?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, categories.get(i).getName());
                ps.setString(2, categories.get(i).getUrl());
            }

            @Override
            public int getBatchSize() {
                return categories.size();
            }
        });
    }

}
