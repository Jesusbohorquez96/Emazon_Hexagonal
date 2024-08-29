package com.jbohorquez.emazon_hexagonal.application.handler;

import com.jbohorquez.emazon_hexagonal.application.dto.ArticleRequest;
import com.jbohorquez.emazon_hexagonal.application.dto.ArticleResponse;
import com.jbohorquez.emazon_hexagonal.application.mapper.ArticleRequestMapper;
import com.jbohorquez.emazon_hexagonal.application.mapper.ArticleResponseMapper;
import com.jbohorquez.emazon_hexagonal.domain.api.IArticleServicePort;
import com.jbohorquez.emazon_hexagonal.domain.model.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticlesHandler implements IArticlesHandler {

    private final ArticleRequestMapper articleRequestMapper;
    private final ArticleResponseMapper articleResponseMapper;
    private final IArticleServicePort articleServicePort;

    @Override
    public Page<ArticleResponse> getCategories(int page, int size, String sortDirection) {
        return null;
    }

    @Override
    public Page<ArticleResponse> getArticle(int page, int size, String sortDirection) {
        return articleServicePort.getArticles(page, size, Boolean.parseBoolean(sortDirection))
                .map(articleResponseMapper::toResponseList);
    }

    @Override
    public void saveArticleIn(ArticleRequest articleRequest) {
        Article article = articleRequestMapper.toArticle(articleRequest);
        articleServicePort.saveArticle(article);
    }

    @Override
    public List<ArticleResponse> getArticleFrom() {
        List<Article> article = articleServicePort.getAllArticle();
        return article.stream()
                .map(articleResponseMapper::toResponseList)
                .collect(Collectors.toList());
    }

    @Override
    public ArticleResponse getArticleFrom(Long articleId) {
        Article article = articleServicePort.getArticleById(articleId);
        return articleResponseMapper.toResponseList(article);
    }

    @Override
    public void updateArticleIn(ArticleRequest articleRequest) {
        Article article = articleRequestMapper.toArticle(articleRequest);
        articleServicePort.updateArticle(article);
    }

    @Override
    public void deleteArticleFrom(Long articleId) {
        articleServicePort.deleteArticle(articleId);
    }
}
