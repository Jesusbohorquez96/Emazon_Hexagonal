package com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.adapter;

import com.jbohorquez.emazon_hexagonal.domain.model.Article;
import com.jbohorquez.emazon_hexagonal.domain.spi.IArticlePersistencePort;
import com.jbohorquez.emazon_hexagonal.infrastructure.exception.AlreadyExistsException;
import com.jbohorquez.emazon_hexagonal.infrastructure.exception.DescriptionTooLongException;
import com.jbohorquez.emazon_hexagonal.infrastructure.exception.NameTooLongException;
import com.jbohorquez.emazon_hexagonal.infrastructure.exception.NoDataFoundException;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.entity.ArticleEntity;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.mapper.ArticleEntityMapper;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.repository.IArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.DESCRIPTION_MAX_LENGTH;
import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.NAME_MAX_LENGTH;

@RequiredArgsConstructor
public class ArticleJpaAdapter implements IArticlePersistencePort {

    private final IArticleRepository articleRepository;
    private final ArticleEntityMapper articleEntityMapper;

    @Override
    public void saveArticle(Article article) {
        System.out.println("hola mundo como esta " + article.getCategories().size());
        if (article.getCategories().size() > 3) {
            throw new IllegalArgumentException("Article can have 1 to 3 categories");
        }
        if (article.getCategories().isEmpty()) {
            throw new IllegalArgumentException("Article must have at least one category");
        }
        System.out.println("hola " + article);
        if (article.getCategories().stream().distinct().count() != article.getCategories().size()) {
            throw new IllegalArgumentException("Categories must be different");
        }
        if (articleRepository.findByName(article.getName()).isPresent()) {
            throw new AlreadyExistsException();
        }
        if (article.getName().length() > NAME_MAX_LENGTH) {
            throw new NameTooLongException("Name is too long");
        }
        if (article.getDescription() == null || article.getDescription().length() > DESCRIPTION_MAX_LENGTH) {
            throw new DescriptionTooLongException("Description is too long");
        }
        articleRepository.save(articleEntityMapper.toEntity(article));
    }

    @Override
    public List<Article> getAllArticle() {
        List<ArticleEntity> articleEntityList = articleRepository.findAll();
        if (articleEntityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return articleEntityMapper.toArticleList(articleEntityList);
    }

    @Override
    public Article getArticleById(Long articleId) {
        return articleEntityMapper.toArticle(articleRepository.findById(articleId)
                .orElseThrow(AlreadyExistsException::new));
    }

    @Override
    public void updateArticle(Article article) {
        articleRepository.save(articleEntityMapper.toEntity(article));
    }

    @Override
    public void deleteArticle(Long articleId) {
        articleRepository.deleteById(articleId);
    }

    @Override
    public Page<Article> getArticles(PageRequest pageRequest) {
        Page<ArticleEntity> articleEntityPage = articleRepository.findAll(pageRequest);
        if (articleEntityPage.isEmpty()) {
            throw new NoDataFoundException();
        }
        return articleEntityPage.map(articleEntityMapper::toArticle);
    }


    @Override
    public Page<Article> getCategories(PageRequest pageRequest) {
        Page<ArticleEntity> articleEntityPage = articleRepository.findAll(pageRequest);
        if (articleEntityPage.isEmpty()) {
            throw new NoDataFoundException();
        }
        return articleEntityPage.map(articleEntityMapper::toArticle);
    }

    @Override
    public Page<Article> findAll(Pageable pageable) {
        return articleRepository.findAll(pageable).map(articleEntityMapper::toArticle);
    }
}
