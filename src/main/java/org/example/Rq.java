package org.example;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Rq {
    private final HttpServletRequest req;
    private final HttpServletResponse resp;

    public Rq(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        this.req=req;
        this.resp=resp;
        try {
            req.setCharacterEncoding("UTF-8");
        }catch(IOException e){
            throw new RuntimeException(e); //이거는 없애도 된다 하지만 없애는 경우 프로그램이 꺼진다. 선택사항임
        }

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");
    }

    public int getIntParam(String paramName, int defualtValue) {
        String value = req.getParameter(paramName); //getParameter는 String 또는 Null을 리턴한다, 입력한 데이터를 읽어오기

        if (value == null) {
            return defualtValue;

        }
        try {
            return Integer.parseInt(value); //숫자를 파싱하다가 정수가 아닌경우
        }
        catch (NumberFormatException e){ //defaultvalue 리턴턴
            return defualtValue;
        }
    }
    public void appendBody(String str) throws IOException {
        try {
            resp.getWriter().append(str);
        }catch(IOException e){
            throw new RuntimeException(e); //이거는 없애도 된다 하지만 없애는 경우 프로그램이 꺼진다. 선택사항임
        }

    }
}
