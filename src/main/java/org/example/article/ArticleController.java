package org.example.article;

import org.example.Rq;

import java.io.IOException;

public class ArticleController {
    public void showList(Rq rq) throws IOException {
        rq.appendBody("게시물 리스트");
    }
}
