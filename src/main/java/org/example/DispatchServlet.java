package org.example;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.article.ArticleController;
import org.example.member.MemberController;

import java.io.IOException;
import java.lang.reflect.Member;

@WebServlet("/usr/*")
public class DispatchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Rq rq = new Rq(req,resp);

        MemberController memberController = new MemberController();
        ArticleController articleController = new ArticleController();

  //같은 경로에 요청이 다를 수 있기 떄문에
        switch(rq.getMethod()){
            case "GET":
                switch(rq.getActionPath()){
                    case "/usr/article/modify":
                        articleController.ShowModify(rq);
                        break;
                    case "/usr/article/detail":
                        articleController.showDetail(rq);
                        break;
                    case "/usr/article/list":
                        articleController.showList(rq);
                        break;
                    case "/usr/article/write":
                        articleController.showWrite(rq);
                        break;
                    case "/usr/article/delete":
                        articleController.doDelete(rq);
                        break;
                    case "/usr/member/login":
                        memberController.showLogin(rq);
                        break;
                }
                break;
            case "POST":
                switch(rq.getActionPath()){
                    case "/usr/article/write":
                        articleController.doWrite(rq);
                        break;
                    case "/usr/article/modify":
                        articleController.doModify(rq);
                        break;
                }
                break;
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }

}