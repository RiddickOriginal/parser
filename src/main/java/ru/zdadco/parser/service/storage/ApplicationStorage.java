package ru.zdadco.parser.service.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zdadco.parser.model.Article;
import ru.zdadco.parser.model.Category;
import ru.zdadco.parser.model.Statistic;
import ru.zdadco.parser.model.User;
import ru.zdadco.parser.repository.ArticleRepository;
import ru.zdadco.parser.repository.CategoryRepository;
import ru.zdadco.parser.repository.StatisticRepository;
import ru.zdadco.parser.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationStorage {

    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;
    private final StatisticRepository statisticRepository;

    @Transactional
    public void saveData(List<Article> articles) {
        for (Article article : articles) {
            User user = saveUser(article.getUser());
            List<Category> categories = article.getCategories().stream().map(this::saveCategory).collect(Collectors.toList());
            Statistic savedStatistic = statisticRepository.save(article.getStatistic());

            article.setUser(user);
            article.setCategories(categories);
            article.setStatistic(savedStatistic);
            articleRepository.save(article);
        }
    }

    public User saveUser(User user) {
        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());
        return userOptional.isEmpty()
                ? userRepository.save(user)
                : userOptional.get();
    }

    @Transactional
    public Category saveCategory(Category category) {
        Optional<Category> categoryOptional = categoryRepository.findByName(category.getName());
        return categoryOptional.isEmpty()
                ? categoryRepository.save(category)
                : categoryOptional.get();
    }

}
