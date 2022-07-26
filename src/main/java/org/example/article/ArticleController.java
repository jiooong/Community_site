package org.example.article;

import org.example.Rq;
import org.example.article.dto.ArticleDto;

import java.io.IOException;
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

        rq.println("%d번 게시물이 생성되었습니다.".formatted(id));

    }

    public void showDetail(Rq rq) throws IOException {
        long id = rq.getLongPathValueByIndex(1,0);
        if(id==0){
            rq.println("번호를 입력해주세요");
            return;
        }

        ArticleDto articleDto = articleService.findById(id);
        if (articleDto == null) {
            rq.println("해당 글이 존재하지 않습니다.");
            return;
        }


        rq.setAttr("article", articleDto);
        rq.view("usr/article/detail");
    }



    public void doDelete(Rq rq) throws IOException {
        long id = rq.getLongPathValueByIndex(1,0);
        if(id==0){
            rq.println("번호를 입력해주세요");
            return;
        }

        ArticleDto articleDto = articleService.findById(id);

        if (articleDto == null) {
            rq.println("해당 글이 존재하지 않습니다.");
            return;
        }

        articleService.delete(id);

        rq.println("<div>%d번 게시물이 삭제되었습니다.</div>".formatted(id)); //append 함수는 내부에 특정 HTML을 추가한다
        rq.println("<div><a href=\"/usr/article/list/free\">리스트로 돌아가기</a></div>".formatted(id));
    }

    public void ShowModify(Rq rq) throws IOException {
        long id = rq.getLongPathValueByIndex(1,0);
        if(id==0){
            rq.println("번호를 입력해주세요");
            return;
        }

        ArticleDto articleDto = articleService.findById(id);
        if (articleDto == null) {
            rq.println("해당 글이 존재하지 않습니다.");
            return;
        }


        rq.setAttr("article", articleDto);
        rq.view("usr/article/modify");
    }

    public void doModify(Rq rq) throws IOException {
        long id = rq.getLongPathValueByIndex(1,0);

        String title = rq.getParam("title", "");
        String body = rq.getParam("body", "");

        articleService.modify(id,title,body);

        rq.println("<div>%d번 게시물이 수정되었습니다</div>".formatted(id));
        rq.println("<div><a href=\"/usr/article/detail/free/%d\">수정된 글로 돌아가기</a></div>".formatted(id));

    }
}

