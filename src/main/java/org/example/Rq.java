package org.example;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
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

    public void setAttr(String name, Object value ) {

        req.setAttribute(name, value); //서블릿이 JSP에게 데이터를 전송할떄의 수단
    }
    public void view(String path) {
        // gugudan2.jsp 에게 나머지 작업을 토스
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/" + path + ".jsp");
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getMethod() {
        return req.getMethod();
    }

    public String getPath() {
        return req.getRequestURI();
        //// getRequestURI는
        //        // http://localhost:8081/usr/article/list/free?page=1 에서
        //        // /usr/article/list/free 부분만 가져온다.
    }

    public String getParam(String paramName, String defaultValue) {
        String value = req.getParameter(paramName);

        if (value == null || value.trim().length() == 0) {
            return defaultValue;
        }

        return value;
    }

    public String getActionPath() {
        String[] bits = req.getRequestURI().split("/");

        return "/%s/%s/%s".formatted(bits[1], bits[2], bits[3]);
    }

    public long getLongPathValueByIndex(int index, long defaultValue) {
        String value = getPathValueByIndex(index, null);

        if ( value == null ) {
            return defaultValue;
        }

        try {
            return Long.parseLong(value);
        }
        catch ( NumberFormatException e ) {
            return defaultValue;
        }
    }
    public String getPathValueByIndex(int index, String defaultValue) {
        String[] bits = req.getRequestURI().split("/");

        try {
            return bits[4+index];
        }catch (ArrayIndexOutOfBoundsException e){
            return defaultValue;
        }

    }
}
