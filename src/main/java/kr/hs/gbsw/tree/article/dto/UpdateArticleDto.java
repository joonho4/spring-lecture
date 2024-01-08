package kr.hs.gbsw.tree.article.dto;

import lombok.Getter;

@Getter
public class UpdateArticleDto {
    private String title;
    private String content;
    private String author;
    private String password;
}
