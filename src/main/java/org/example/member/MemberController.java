package org.example.member;

import org.example.Rq;

import java.io.IOException;

public class MemberController {
    public void showLogin(Rq rq) throws IOException {
        rq.println("로그인");
    }
}
