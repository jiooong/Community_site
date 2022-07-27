<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.example.article.dto.ArticleDto" %>

<%
    ArticleDto article = (ArticleDto)request.getAttribute("article");
%>

<h1>게시물 리스트</h1>

<div>
    <% if ( article != null ) { %>
    <div>
        ID : <%=article.getId()%>
    </div>
    <div>
        TITLE : <%=article.getTitle()%>
    </div>
    <div>
        BODY : <%=article.getBody()%>
    </div>
    <% } %>
</div>