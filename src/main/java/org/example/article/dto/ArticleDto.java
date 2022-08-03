package org.example.article.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor //자동으로 만들어준다.
@Getter
@Setter
public class ArticleDto {

    private long id;
    private String title;
    private String body;
}