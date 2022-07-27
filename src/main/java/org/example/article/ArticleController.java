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
        List<ArticleDto> articleDtos = articleService.findAll();

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

    public void showDetail(Rq rq) {
        long id = 1;

        ArticleDto articleDto = articleService.findById(id);

        rq.setAttr("article", articleDto);
        rq.view("usr/article/detail");
    }
}
