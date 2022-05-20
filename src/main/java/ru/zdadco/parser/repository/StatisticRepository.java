package ru.zdadco.parser.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.zdadco.parser.model.Statistic;

@Repository
public interface StatisticRepository extends CrudRepository<Statistic, Long> {
}
