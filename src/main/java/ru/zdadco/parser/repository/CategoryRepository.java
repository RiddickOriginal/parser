package ru.zdadco.parser.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.zdadco.parser.model.Category;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    boolean existsByName(String name);

    Optional<Category> findByName(String name);
}