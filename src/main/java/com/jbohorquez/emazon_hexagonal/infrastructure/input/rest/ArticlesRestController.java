package com.jbohorquez.emazon_hexagonal.infrastructure.input.rest;

import com.jbohorquez.emazon_hexagonal.application.dto.ArticleRequest;
import com.jbohorquez.emazon_hexagonal.application.dto.ArticleResponse;
import com.jbohorquez.emazon_hexagonal.application.handler.ArticlesHandler;
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
import java.util.List;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
@Tag(name = "articles", description = "article management")
public class ArticlesRestController {

    private final ArticlesHandler articlesHandler;

    @Operation(summary = "get paginated articles", description = "Returns a paginated list of articles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of items returned successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @GetMapping
    public ResponseEntity<Page<ArticleResponse>> getArticles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        Page<ArticleResponse> articles = articlesHandler.getArticle(page, size, sortDirection);
        return ResponseEntity.ok(articles);
    }

    @Operation(summary = "Guardar una nueva article", description = "Guarda una nueva article en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Article creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PostMapping("/")
    public ResponseEntity<Void> saveArticleIn(@Valid @RequestBody ArticleRequest articleRequest) {
        System.out.println("hola mundo");
        articlesHandler.saveArticleIn(articleRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Obtener todas las articles", description = "Devuelve una lista de todas las articles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de articles devuelta exitosamente")
    })
    @GetMapping("/")
    public ResponseEntity<List<ArticleResponse>> getArticleFrom() {
        return ResponseEntity.ok(articlesHandler.getArticleFrom());
    }

    @Operation(summary = "Obtener una article por ID", description = "Devuelve una article específica basada en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article devuelta exitosamente"),
            @ApiResponse(responseCode = "404", description = "Article no encontrada")
    })
    @GetMapping("/{brId}")
    public ResponseEntity<ArticleResponse> getArticleFrom(@PathVariable(name = "brId") Long articleId) {
        return ResponseEntity.ok(articlesHandler.getArticleFrom(articleId));
    }

    @Operation(summary = "Actualizar una article", description = "Actualiza una article existente en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Article actualizada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Article no encontrada")
    })
    @PutMapping("/")
    public ResponseEntity<Void> updateArticleIn(@Valid @RequestBody ArticleRequest articleRequest) {
        articlesHandler.updateArticleIn(articleRequest);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Eliminar una article", description = "Elimina una article existente basada en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Article eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Article no encontrada")
    })
    @DeleteMapping("/{articleId}")
    public ResponseEntity<Void> deleteArticleFrom(@PathVariable Long articleId) {
        articlesHandler.deleteArticleFrom(articleId);
        return ResponseEntity.noContent().build();
    }

}
