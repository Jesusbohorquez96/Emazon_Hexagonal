package com.jbohorquez.emazon_hexagonal.infrastructure.input.rest;

import com.jbohorquez.emazon_hexagonal.application.dto.ArticleRequest;
import com.jbohorquez.emazon_hexagonal.application.dto.ArticleResponse;
import com.jbohorquez.emazon_hexagonal.application.handler.ArticlesHandler;
import com.jbohorquez.emazon_hexagonal.infrastructure.exceptionhandler.ExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
@Tag(name = "Articles", description = "Article management")
public class ArticlesRestController {

    private final ArticlesHandler articlesHandler;

    @Operation(summary = "Get paginated articles", description = "Returns a paginated list of articles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of items returned successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @GetMapping
    public ResponseEntity<Page<ArticleResponse>> getArticles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        Page<ArticleResponse> articles = articlesHandler.getArticle(page, size, sortBy, sortDirection);
        return ResponseEntity.ok(articles);
    }

    @Operation(summary = "Save a new article", description = "Saves a new article to the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Article created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })

    @PostMapping("/")
    public ResponseEntity<Map<String, String>> saveArticleIn(@Valid @RequestBody ArticleRequest articleRequest) {
        try {
            articlesHandler.saveArticleIn(articleRequest);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Collections.singletonMap("message", ExceptionResponse.SUCCESSFUL_CREATION.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Collections.singletonMap("message", ExceptionResponse.ALREADY_EXISTS.getMessage()));
        }
    }

    @Operation(summary = "Get all articles", description = "Returns a list of all articles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of articles returned successfully")
    })
    @GetMapping("/")
    public ResponseEntity<List<ArticleResponse>> getArticleFrom() {
        return ResponseEntity.ok(articlesHandler.getArticleFrom());
    }



    @Operation(summary = "Get an article by ID", description = "Returns a specific article based on its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article returned successfully"),
            @ApiResponse(responseCode = "404", description = "Article not found")
    })
    @GetMapping("/{brId}")
    public ResponseEntity<ArticleResponse> getArticleFrom(@PathVariable(name = "brId") Long articleId) {
        return ResponseEntity.ok(articlesHandler.getArticleFrom(articleId));
    }

    @Operation(summary = "Update an article", description = "Updates an existing article in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Article successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Article not found")
    })
    @PutMapping("/")
    public ResponseEntity<Void> updateArticleIn(@Valid @RequestBody ArticleRequest articleRequest) {
        articlesHandler.updateArticleIn(articleRequest);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete an article", description = "Deletes an existing article based on its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Article successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Article not found")
    })
    @DeleteMapping("/{articleId}")
    public ResponseEntity<Void> deleteArticleFrom(@PathVariable Long articleId) {
        articlesHandler.deleteArticleFrom(articleId);
        return ResponseEntity.noContent().build();
    }

}
