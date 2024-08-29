package com.jbohorquez.emazon_hexagonal.infrastructure.configuration;


import com.jbohorquez.emazon_hexagonal.domain.api.IArticleServicePort;
import com.jbohorquez.emazon_hexagonal.domain.model.Article;
import com.jbohorquez.emazon_hexagonal.domain.spi.IArticlePersistencePort;
import com.jbohorquez.emazon_hexagonal.domain.usecase.ArticleUseCase;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.adapter.ArticleJpaAdapter;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.mapper.ArticleEntityMapper;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.repository.IArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;

@Configuration
@RequiredArgsConstructor
public class BeanConfigurationArticle {

    private final IArticleRepository articleRepository;
    private final ArticleEntityMapper articleEntityMapper;

    @Bean
    public IArticlePersistencePort articlePersistencePort() {
        return new ArticleJpaAdapter(articleRepository, articleEntityMapper);
    }

    @Bean
    public IArticleServicePort articleServicePort() {
        return new ArticleUseCase(articlePersistencePort()) {
            @Override
            public Page<Article> getArticles(int page, int size, boolean ascending) {
                return null;
            }
        };
    }
}
