package kr.hs.gbsw.tree.article.controller;

import kr.hs.gbsw.tree.article.dto.CreateArticleDto;
import kr.hs.gbsw.tree.article.dto.DeleteArticleDto;
import kr.hs.gbsw.tree.article.dto.UpdateArticleDto;
import kr.hs.gbsw.tree.article.model.Article;
import kr.hs.gbsw.tree.article.service.ArticleService;
import kr.hs.gbsw.tree.user.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/articles")
@AllArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public List<Article> getArticles() {
        return articleService.getArticles();
    }

    @GetMapping("/{id}")
    public Article getArticle(@PathVariable int id) {
        return articleService.getArticle(id);
    }

    @PostMapping
    public Article createArticle(
            @AuthenticationPrincipal User user,
            @RequestBody CreateArticleDto dto
    ) {
        return articleService.createArticle(user, dto);
    }

    @PutMapping("/{id}")
    public Article modifyArticle(
            @PathVariable int id,
            @AuthenticationPrincipal User user,
            @RequestBody UpdateArticleDto dto
    ) {
        return articleService.modifyArticle(id, user, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteArticle(
            @PathVariable int id,
            @AuthenticationPrincipal User user,
            @RequestBody(required = false) DeleteArticleDto dto
    ) {
        articleService.deleteArticle(id, user, dto);
    }
}
