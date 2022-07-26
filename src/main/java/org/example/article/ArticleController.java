package org.example.article;

import org.example.Rq;
import org.example.article.dto.ArticleDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArticleController {
    private ArticleService articleService;

    public ArticleController(){
        articleService = new ArticleService();
    }

    public void showList(Rq rq) throws IOException {
        List<ArticleDto> articleDtos = new ArrayList<>(); //Dto는 단순 데이터를 담는 녀석이다

        articleDtos.add(new ArticleDto(1,"제목:1","내용1"));
        articleDtos.add(new ArticleDto(2,"제목:2","내용2"));
        articleDtos.add(new ArticleDto(3,"제목:3","내용3"));

        rq.setAttr("articles", articleDtos);
        rq.view("usr/article/list");

    }

    public void showWrite(Rq rq) {
        rq.view("usr/article/write");
    }

    public void doWrite(Rq rq) throws IOException {
        String title = rq.getParam("title", "");
        String body = rq.getParam("body", "");


        long id = articleService.write(title,body);

        rq.appendBody("%d번 게시물이 생성되었습니다.".formatted(id));

    }
}
