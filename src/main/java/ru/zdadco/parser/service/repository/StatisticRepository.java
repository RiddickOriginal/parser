package ru.zdadco.parser.service.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.zdadco.parser.model.Statistic;
import ru.zdadco.parser.model.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StatisticRepository {

    private final JdbcTemplate template;

    public void saveStatistics(List<Statistic> statistics) {
        template.batchUpdate("insert into statistics (reputation, views, bookmarks, comments) values (?, ?, ?, ?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, statistics.get(i).getReputations());
                ps.setLong(2, statistics.get(i).getViews());
                ps.setLong(3, statistics.get(i).getBookmarks());
                ps.setLong(4, statistics.get(i).getComments());
            }

            @Override
            public int getBatchSize() {
                return statistics.size();
            }
        });
    }

}
