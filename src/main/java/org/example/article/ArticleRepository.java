package org.example.article;

import org.example.article.dto.ArticleDto;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository { // 직접 저장
    private static List<ArticleDto> datum;
    private static long lastId;
    static{ //static 생성자 초기화하는 것
        datum = new ArrayList<>();
        lastId=0;
    }
    public long write( String title, String body) {
        long id = ++lastId;
        ArticleDto newArticleDto = new ArticleDto(id,title,body);

        datum.add(newArticleDto);
        return id;

    }
}
