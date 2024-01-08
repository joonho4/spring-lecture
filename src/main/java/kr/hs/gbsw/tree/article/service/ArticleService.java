package kr.hs.gbsw.tree.article.service;

import jakarta.transaction.Transactional;
import kr.hs.gbsw.tree.article.dto.DeleteArticleDto;
import kr.hs.gbsw.tree.article.dto.UpdateArticleDto;
import kr.hs.gbsw.tree.article.dto.CreateArticleDto;
import kr.hs.gbsw.tree.article.model.Article;
import kr.hs.gbsw.tree.article.repository.ArticleRepository;
import kr.hs.gbsw.tree.user.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ArticleService {
    private ArticleRepository articleRepository;

    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    @Transactional
    public Article getArticle(int id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시물이 없습니다."));
    }

    public Article createArticle(User user, CreateArticleDto dto) {
        LocalDateTime now = LocalDateTime.now();

        Article article = new Article(
                0,
                dto.getTitle(),
                dto.getContent(),
                dto.getAuthor(),
                dto.getPassword(),
                user,
                new ArrayList<>(),
                now,
                now
        );

        return articleRepository.save(article);
    }

    public Article modifyArticle(int id, User user, UpdateArticleDto dto) {
        Article article = getArticle(id);

        // 로그인한 사용자가 작성한 게시물인가? 아니라면 익명 게시물
        if (article.getUser() != null) {
            // 사용자가 로그인하였는가?
            if (user == null) {
                throw new IllegalArgumentException("로그인이 필요합니다.");
            }
            // 게시물 작성자와 로그인한 사람이 동일한가?
            if (!article.getUser().getId().equals(user.getId())) {
                throw new IllegalArgumentException("내 글만 수정 가능합니다.");
            }
        } else {        // 익명 등록 게시물
            if (!article.getPassword().equals(dto.getPassword())) {
                throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
            }
        }

        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());
        article.setAuthor(dto.getAuthor());
        article.setUpdatedAt(LocalDateTime.now());
        return articleRepository.save(article);
    }

    public void deleteArticle(int id, User user, DeleteArticleDto dto) {
        Article article = getArticle(id);

        // 로그인한 사용자가 작성한 게시물인가? 아니라면 익명 게시물
        if (article.getUser() != null) {
            // 사용자가 로그인하였는가?
            if (user == null) {
                throw new IllegalArgumentException("로그인이 필요합니다.");
            }
            // 게시물 작성자와 로그인한 사람이 동일한가?
            if (!article.getUser().getId().equals(user.getId())) {
                throw new IllegalArgumentException("내 글만 삭제 가능합니다.");
            }
        } else {        // 익명 등록 게시물
            if (!article.getPassword().equals(dto.getPassword())) {
                throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
            }
        }

        articleRepository.delete(article);
    }
}
